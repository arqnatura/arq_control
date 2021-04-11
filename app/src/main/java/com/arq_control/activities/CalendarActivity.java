package com.arq_control.activities;

import android.Manifest;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.arq_control.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_CALENDAR = 10;
    private ListView lstDays;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        setTitle("Busca un teléfono");

        // Encuentra la vista de lista
        this.lstDays = (ListView) findViewById(R.id.lstDays);

        // Leer y mostrar el calendario principal
        readEvents(view);

    }

    public void readEvents(View view) {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.READ_CALENDAR},
                    PERMISSIONS_REQUEST_READ_CALENDAR);
            return;
        }

        // Proyección de array: La creación de índices para esta matriz en lugar de realizar
        // búsquedas dinámicas mejora el rendimiento.
        final String[] INSTANCE_PROJECTION = new String[] {
                CalendarContract.Instances.EVENT_ID,      // 0
                CalendarContract.Instances.BEGIN,         // 1
                CalendarContract.Instances.TITLE,          // 2
                CalendarContract.Instances.ORGANIZER
        };

        // Los índices de la matriz de proyección anterior.
        final int PROJECTION_ID_INDEX = 0;
        final int PROJECTION_BEGIN_INDEX = 1;
        final int PROJECTION_TITLE_INDEX = 2;
        final int PROJECTION_ORGANIZER_INDEX = 3;

        String sortOrder = CalendarContract.Instances.BEGIN + " ASC";

        // Especificamos el rango de fechas en el que deseamos buscar instancias de eventos
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2021, 3, 23, 8, 0);
        long startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(2021, 5, 24, 8, 0);
        long endMillis = endTime.getTimeInMillis();


        // Pasamos el ID del evento cuyas instancias estamos buscando en la tabla Instances.
        String selection = CalendarContract.Instances.EVENT_ID + " = ?";
        String[] selectionArgs = new String[] {"207"};

        // Construimos la consulta con el rango de fechas deseado.
        Uri.Builder builder = CalendarContract.Instances.CONTENT_URI.buildUpon();
        ContentUris.appendId(builder, startMillis);
        ContentUris.appendId(builder, endMillis);

        // Enviamos la consulta
        Cursor cur =  getContentResolver().query(builder.build(),
                INSTANCE_PROJECTION,
                null,
                null,
                sortOrder);


        ArrayList<String> events = new ArrayList<>();
        while (cur.moveToNext()) {

            // Obtiene los valores del campo
            long eventID = cur.getLong(PROJECTION_ID_INDEX);
            long beginVal = cur.getLong(PROJECTION_BEGIN_INDEX);
            String title = cur.getString(PROJECTION_TITLE_INDEX);
            String organizer = cur.getString(PROJECTION_ORGANIZER_INDEX);

            // Jugamos con los valores.
            Log.i("Calendario", "Evento:  " + title);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(beginVal);
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Log.i("Calendario", "Fecha: " + formatter.format(calendar.getTime()));

            events.add(String.format("Fecha:  %s\nEvento:  %s"
                    ,formatter.format(calendar.getTime()), title));
        }

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, events);
        lstDays.setAdapter(stringArrayAdapter);
    }
}

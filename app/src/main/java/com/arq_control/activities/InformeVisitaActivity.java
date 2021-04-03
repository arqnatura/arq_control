package com.arq_control.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.arq_control.R;
import com.arq_control.models.VisitaDB;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;

public class InformeVisitaActivity extends AppCompatActivity {

    TextView textViewInfo;
    long idObra, idVisita;
    Realm realm;
    VisitaDB visitaDB;
//    ObraDB obraDB;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe_visita);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout_visita);


        // Código del informe
        textViewInfo = (TextView) findViewById(R.id.texto_titulo_visita);
        // Rescatamos el ID de la visita.
        Bundle extras = getIntent().getExtras();
        idVisita = extras.getLong(VisitaDB.VISITADB_ID);
        // Rescatamos los registros de la base de datos Realm de esa visita.
        realm = Realm.getDefaultInstance();
        // Tabla sobre la que queremos ejecutar la consulta VisitaDB.class
        // Buscamos el primer elemento que concuerde con la consulta.
        visitaDB = realm.where(VisitaDB.class)
                .equalTo(VisitaDB.VISITADB_ID, idVisita)
                .findFirst();

        toolBarLayout.setTitle(visitaDB.getTituloVisita());

        textViewInfo.setText("\nDatos de la Visita ___________"+
                "\n   Motivo: "+visitaDB.getTituloVisita()+
//                "\n   Promotor: "+obraDB.getPromotor()+
                "\n   Fecha: "+visitaDB.getFecha()+
                "   Visita Nº: "+visitaDB.getNumeroVisita()+
                "\nDescripción ________________\n"+visitaDB.getDescripcion()+
                "\n   Fecha Visita: "+visitaDB.getFecha()+
                "    Id: "+visitaDB.getIdVisita());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabFoto);
        imageView = (ImageView) findViewById(R.id.imageHeader);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 1);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_obra, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Regresa a la pantalla anterior", Toast.LENGTH_SHORT).show();
        onBackPressed();
        return true;
    }

}
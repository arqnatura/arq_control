package com.arq_control.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.arq_control.R;

import java.util.ArrayList;
import java.util.List;

public class ChoosePhoneActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private ListView lstNames;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_phone);
        setTitle("Busca un teléfono");

        // Encuentra la vista de lista
        this.lstNames = (ListView) findViewById(R.id.lstNames);

        // Leer y mostrar los contactos
        showContacts();
    }

    /**
     * Muestra los contactos en ListView.
     */
    private void showContacts() {
        // Verifique la versión del SDK y si el permiso ya está otorgado o no.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS);
            //Después de este punto, espere la devolución de llamada en el método de anulación
            // onRequestPermissionsResult(int, String[], int[])
        } else {
            // La versión de Android es inferior a 6.0 o el permiso ya está otorgado.
            List<String> contacts = getContactNames();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_list_item_1, contacts);
            lstNames.setAdapter(adapter);
        }
    }

/*    @Override
    public void onRequestPermissionsResult
            (int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Se concede el permiso
                showContacts();
            } else {
                Toast.makeText(this, "Hasta que se conceda el permiso, " +
                        "no podemos mostrar los nombres", Toast.LENGTH_SHORT).show();
            }
        }
    }
 */

     // Leer el nombre de todos los contactos y llenamos la lista
    private List<String> getContactNames() {

        List<String> contacts = new ArrayList<>();
        // Programamos el ContentResolver
        // Columnas a seleccionar
        String[] projeccion = new String[] {
                ContactsContract.Data.DISPLAY_NAME,
                //ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};
        // Condición del WHERE
        String selectionClause = ContactsContract.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
                + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";
        // ORDER BY columna [ASC|DESC]
        String sortOrder = ContactsContract.Data.DISPLAY_NAME + " ASC";

        // Obtenemos el ContentResolver
        Cursor c = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,  // URI de contenido para los contactos
                projeccion,                         // Columnas a seleccionar
                selectionClause,                    // Condición del WHERE
                null,                   // Valores de la condición
                sortOrder);                         // ORDER BY columna [ASC|DESC]

        if (c.moveToFirst()) {
            do {
                // Obtenemos los nombres y números de teléfono de los contactos
                String phone = (c.getString(0) + "\n   Teléfono:   " +
                        c.getString(1));
                contacts.add(phone);
            } while (c.moveToNext());
        }
        // Cerramos el cursor
        c.close();
        return contacts;
    }
}
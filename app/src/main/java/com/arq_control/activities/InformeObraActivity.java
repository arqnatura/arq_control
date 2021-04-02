package com.arq_control.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.arq_control.R;
import com.arq_control.models.ObraDB;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import io.realm.Realm;

public class InformeObraActivity extends AppCompatActivity {

    TextView textViewInfo;
    long idObra;
    Realm realm;
    ObraDB obraDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe_obra);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout_informe);

        // Código del informe
        textViewInfo = (TextView) findViewById(R.id.texto_titulo_obra);
        // Rescatamos el ID de la obra.
        Bundle extras = getIntent().getExtras();
        idObra = extras.getLong(ObraDB.OBRADB_ID);

        // Rescatamos los registros de la base de datos Realm de esa obra.
        realm = Realm.getDefaultInstance();
        // Tabla sobre la que queremos ejecutar la consulta ObraDB.class
        // Buscamos el primer elemento que concuerde con la consulta.
        obraDB = realm.where(ObraDB.class)
                .equalTo(ObraDB.OBRADB_ID, idObra)
                .findFirst();

         toolBarLayout.setTitle(obraDB.getTitulo());
         textViewInfo.setText("\n   Datos de la Obra___________"+
                              "\n   Título: "+obraDB.getTitulo()+
                              "\n   Promotor: "+obraDB.getPromotor()+
                              "\n   Dirección: "+obraDB.getDireccion()+
                              "\n   Teléfono: "+obraDB.getTelefono()+
                              "\n   Tipo de Obra: "+obraDB.getTipoObra()+
                              "\n   Referencia: "+obraDB.getReferencia()+
                              "\n   Fecha de inicio: "+obraDB.getFechaInicio()+
                              "\n   Fecha final: "+obraDB.getFechaFinal()+
                              "\n   Número Identificación: "+obraDB.getId());


         FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabVisita);
         fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Visita de Obra", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent miIntent = new Intent(InformeObraActivity.this, VisitasActivity.class);
                startActivity(miIntent);
            }
         });

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
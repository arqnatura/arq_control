package com.arq_control.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        // Código del informe
        textViewInfo = (TextView) findViewById(R.id.texto_informe);
        // Rescatamos el ID de la avería.
        Bundle extras = getIntent().getExtras();
        idObra = extras.getLong(ObraDB.OBRADB_ID);
        // Rescatamos los registros de la base de datos Realm de esa obra.
        realm = Realm.getDefaultInstance();
        // Tabla sobre la que queremos ejecutar la consulta ObraDB.class
        // Buscamos el primer elemento que concuerde con la consulta.
        obraDB = realm.where(ObraDB.class).equalTo(ObraDB.OBRADB_ID, idObra).findFirst();
         setTitle(obraDB.getTitulo());
         textViewInfo.setText(obraDB.getPromotor());
         // Seguimos poniendo todos los campos del informe.....

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
package com.arq_control.activities;

import android.os.Bundle;

import com.arq_control.ui.Obras.NuevaObraDialogo;
import com.arq_control.ui.Obras.OnNuevaObraListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.view.View;
import android.widget.Toast;

import com.arq_control.R;

public class ObrasActivity extends AppCompatActivity implements OnNuevaObraListener {
    DialogFragment dialogoNuevaObra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obras);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab);
        fab2.setOnClickListener((view) -> {
            dialogoNuevaObra = new NuevaObraDialogo();
            dialogoNuevaObra.show(getSupportFragmentManager(),"NuevaObraDialogo");
        });

 /*      FloatingActionButton fab = findViewById(R.id.fab);
         fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
 */
    }

    @Override
    public void onObraGuardarClickListener() {
        Toast.makeText(this, "Se ha recibido la Obra guardada", Toast.LENGTH_SHORT).show();
    }
}


package com.arq_control.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.arq_control.R;
import com.arq_control.models.ObraDB;
import com.arq_control.models.VisitaDB;
import com.arq_control.ui.visitas.EditVisitaDialogFragment;
import com.arq_control.ui.visitas.ListadoVisitasFragment;
import com.arq_control.ui.visitas.NuevaVisitaDialogo;
import com.arq_control.ui.visitas.OnNuevaVisitaListener;
import com.arq_control.ui.visitas.OnVisitaInteractionListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import io.realm.Realm;

public class VisitasActivity extends AppCompatActivity
        implements OnNuevaVisitaListener, OnVisitaInteractionListener {

    DialogFragment dialogoNuevaVisita, dialogEditVisita;
    Realm realm;
    ObraDB obra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarVisitas);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab3);
        fab.setOnClickListener((view) -> {
            dialogoNuevaVisita = new NuevaVisitaDialogo();
            dialogoNuevaVisita.show(getSupportFragmentManager(),"DialogoNuevaVisita");
            Snackbar.make(view, "Diálogo nueva visita.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });

        // Instanciamos el método de Realm getDefaultInstance
        realm = Realm.getDefaultInstance();
        // Rescatamos el contenedor y le cargamos un fragmento
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.containerVisitas, new ListadoVisitasFragment())
                .commit();

        // Rescatamos el ID de la obra en curso.
        Bundle extras = getIntent().getExtras();
        long idObra = extras.getLong("obraId");

        obra = realm.where(ObraDB.class)
                .equalTo("id",idObra)
                .findFirst();

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

    @Override
    public void OnVisitaClick(VisitaDB visitaDB) {
        Intent i = new Intent(this, InformeVisitaActivity.class);
        i.putExtra(VisitaDB.VISITADB_ID, visitaDB.getId());
        startActivity(i);
    }

    @Override
    public void OnVisitaEdit(VisitaDB mItem) {
        Toast.makeText(this, "Editar esta obra", Toast.LENGTH_SHORT).show();
        dialogEditVisita = EditVisitaDialogFragment.newInstance(mItem.getId(),
                mItem.getTituloVisita(), mItem.getFecha(), mItem.getNumeroVisita(),
                mItem.getDescripcion());
        dialogEditVisita.show(getSupportFragmentManager(),"EditObraDialogo");
    }

    @Override
    public void onVisitaGuardarClickListener(String tituloVisita, String fecha, long numeroVisita,
                                             String descripcion) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                VisitaDB nuevaVisita = new VisitaDB();
                nuevaVisita.setTituloVisita(tituloVisita);
                nuevaVisita.setFecha(fecha);
                nuevaVisita.setNumeroVisita(numeroVisita);
                nuevaVisita.setDescripcion(descripcion);
//                nuevaVisita.setAlmacenFoto(almacenFoto);

                obra.visitas.add(nuevaVisita);
//                realm.copyToRealmOrUpdate(nuevaVisita);
            }
        });
    }

    @Override
    public void onVisitaActualizarClickListener(long id, String tituloVisita, String fecha,
                                  long numeroVisita, String descripcion) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                VisitaDB nuevaVisita = new VisitaDB();
                nuevaVisita.setId(id);
                nuevaVisita.setTituloVisita(tituloVisita);
                nuevaVisita.setFecha(fecha);
                nuevaVisita.setNumeroVisita(numeroVisita);
                nuevaVisita.setDescripcion(descripcion);
//                nuevaVisita.setAlmacenFoto(almacenFoto);

                realm.copyToRealmOrUpdate(nuevaVisita);
            }
        });
    }

    @Override
    public void OnVisitaEliminar(VisitaDB mItem) { mostrarDialogoConfirmacion(mItem); }

        private void mostrarDialogoConfirmacion(final VisitaDB visitaDB) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Añadimos titulo y mensaje de diálogo con el usuario
            builder.setTitle("Eliminar Visita");
            builder.setMessage("¿Desea eliminar definitivamente la visita del día "
                    +visitaDB.getFecha()+" ?");
            // Add the buttons
            builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            long idVisitaEliminar = visitaDB.getId();
                            VisitaDB visitaEliminar = realm.where(VisitaDB.class)
                                    .equalTo(VisitaDB.VISITADB_ID, idVisitaEliminar).findFirst();
                            visitaEliminar.deleteFromRealm();
                        }
                    });
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }

/*    public void onClickVisitas(VisitaDB mItem) {
        Intent miIntent = new Intent(this, VisitasActivity.class);
        miIntent.putExtra("obraId", obra.getId());
        startActivity(miIntent);
    }

 */
}
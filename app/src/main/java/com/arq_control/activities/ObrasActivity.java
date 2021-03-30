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
import com.arq_control.ui.gallery.EditObraDialogFragment;
import com.arq_control.ui.gallery.ListadoObrasFragment;
import com.arq_control.ui.gallery.NuevaObraDialogo;
import com.arq_control.ui.gallery.OnNuevaObraListener;
import com.arq_control.ui.gallery.OnObraInteractionListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import io.realm.Realm;

public class ObrasActivity extends AppCompatActivity
        implements OnObraInteractionListener, OnNuevaObraListener {

    DialogFragment dialogoNuevaObra, dialogEditObra;
    Realm realm;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obras);
        Toolbar toolbar = findViewById(R.id.toolbarObras);
        setSupportActionBar(toolbar);

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2.setOnClickListener((view) -> {
            dialogoNuevaObra = new NuevaObraDialogo();
            dialogoNuevaObra.show(getSupportFragmentManager(),"DialogoNuevaObra");
            Snackbar.make(view, "Diálogo nueva obra.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });


 /*      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
         fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo = new NuevaObraDialogo();
                dialogo.show(getSupportFragmentManager(),"DialogoNueva");
                Snackbar.make(view, "Diálogo nueva obra", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
 */
        // Instanciamos el método de Realm getDefaultInstance
        realm = Realm.getDefaultInstance();

        // Rescatamos el contenedor y le cargamos un fragmento
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.containerObras, new ListadoObrasFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_obra, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_regresar) {
            Toast.makeText(this, "Regresa a la pantalla anterior", Toast.LENGTH_SHORT).show();
            onBackPressed();
            return true;
/*        }
        if (id == R.id.action_compartir) {
            Toast.makeText(this, "Se presionó el icono de compartir", Toast.LENGTH_LONG).show();
            return true;
        }        return super.onOptionsItemSelected(item);
*/    }


    @Override
    public void OnObraClick(ObraDB obraDB) {
        Intent i = new Intent(this, InformeObraActivity.class);
        i.putExtra(ObraDB.OBRADB_ID, obraDB.getId());
        startActivity(i);
    }

    @Override
    public void OnObraEdit(ObraDB mItem) {
        Toast.makeText(this, "Editar esta obra", Toast.LENGTH_SHORT).show();
        dialogEditObra = EditObraDialogFragment.newInstance(mItem.getId(), mItem.getPromotor(),
                mItem.getDireccion(), mItem.getTelefono(), mItem.getTitulo(), mItem.getTipoObra(),
                mItem.getReferencia(), mItem.getFechaInicio(), mItem.getFechaFinal());
        dialogEditObra.show(getSupportFragmentManager(),"EditObraDialogo");
    }


    @Override
    public void onObraGuardarClickListener(String promotor, String direccion, String telefono,
                                           String titulo, String tipoObra, String referencia,
                                           String fechaInicio, String fechaFinal) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ObraDB nuevaObra = new ObraDB();
                nuevaObra.setPromotor(promotor);
                nuevaObra.setDireccion(direccion);
                nuevaObra.setTelefono(telefono);
                nuevaObra.setTitulo(titulo);
                nuevaObra.setTipoObra(tipoObra);
                nuevaObra.setReferencia(referencia);
                nuevaObra.setFechaInicio(fechaInicio);
                nuevaObra.setFechaFinal(fechaFinal);

                realm.copyToRealm(nuevaObra);
            }
        });
    }

    @Override
    public void onObraActualizarClickListener(long id, String promotor, String direccion,
                                              String telefono, String titulo, String tipoObra,
                                              String referencia, String fechaInicio,
                                              String fechaFinal) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ObraDB nuevaObra = new ObraDB();
                nuevaObra.setId(id);
                nuevaObra.setPromotor(promotor);
                nuevaObra.setDireccion(direccion);
                nuevaObra.setTelefono(telefono);
                nuevaObra.setTitulo(titulo);
                nuevaObra.setTipoObra(tipoObra);
                nuevaObra.setReferencia(referencia);
                nuevaObra.setFechaInicio(fechaInicio);
                nuevaObra.setFechaFinal(fechaFinal);

                realm.copyToRealmOrUpdate(nuevaObra);
            }
        });
    }

    @Override
    public void OnObraEliminar(ObraDB mItem) { mostrarDialogoConfirmacion(mItem); }

    private void mostrarDialogoConfirmacion(final ObraDB obraDB) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Añadimos titulo y mensaje de diálogo con el usuario
        builder.setTitle("Eliminar Obra");
        builder.setMessage("¿Desea eliminar definitivamente la Obra del promotor "
                + obraDB.getPromotor()+" ?");
        // Add the buttons
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        long idObraEliminar = obraDB.getId();
                        ObraDB obraEliminar = realm.where(ObraDB.class).equalTo(ObraDB.OBRADB_ID,
                              idObraEliminar).findFirst();
                        obraEliminar.deleteFromRealm();
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

}


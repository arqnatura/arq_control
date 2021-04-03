package com.arq_control.ui.visitas;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.arq_control.R;

import io.realm.Realm;

public class NuevaVisitaDialogo extends DialogFragment {

    AlertDialog.Builder builder;
    OnNuevaVisitaListener mListenerB;
    View v;

    long idObra;
    Realm realm;

    EditText editTextTituloVisita, editTextFecha, editTextVisitas,
            editTextDescripcion, editTextAlmacenFoto;
    Context ctx;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        builder = new AlertDialog.Builder(getActivity());
        ctx = getActivity();
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        v =inflater.inflate(R.layout.dialogo_nueva_visita, null);

        editTextTituloVisita = (EditText)  v.findViewById(R.id.editTextTituloVisita);
        editTextFecha = (EditText)  v.findViewById(R.id.editTextFecha);
        editTextVisitas = (EditText)  v.findViewById(R.id.editTextNumeroVisita);
        editTextDescripcion= (EditText)  v.findViewById(R.id.editTextDescripcion);
//        editTextAlmacenFoto = (EditText)  v.findViewById(R.id.editTextAlmacenFoto);
        builder.setView(v);

        builder.setTitle("Formulario Nueva Visita");
        builder.setPositiveButton("Guardar", (dialog, id) -> {
            String tituloVisita = editTextTituloVisita.getText().toString();
            String fecha = editTextFecha.getText().toString();

//            String almacenFoto = editTextAlmacenFoto.getText().toString();
            // Evitamos que el campo Visitas Previstas quede vacío.
            long numeroVisita;
            if (!editTextVisitas.getText().toString().isEmpty()) {
                numeroVisita = Long.parseLong(editTextVisitas.getText().toString());
            } else {
                numeroVisita = 1;
            }
            String descripcion = editTextDescripcion.getText().toString();
            

            if (!tituloVisita.isEmpty() && !fecha.isEmpty()) {
                mListenerB.onVisitaGuardarClickListener(tituloVisita, fecha, numeroVisita,
                        descripcion);

                Toast.makeText(getActivity(), "Visita guardada", Toast.LENGTH_SHORT).show();
                // Cierra el diálogo
                dialog.dismiss();
            } else {
                Toast.makeText(ctx, "Introduzca los datos mínimos: Título," +
                        " y Fecha  de la visita.", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancelar", (dialog, id) -> {
            // Cancelar > cerrar el cuadro de diálogo
            dialog.dismiss();
        });
        return builder.create();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verifique que el activity del host implemente la interfaz de devolución de llamada
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListenerB = (OnNuevaVisitaListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement OnNuevaVisitaListener");
        }


    }

}
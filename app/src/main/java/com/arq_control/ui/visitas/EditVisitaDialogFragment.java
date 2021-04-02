package com.arq_control.ui.visitas;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.arq_control.R;
import com.arq_control.models.VisitaDB;

public class EditVisitaDialogFragment extends DialogFragment {

    AlertDialog.Builder builder;
    OnNuevaVisitaListener mListener;
    View v;

    private long idVisita;
    private long numeroVisita;
    private String tituloVisita, fecha, descripcion, almacenFoto;
    EditText editTextTituloVisita, editTextFecha, editTextVisitas,
            editTextDescripcion, editTextAlmacenFoto;
    Context ctx;

    public EditVisitaDialogFragment() {
        // Required empty public constructor
    }

    public static EditVisitaDialogFragment newInstance(long id, String t, String f, long v,
                                                       String d) {
        EditVisitaDialogFragment fragment = new EditVisitaDialogFragment();
        Bundle args = new Bundle();
        args.putLong(VisitaDB.VISITADB_ID, id);
        args.putString(VisitaDB.VISITADB_TITULO, t);
        args.putString(VisitaDB.VISITADB_FECHA, f);
        args.putString(VisitaDB.VISITADB_NUMEROVISITA, String.valueOf(v));
        args.putString(VisitaDB.VISITADB_DESCRIPCION, d);
//        args.putString(VisitaDB.VISITADB_ALMACENFOTO, a);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idVisita = getArguments().getLong(VisitaDB.VISITADB_ID);
            tituloVisita = getArguments().getString(VisitaDB.VISITADB_TITULO);
            fecha = getArguments().getString(VisitaDB.VISITADB_FECHA);
            numeroVisita = Long.parseLong(getArguments().getString(VisitaDB.VISITADB_NUMEROVISITA));
            descripcion = getArguments().getString(VisitaDB.VISITADB_DESCRIPCION);
 //           almacenFoto = getArguments().getString(VisitaDB.VISITADB_ALMACENFOTO);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());

        ctx = getActivity();

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        v =inflater.inflate(R.layout.dialogo_nueva_visita, null);
        editTextTituloVisita = v.findViewById(R.id.editTextTituloVisita);
        editTextFecha = v.findViewById(R.id.editTextFecha);
        editTextVisitas = v.findViewById(R.id.editTextNumeroVisita);
        editTextDescripcion= v.findViewById(R.id.editTextDescripcion);
//        editTextAlmacenFoto = (EditText)  v.findViewById(R.id.editTextAlmacenFoto);

        // Precargamos el formulario
        editTextTituloVisita.setText(tituloVisita);
        editTextFecha.setText(fecha);
        editTextVisitas.setText(Long.toString((int) numeroVisita));
        editTextDescripcion.setText(descripcion);
//        editTextAlmacenFoto.setText(almacenFoto);


        builder.setView(v);

        builder.setTitle("Editar Visita");
        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                String tituloVisita = editTextTituloVisita.getText().toString();
                String fecha = editTextFecha.getText().toString();

//                    String almacenFoto = editTextAlmacenFoto.getText().toString();
                //long visitas = Long.parseLong(editTextVisitas.getText().toString());
                long numeroVisita = Long.parseLong(editTextVisitas.getText().toString());

                String descripcion = editTextDescripcion.getText().toString();

                if (!tituloVisita.isEmpty() && !fecha.isEmpty()) {
                    mListener.onVisitaGuardarClickListener(tituloVisita, fecha, numeroVisita,
                            descripcion);

                    Toast.makeText(getActivity(), "Visita editada", Toast.LENGTH_SHORT).show();
                    // Cierra el diálogo
                    dialog.dismiss();
                } else {
                    Toast.makeText(ctx, "Introduzca los datos mínimos: Título," +
                            " y Fecha  de la visita.", Toast.LENGTH_LONG).show();
                }
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
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (OnNuevaVisitaListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement OnNuevaObraListener");
        }
    }

}
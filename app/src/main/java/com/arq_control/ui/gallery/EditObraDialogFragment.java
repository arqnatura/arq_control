package com.arq_control.ui.gallery;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.arq_control.R;
import com.arq_control.models.ObraDB;

public class EditObraDialogFragment extends DialogFragment {

    private long idObra;
    AlertDialog.Builder builder;
    OnObraInteractionListener mListener;
    View v;
    EditText editTextPromotor, editTextDireccion,  editTextPhone, editTextTitulo, editTextTipoObra,
            editTextReferencia, editTextVisitas, editTextFechaInicio, editTextFechaFinal;
    Context ctx;

    public EditObraDialogFragment() {
        // Required empty public constructor
    }

    public static EditObraDialogFragment newInstance(long idB) {
        EditObraDialogFragment fragment = new EditObraDialogFragment();
        Bundle args = new Bundle();
        args.putLong(ObraDB.OBRADB_ID, idB);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idObra = getArguments().getLong(ObraDB.OBRADB_ID);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());

        ctx = getActivity();

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        v =inflater.inflate(R.layout.dialogo_nueva_obra, null);
        editTextPromotor = (EditText)  v.findViewById(R.id.editTextPromotor);
        editTextDireccion = (EditText)  v.findViewById(R.id.editTextDireccion);
        editTextPhone = (EditText)  v.findViewById(R.id.editTextPhone);
        editTextTitulo = (EditText)  v.findViewById(R.id.editTextTitulo);
        editTextTipoObra = (EditText)  v.findViewById(R.id.editTextTipoObra);
        editTextReferencia = (EditText)  v.findViewById(R.id.editTextReferencia);
        editTextVisitas = (EditText)  v.findViewById(R.id.editTextVisitas);
        editTextFechaInicio = (EditText)  v.findViewById(R.id.editTextFechaInicio);
        editTextFechaFinal = (EditText)  v.findViewById(R.id.editTextFechaFinal);
        builder.setView(v);

        builder.setTitle("Nueva Obra")
                .setPositiveButton("Guardar", (dialog, id) ->  {
                    Toast.makeText(getActivity(), "Obra guardada", Toast.LENGTH_SHORT).show();

                    String promotor = editTextPromotor.getText().toString();
                    String direccion = editTextDireccion.getText().toString();
                    String telefono = editTextPhone.getText().toString();
                    String titulo = editTextTitulo.getText().toString();
                    String tipoObra = editTextTipoObra.getText().toString();
                    String referencia = editTextReferencia.getText().toString();
                    long visitasPrevistas = Long.parseLong(editTextVisitas.getText().toString());
                    String fechaInicio = editTextFechaInicio.getText().toString();
                    String fechaFinal = editTextFechaFinal.getText().toString();

                    if(!promotor.isEmpty() && !titulo.isEmpty() && !referencia.isEmpty()
                            && !fechaInicio.isEmpty()){
                        mListener.onObraActualizarClickListener(idObra, promotor, direccion, telefono,
                                titulo, tipoObra, referencia, visitasPrevistas, fechaInicio, fechaFinal);
                        // Cierra el diálogo
                        dialog.dismiss();
                    } else {
                        Toast.makeText(ctx, "Introduzca los datos mínimos: Promotor, Título," +
                                " Referencia y Fecha de Inicio.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", (dialog, id) ->  {
                    // Cancelar > cerrar el cuadro de diálogo
                    dialog.dismiss();
                });
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (OnObraInteractionListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement OnObraActualizar");
        }
    }
}
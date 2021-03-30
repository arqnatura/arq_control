package com.arq_control.ui.gallery;

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
import com.arq_control.models.ObraDB;

public class EditObraDialogFragment extends DialogFragment {

    AlertDialog.Builder builder;
    OnNuevaObraListener mListener;
    View v;

    private long idObra;
    private String promotor, direccion, telefono, titulo, tipoObra,
            referencia, fechaInicio, fechaFinal;
    EditText editTextPromotor, editTextDireccion, editTextPhone, editTextTitulo, editTextTipoObra,
                editTextReferencia, editTextFechaInicio, editTextFechaFinal;
    Context ctx;

    public EditObraDialogFragment() {
        // Required empty public constructor
    }

    public static EditObraDialogFragment newInstance(long id, String p, String d, String te,
                                                     String ti, String to, String r,
                                                     String fi, String ff) {
        EditObraDialogFragment fragment = new EditObraDialogFragment();
        Bundle args = new Bundle();
        args.putLong(ObraDB.OBRADB_ID, id);
        args.putString(ObraDB.OBRADB_PROMOTOR, p);
        args.putString(ObraDB.OBRADB_DIRECCION, d);
        args.putString(ObraDB.OBRADB_TELEFONO, te);
        args.putString(ObraDB.OBRADB_TITULO, ti);
        args.putString(ObraDB.OBRADB_TIPOOBRA, to);
        args.putString(ObraDB.OBRADB_REFERENCIA, r);
        args.putString(ObraDB.OBRADB_FECHAINICIO, fi);
        args.putString(ObraDB.OBRADB_FECHAFINAL, ff);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idObra = getArguments().getLong(ObraDB.OBRADB_ID);
            promotor = getArguments().getString(ObraDB.OBRADB_PROMOTOR);
            direccion = getArguments().getString(ObraDB.OBRADB_DIRECCION);
            telefono = getArguments().getString(ObraDB.OBRADB_TELEFONO);
            titulo = getArguments().getString(ObraDB.OBRADB_TITULO);
            tipoObra = getArguments().getString(ObraDB.OBRADB_TIPOOBRA);
            referencia = getArguments().getString(ObraDB.OBRADB_REFERENCIA);
            fechaInicio = getArguments().getString(ObraDB.OBRADB_FECHAINICIO);
            fechaFinal = getArguments().getString(ObraDB.OBRADB_FECHAFINAL);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());

        ctx = getActivity();

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        v =inflater.inflate(R.layout.dialogo_nueva_obra, null);
        editTextPromotor = (EditText)  v.findViewById(R.id.editTextPromotor);
        editTextDireccion = (EditText)  v.findViewById(R.id.editTextDireccion);
        editTextPhone = (EditText)  v.findViewById(R.id.editTextPhone);
        editTextTitulo = (EditText)  v.findViewById(R.id.editTextTitulo);
        editTextTipoObra = (EditText)  v.findViewById(R.id.editTextTipoObra);
        editTextReferencia = (EditText)  v.findViewById(R.id.editTextReferencia);
        editTextFechaInicio = (EditText)  v.findViewById(R.id.editTextFechaInicio);
        editTextFechaFinal = (EditText)  v.findViewById(R.id.editTextFechaFinal);

        // Precargamos el formulario
        editTextPromotor.setText(promotor);
        editTextDireccion.setText(direccion);
        editTextPhone.setText(telefono);
        editTextTitulo.setText(titulo);
        editTextTipoObra.setText(tipoObra);
        editTextReferencia.setText(referencia);
        editTextFechaInicio.setText(fechaInicio);
        editTextFechaFinal.setText(fechaFinal);

        builder.setView(v);

        builder.setTitle("Editar Obra")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    String promotor = editTextPromotor.getText().toString();
                    String direccion = editTextDireccion.getText().toString();
                    String telefono = editTextPhone.getText().toString();
                    String titulo = editTextTitulo.getText().toString();
                    String tipoObra = editTextTipoObra.getText().toString();
                    String referencia = editTextReferencia.getText().toString();
                    /*            //long visitas = Long.parseLong(editTextVisitas.getText().toString());
                                        long visitas;
                                        if(!editTextVisitas.getText().toString().isEmpty()){
                                            visitas = Long.parseLong(editTextVisitas.getText().toString());
                                        }else{
                                            visitas = 0;
                                        }

                     */
                    String fechaInicio = editTextFechaInicio.getText().toString();
                    String fechaFinal = editTextFechaFinal.getText().toString();


                    if(!promotor.isEmpty() && !titulo.isEmpty() && !referencia.isEmpty()
                            && !fechaInicio.isEmpty()){
                        mListener.onObraActualizarClickListener(idObra, promotor, direccion,
                                telefono, titulo, tipoObra, referencia, fechaInicio, fechaFinal);

                        Toast.makeText(getActivity(), "Obra editada", Toast.LENGTH_SHORT).show();
                        // Cierra el diálogo
                        dialog.dismiss();
                    } else {
                        Toast.makeText(ctx, "Introduzca los datos mínimos: Promotor, Título," +
                                " Referencia y Fecha de Inicio.", Toast.LENGTH_SHORT).show();
                    }
                    }
                })
                .setNegativeButton("Cancelar", (dialog, id) ->  {
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
            mListener = (OnNuevaObraListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement OnNuevaObraListener");
        }
    }

}
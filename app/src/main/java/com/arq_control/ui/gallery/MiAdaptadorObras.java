package com.arq_control.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arq_control.R;
import com.arq_control.models.ObraDB;

import java.util.List;

public class MiAdaptadorObras extends ArrayAdapter<ObraDB> {
    Context ctx;
    int layoutTemplate;
    List<ObraDB> obraDBList;

    public MiAdaptadorObras(@NonNull Context context, @LayoutRes int resource, @NonNull List<ObraDB> objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.layoutTemplate = resource;
        this.obraDBList = objects;
    }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View v = LayoutInflater.from(ctx).inflate(layoutTemplate, parent, false);

            // Obtener la información del elemento de la lista que estoy iterando en este momento
            ObraDB elementoActual = obraDBList.get(position);

            // Rescatar los elementos de la interfaz de la plantilla
            TextView textViewTitulo = (TextView) v.findViewById(R.id.textViewTitulo);
            TextView textViewTipoObra = (TextView) v.findViewById(R.id.textViewTipo);
            TextView textViewVisita = (TextView) v.findViewById(R.id.textViewVisitas);
            TextView textViewPromotor = (TextView) v.findViewById(R.id.textViewPromotor);
            TextView textViewReferencia = (TextView) v.findViewById(R.id.textViewReferencia);

            // Hacer un set del elemento actual en los elementos de la IU
            textViewTitulo.setText(elementoActual.getTitulo());
            textViewTipoObra.setText(elementoActual.getTipoObra());
            textViewVisita.setText(elementoActual.getNumeroVisitas()+" visitas");
            textViewPromotor.setText(elementoActual.getPromotor());
            textViewReferencia.setText(elementoActual.getReferencia());

            return v;
    }
}

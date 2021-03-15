package com.arq_control.ui.obras;

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
import com.arq_control.ui.gallery.Obra;

import java.util.List;

public class MiAdaptadorObras extends ArrayAdapter<Obra> {
    Context ctx;
    int layoutTemplate;
    List<Obra> obraList;

    public MiAdaptadorObras(@NonNull Context context, @LayoutRes int resource, @NonNull List<Obra> objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.layoutTemplate = resource;
        this.obraList = objects;
    }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View v = LayoutInflater.from(ctx).inflate(layoutTemplate, parent, false);

            // Obtener la información del elemento de la lista que estoy iterando en este momento
            Obra elementoActual = obraList.get(position);

            // Rescatar los elementos de la interfaz de la plantilla
            TextView textViewTitulo = (TextView) v.findViewById(R.id.textViewTitulo);
            TextView textViewTipoObra = (TextView) v.findViewById(R.id.textViewTipo);
            TextView textViewVisita = (TextView) v.findViewById(R.id.textViewVisitas);

            // Hacer un set del elemento actual en los elementos de la IU
            textViewTitulo.setText(elementoActual.getTitulo());
            textViewTipoObra.setText(elementoActual.getTipoObra());
            textViewVisita.setText(elementoActual.getNumeroVisitas()+" visitas");

            return v;
    }
}

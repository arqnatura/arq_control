package com.arq_control.ui.visitas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.arq_control.R;
import com.arq_control.models.VisitaDB;
import com.bumptech.glide.Glide;

import io.realm.OrderedRealmCollection;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;


public class MyVisitaRecyclerViewAdapter
        extends RecyclerView.Adapter<MyVisitaRecyclerViewAdapter.ViewHolder> {

    private final RealmList<VisitaDB> mValues;
    private final OnVisitaInteractionListener mListener;
    private final Context ctx;
    private RealmChangeListener listenerRefresco;

    public MyVisitaRecyclerViewAdapter(Context context, RealmList<VisitaDB> items,
                                       OnVisitaInteractionListener listener) {
        ctx = context;
        mValues = items;
        mListener = listener;
        this.listenerRefresco = (RealmChangeListener) (results) -> {
                notifyDataSetChanged();
        };
        if (items != null) {
            addListener(items);
        }
    }

    private void addListener(OrderedRealmCollection<VisitaDB> items) {
        if (items instanceof RealmResults){
            RealmResults realmResults = (RealmResults) items;
            realmResults.addChangeListener(listenerRefresco);
        }else if (items instanceof RealmList){
            RealmList<VisitaDB> list = (RealmList<VisitaDB>) items;
            list.addChangeListener((RealmChangeListener) listenerRefresco);
        }else {
            throw new IllegalArgumentException("RealmCollection not supported: " + items.getClass());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.visita_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewTitulo.setText(holder.mItem.getTituloVisita());
        holder.textViewFecha.setText(holder.mItem.getFecha());
//        holder.textViewVisitas.setText((int) holder.mItem.getNumeroVisita());
        holder.textViewVisitas.setText(holder.mItem.getNumeroVisita()+" Visita/s");

        // Cargamos la imagen en el imageView del CardView.
        if(!holder.mItem.getTituloVisita().isEmpty()) {
            Glide.with(ctx)
                    .load(holder.mItem.getAlmacenFoto())
                    .into(holder.imageViewCamara);
        }
        //Activamos el evento clic que activa el informe de visita seleccionado.
        holder.mView.setOnClickListener((v) -> {
            if (null != mListener){
                mListener.OnVisitaClick(holder.mItem);
            }
        });

        //Definimos el evento de editar una visita
        holder.imageViewEditarVisita.setOnClickListener((v) ->  {
            if (null != mListener){
                // Notifique a la interfaz la devolucion de la llamada que se ha
                // seleccionado un elemento (si el fragment está adjunto a un activity) .
                mListener.OnVisitaEdit(holder.mItem);
            }
        });

        //Definimos el evento de eliminar una obra
        holder.imageViewEliminarVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.OnVisitaEliminar(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() { return mValues.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView textViewTitulo;
        public final TextView textViewVisitas;
        public final TextView textViewFecha;
        public final ImageView imageViewCamara;
        public final ImageView imageViewEditarVisita;
        public final ImageView imageViewEliminarVisita;

        public VisitaDB mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageViewCamara = (ImageView) view.findViewById(R.id.imageViewCamara);
            textViewTitulo = (TextView) view.findViewById(R.id.textViewTitulo);
            textViewVisitas = (TextView) view.findViewById(R.id.textViewVisitas);
            textViewFecha = (TextView) view.findViewById(R.id.textViewFecha);
            imageViewEditarVisita = (ImageView) view.findViewById(R.id.imageViewEditarVisita);
            imageViewEliminarVisita = (ImageView) view.findViewById(R.id.imageViewEliminarVisita);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewTitulo.getText() + "'";
        }
    }

}
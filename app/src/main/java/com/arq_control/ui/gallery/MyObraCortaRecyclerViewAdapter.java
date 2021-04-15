package com.arq_control.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.arq_control.R;
import com.arq_control.models.ObraDB;

import io.realm.OrderedRealmCollection;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;


public class MyObraCortaRecyclerViewAdapter
        extends RecyclerView.Adapter<MyObraCortaRecyclerViewAdapter.ViewHolder> {

    private final RealmResults<ObraDB> mValues;
    private final OnObraInteractionListener mListener;
    private final Context ctx;
    private RealmChangeListener listenerRefresco;

    public MyObraCortaRecyclerViewAdapter(Context context, RealmResults<ObraDB> items,
                                          OnObraInteractionListener listener) {
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

    private void addListener(OrderedRealmCollection<ObraDB> items) {
        if (items instanceof RealmResults){
            RealmResults realmResults = (RealmResults) items;
            realmResults.addChangeListener(listenerRefresco);
        }else if (items instanceof RealmList){
            RealmList<ObraDB> list = (RealmList<ObraDB>) items;
            list.addChangeListener((RealmChangeListener) listenerRefresco);
        }else {
            throw new IllegalArgumentException("RealmCollection not supported: " + items.getClass());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.obra_item_corta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.textViewPromotor.setText(holder.mItem.getPromotor());
        holder.textViewTitulo.setText(holder.mItem.getTitulo());
        holder.textViewTipo.setText(holder.mItem.getTipoObra());
        holder.textViewTitulo.setText(holder.mItem.getTitulo());
        holder.textViewReferencia.setText(holder.mItem.getReferencia());
    }

    @Override
    public int getItemCount() { return mValues.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView textViewPromotor;
        public final TextView textViewTitulo;
        public final TextView textViewTipo;
        public final TextView textViewReferencia;

        public ObraDB mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewPromotor = (TextView) view.findViewById(R.id.textViewPromotor);
            textViewTitulo = (TextView) view.findViewById(R.id.textViewTitulo);
            textViewTipo = (TextView) view.findViewById(R.id.textViewTipo);
            textViewReferencia = (TextView) view.findViewById(R.id.textViewReferencia);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewPromotor.getText() + "'";
        }
    }





}
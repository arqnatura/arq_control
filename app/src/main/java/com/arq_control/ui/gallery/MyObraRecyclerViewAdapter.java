package com.arq_control.ui.gallery;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arq_control.R;
import com.arq_control.models.ObraDB;
import com.bumptech.glide.Glide;

import java.util.List;


public class MyObraRecyclerViewAdapter extends RecyclerView.Adapter<MyObraRecyclerViewAdapter.ViewHolder> {

    private final List<ObraDB> mValues;
    private OnObraInteractionListener mListener;
    private Context ctx;

    public MyObraRecyclerViewAdapter(Context context, List<ObraDB> items, OnObraInteractionListener listener) {
        ctx = context;
        mValues = items;
        mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewPromotor;
        public final TextView textViewTitulo;
        public final TextView textViewTipo;
        public final TextView textViewVisitas;
        public final ImageView imageViewCamara;
        public final TextView textViewReferencia;
        public ObraDB mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewPromotor = (TextView) view.findViewById(R.id.textViewPromotor);
            textViewTitulo = (TextView) view.findViewById(R.id.textViewTitulo);
            textViewTipo = (TextView) view.findViewById(R.id.textViewTipo);
            textViewVisitas = (TextView) view.findViewById(R.id.textViewVisitas);
            imageViewCamara = (ImageView) view.findViewById(R.id.imageViewCamara);
            textViewReferencia = (TextView) view.findViewById(R.id.textViewReferencia);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewTitulo.getText() + "'";
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.obra_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewPromotor.setText(holder.mItem.getPromotor());
        holder.textViewTitulo.setText(holder.mItem.getTitulo());
        holder.textViewTipo.setText(holder.mItem.getTipoObra());
        holder.textViewVisitas.setText(holder.mItem.getNumeroVisitas()+" visitas");
        holder.textViewReferencia.setText(holder.mItem.getReferencia());

        Glide.with(ctx)
                .load(holder.mItem.getAlmacenFoto())
                .into(holder.imageViewCamara);

        holder.mView.setOnClickListener((v) -> {
            if (null != mListener){
                mListener.OnObraClick(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

}
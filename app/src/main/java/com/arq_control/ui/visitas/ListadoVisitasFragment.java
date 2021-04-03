package com.arq_control.ui.visitas;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arq_control.R;
import com.arq_control.models.ObraDB;
import com.arq_control.models.VisitaDB;

import io.realm.Realm;
import io.realm.RealmList;

public class ListadoVisitasFragment extends Fragment {

    OnVisitaInteractionListener mListener;
    RealmList<VisitaDB> visitasDBList;

    VisitaDB visitaDB;
    ObraDB obraDB;
    long idObra, idVisita;

    Realm realm;

    public ListadoVisitasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_visitas_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            // Rescatamos el ID de la visita.
            Bundle extras = getActivity().getIntent().getExtras();
            idObra = extras.getLong("obraId");
            // Tabla sobre la que queremos ejecutar la consulta VisitaDB.class
            // Buscamos el primer elemento que concuerde con la consulta.
            obraDB = realm.where(ObraDB.class)
                    .equalTo(ObraDB.OBRADB_ID, idObra)
                    .findFirst();

            // Hacemos la consulta a la DB y obtenemos todos los registros.
//            visitasDBList = realm.where(VisitaDB.class)
            visitasDBList = obraDB.visitas;

            recyclerView.setAdapter(new MyVisitaRecyclerViewAdapter(getActivity(), visitasDBList,
                    mListener));
        }
        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnVisitaInteractionListener) {
            mListener = (OnVisitaInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnVisitaInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



}
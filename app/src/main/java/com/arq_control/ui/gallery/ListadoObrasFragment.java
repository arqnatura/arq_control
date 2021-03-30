package com.arq_control.ui.gallery;

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

import io.realm.Realm;
import io.realm.RealmResults;

public class ListadoObrasFragment extends Fragment {
    OnObraInteractionListener mListener;
    RealmResults<ObraDB> obrasDBList;
    Realm realm;

    public ListadoObrasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            // Hacemos la consulta a la DB y obtenemos todos los registros.
            obrasDBList = realm.where(ObraDB.class).findAll();
            recyclerView.setAdapter(new MyObraRecyclerViewAdapter(getActivity(), obrasDBList,
                    mListener));
        }
        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnObraInteractionListener) {
            mListener = (OnObraInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnObraInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
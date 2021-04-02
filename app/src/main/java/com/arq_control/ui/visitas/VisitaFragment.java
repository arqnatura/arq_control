package com.arq_control.ui.visitas;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arq_control.R;
import com.arq_control.models.VisitaDB;

import io.realm.Realm;
import io.realm.RealmResults;

public class VisitaFragment extends Fragment {
    OnVisitaInteractionListener mListener;
    RealmResults<VisitaDB> visitaDBList;
    Realm realm;

    private VisitaViewModel visitaViewModel;

    public VisitaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        visitaViewModel = new ViewModelProvider(this).get(VisitaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_visitas_list, container, false);


        visitaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Context context = root.getContext();
                RecyclerView recyclerView = (RecyclerView) root;
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                // Hacemos la consulta a la DB y obtenemos todos los registros.
                RealmResults<VisitaDB> result = realm.where(VisitaDB.class).findAll();

                recyclerView.setAdapter(new MyVisitaRecyclerViewAdapter(getActivity(), result,
                        mListener));
            }
        });
        return root;
    }

}
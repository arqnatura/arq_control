package com.arq_control.ui.finalizadas;

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
import com.arq_control.models.ObraDB;
import com.arq_control.ui.gallery.MyObraCortaRecyclerViewAdapter;
import com.arq_control.ui.gallery.OnObraInteractionListener;

import io.realm.Realm;
import io.realm.RealmResults;

public class FinalizadasFragment extends Fragment {
    OnObraInteractionListener mListener;
    RealmResults<ObraDB> obrasDBList;
    Realm realm;

    private FinalizadasViewModel finalizadasViewModel;

    public FinalizadasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        finalizadasViewModel = new ViewModelProvider(this).get(FinalizadasViewModel.class);
        View view = inflater.inflate(R.layout.fragment_gallery_list, container, false);

        finalizadasViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Context context = view.getContext();
                RecyclerView recyclerView = (RecyclerView) view;
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                // Hacemos la consulta a la DB y obtenemos todos los registros.
                RealmResults<ObraDB> result = realm.where(ObraDB.class)
                    // Add query conditions:
                    .not()
                    .equalTo(ObraDB.OBRADB_FECHAFINAL, "")
                    // Execute the query:
                    .findAll();

                recyclerView.setAdapter(new MyObraCortaRecyclerViewAdapter(getActivity(), result,
                        mListener));
            }
        });
        return view;
    }

}
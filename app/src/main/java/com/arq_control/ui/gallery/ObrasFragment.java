package com.arq_control.ui.gallery;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arq_control.R;
import com.arq_control.models.ObraDB;

import java.util.ArrayList;
import java.util.List;

public class ObrasFragment extends Fragment {
    OnObraInteractionListener mListener;
    List<ObraDB> obrasList;

    public ObrasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

            // Lista de Obras
            obrasList = new ArrayList<>();
            obrasList = new ArrayList<>();
            obrasList.add(new ObraDB("Souto de Moura","Viv. Unifamiliar", "ObraDB nueva", "https://tinyurl.com/yer7qpnq", 16, "VIV_03"));
            obrasList.add(new ObraDB("Alejandro De La Sota","Viv. Plurifamiliar", "Rehabilitación", "E:\\TALIARTE\\Mapa Radon Canarias_00.JPG", 10, "VIV_05"));
            obrasList.add(new ObraDB("Zaha Hadid","Vivienda Duplex", "Reforma", "https://tinyurl.com/yf8elgmk", 5, "VIV_18"));
            obrasList.add(new ObraDB("Mies Van Der Rohe","Viv. Unifamiliar", "Ampliación", "https://tinyurl.com/yzvfufjk", 8, "VIV_25"));

            recyclerView.setAdapter(new MyObraRecyclerViewAdapter(getActivity(), obrasList, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
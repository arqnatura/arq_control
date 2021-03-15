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

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {
    OnObraInteractionListener mListener;
    List<Obra> obrasList;

    public GalleryFragment() {
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
            obrasList.add(new Obra("Souto de Moura","Viv. Unifamiliar", "Obra nueva", "https://tinyurl.com/yer7qpnq", 16));
            obrasList.add(new Obra("Alejandro De La Sota","Viv. Plurifamiliar", "Rehabilitación", "E://TALIARTE//Mapa Radon Canarias_00.JPG", 10));
            obrasList.add(new Obra("Zaha Hadid","Vivienda Duplex", "Reforma", "https://tinyurl.com/yf8elgmk", 5));
            obrasList.add(new Obra("Mies Van Der Rohe","Viv. Unifamiliar", "Ampliación", "https://tinyurl.com/yzvfufjk", 8));

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
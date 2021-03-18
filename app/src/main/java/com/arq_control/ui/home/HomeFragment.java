package com.arq_control.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.arq_control.R;
import com.arq_control.ui.Obras.NuevaObraDialogo;
import com.arq_control.ui.finalizadas.FinalizadasFragment;
import com.arq_control.ui.gallery.ObrasFragment;
import com.arq_control.ui.operadores.OperadoresFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.textViewVersion);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

 /*   public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonPlus:
                cargarFragment(new ObrasFragment());
                break;
            case R.id.buttonCurso:
                cargarFragment(new FinalizadasFragment());
                break;
            case R.id.buttonArchivo:
                cargarFragment(new NuevaObraDialogo());
                break;
            case R.id.buttonAgenda:
                cargarFragment(new OperadoresFragment());
                break;
        }

    }

    private void cargarFragment(Fragment fragment) {
        FragmentTransaction ft =  getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mobile_navigation, fragment).addToBackStack(null).commit();
    }*/
}
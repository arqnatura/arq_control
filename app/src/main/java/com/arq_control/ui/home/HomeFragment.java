package com.arq_control.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.arq_control.R;
import com.arq_control.ui.finalizadas.FinalizadasFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

    }

    public void onClickListener(View v) {
        switch (v.getId()){
/*            case R.id.buttonPlus:
                cargarFragment(new FinalizadasFragment());
                break;
            case R.id.buttonCurso:
                cargarFragment(new FinalizadasFragment());
                break;
 */           case R.id.buttonArchivo:
                Toast.makeText(getActivity(), "Ver Listado de Obras Finalizadas...",
                        Toast.LENGTH_SHORT).show();
                cargarFragment(new FinalizadasFragment());
                break;
  /*           case R.id.buttonAgenda:
                cargarFragment(new OperadoresFragment());
                break;
  */
        }
    }

    private void cargarFragment(Fragment fragment) {
        FragmentTransaction ft =  getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.nav_finalizadas, fragment).addToBackStack(null).commit();
    }
}
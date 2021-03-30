package com.arq_control.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.arq_control.R;

public class ObraFragment extends Fragment {

    private ObraViewModel obraViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        obraViewModel = new ViewModelProvider(this).get(ObraViewModel.class);
        View root = inflater.inflate(R.layout.fragment_obra, container, false);
        final TextView textView = root.findViewById(R.id.TextViewObra);

        obraViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }


}
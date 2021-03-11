package com.arq_control.ui.obras;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arq_control.R;

public class OperadoresFragment extends Fragment {

    private OperadoresViewModel operadoresViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        operadoresViewModel = new ViewModelProvider(this).get(OperadoresViewModel.class);
        View root = inflater.inflate(R.layout.fragment_operadores, container, false);
        final TextView textView = root.findViewById(R.id.text_obras);

        operadoresViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
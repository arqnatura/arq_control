package com.arq_control.ui.finalizadas;

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

public class FinalizadasFragment extends Fragment {

    private FinalizadasViewModel finalizadasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        finalizadasViewModel =
                new ViewModelProvider(this).get(FinalizadasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_finalizadas, container, false);
        final TextView textView = root.findViewById(R.id.text_finalizadas);

        finalizadasViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
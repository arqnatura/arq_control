package com.arq_control.ui.operadores;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OperadoresViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public OperadoresViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Listado de operadores");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

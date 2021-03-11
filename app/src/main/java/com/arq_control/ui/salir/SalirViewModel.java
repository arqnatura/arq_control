package com.arq_control.ui.salir;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SalirViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SalirViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("Salir de la aplicación");
    }

    public LiveData<String> getText() {
        return mText;
    }
}


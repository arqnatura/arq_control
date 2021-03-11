package com.arq_control.ui.finalizadas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FinalizadasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FinalizadasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Galería de obras finalizadas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
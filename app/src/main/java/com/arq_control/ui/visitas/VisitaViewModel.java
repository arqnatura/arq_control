package com.arq_control.ui.visitas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VisitaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public VisitaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Listado de visitas en Curso");
    }

    public LiveData<String> getText() {
        return mText;
    }


}

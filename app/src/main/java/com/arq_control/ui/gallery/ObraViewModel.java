package com.arq_control.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ObraViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ObraViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Listado de obras en Curso");
    }

    public LiveData<String> getText() {
        return mText;
    }


}

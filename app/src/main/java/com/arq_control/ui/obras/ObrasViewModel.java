package com.arq_control.ui.obras;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ObrasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ObrasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("No has creado obras nuevas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

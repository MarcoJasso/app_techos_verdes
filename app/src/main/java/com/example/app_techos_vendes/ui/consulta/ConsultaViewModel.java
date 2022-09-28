package com.example.app_techos_vendes.ui.consulta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConsultaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ConsultaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
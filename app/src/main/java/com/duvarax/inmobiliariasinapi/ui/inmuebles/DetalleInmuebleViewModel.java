package com.duvarax.inmobiliariasinapi.ui.inmuebles;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

public class DetalleInmuebleViewModel extends AndroidViewModel {
    private Context context;
    public DetalleInmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    // TODO: Implement the ViewModel
}
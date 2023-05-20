package com.duvarax.inmobiliariasinapi.ui.inmuebles;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.duvarax.inmobiliariasinapi.modelo.Inmueble;
import com.duvarax.inmobiliariasinapi.request.ApiClient;
import com.google.android.gms.common.api.Api;

public class DetalleInmuebleViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Inmueble> inmuebleMutable;
    public DetalleInmuebleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inmueble> getInmueble(){
        if(inmuebleMutable == null){
            inmuebleMutable = new MutableLiveData<>();
        }
        return inmuebleMutable;
    }

    public void setInmueble(Inmueble inmueble){
        inmuebleMutable.setValue(inmueble);
    }

    public void modificarEstadoInmueble(Boolean disponible){
        Inmueble inmueble = inmuebleMutable.getValue();
        inmueble.setEstado(disponible);
        ApiClient.getApi().actualizarInmueble(inmueble);
    }
    // TODO: Implement the ViewModel
}
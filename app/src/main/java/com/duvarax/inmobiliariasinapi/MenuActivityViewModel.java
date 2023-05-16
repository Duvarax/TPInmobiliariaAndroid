package com.duvarax.inmobiliariasinapi;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.duvarax.inmobiliariasinapi.modelo.Propietario;
import com.duvarax.inmobiliariasinapi.request.ApiClient;

public class MenuActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Propietario> propietarioMutable;
    public MenuActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Propietario> getPropietario(){
        if(propietarioMutable == null){
            propietarioMutable = new MutableLiveData<>();
        }
        return propietarioMutable;
    }

    public void setPropietarioMutable(){
        propietarioMutable.setValue(ApiClient.getApi().obtenerUsuarioActual());
    }

}

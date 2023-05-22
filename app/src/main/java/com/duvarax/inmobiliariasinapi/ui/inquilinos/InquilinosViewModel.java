package com.duvarax.inmobiliariasinapi.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.duvarax.inmobiliariasinapi.modelo.Inmueble;
import com.duvarax.inmobiliariasinapi.request.ApiClient;

import java.util.ArrayList;
import java.util.List;


public class InquilinosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Inmueble>> listaInmueblesMutable;

    public InquilinosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<List<Inmueble>> setInmuebles(){
        if(listaInmueblesMutable == null){
            listaInmueblesMutable = new MutableLiveData<>();
        }
        return listaInmueblesMutable;
    }

    public void setListaInmueblesMutable(){
        ArrayList<Inmueble> listaInmuebles = ApiClient.getApi().obtenerPropiedadesAlquiladas();
        listaInmueblesMutable.setValue(listaInmuebles);

    }



    // TODO: Implement the ViewModel
}
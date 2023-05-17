package com.duvarax.inmobiliariasinapi.ui.perfil;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.duvarax.inmobiliariasinapi.modelo.Propietario;
import com.duvarax.inmobiliariasinapi.request.ApiClient;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> editarMutable;
    private MutableLiveData<Boolean> guardarMutable;

    private MutableLiveData<Propietario> propietarioMutable;
    private Context context;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }


    public LiveData<Propietario> getPropietario() {
        if(propietarioMutable == null){
            propietarioMutable = new MutableLiveData<>();
        }
        return propietarioMutable;
    }
    public LiveData<Boolean> getEditar() {
        if(editarMutable == null){
            editarMutable = new MutableLiveData<>();
        }
        return editarMutable;
    }
    public LiveData<Boolean> getGuardar() {
        if(guardarMutable == null){
            guardarMutable = new MutableLiveData<>();
        }
        return guardarMutable;
    }

    public void editarPropietario(Propietario propietario){
        ApiClient.getApi().actualizarPerfil(propietario);
        propietarioMutable.setValue(propietario);
    }

    public void setPropietarioMutable(){
        Propietario propietario = ApiClient.getApi().obtenerUsuarioActual();
        propietarioMutable.setValue(propietario);
    }

    public void setEditarMutable(){
        editarMutable.setValue(true);
    }
    public void setGuardarMutable(){
        guardarMutable.setValue(true);
    }
}
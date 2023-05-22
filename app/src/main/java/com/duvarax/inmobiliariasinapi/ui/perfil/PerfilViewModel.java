package com.duvarax.inmobiliariasinapi.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.duvarax.inmobiliariasinapi.modelo.Propietario;
import com.duvarax.inmobiliariasinapi.request.ApiClient;
import com.duvarax.inmobiliariasinapi.request.ApiClientRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClientRetrofit.EndPointInmobiliaria end = ApiClientRetrofit.getEndpointInmobiliaria();
        Call<Propietario> propietarioCall = end.obtenerPerfil(token);

        propietarioCall.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){

                        Propietario propietario = response.body();
                        propietarioMutable.setValue(propietario);
                    }
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("salida", t.getMessage());
                Toast.makeText(context, "Error en traer al propietario" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setEditarMutable(){
        editarMutable.setValue(true);
    }
    public void setGuardarMutable(){
        guardarMutable.setValue(true);
    }
}
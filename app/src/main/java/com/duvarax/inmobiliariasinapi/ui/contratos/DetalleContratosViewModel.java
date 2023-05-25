package com.duvarax.inmobiliariasinapi.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.duvarax.inmobiliariasinapi.modelo.Contrato;
import com.duvarax.inmobiliariasinapi.modelo.Inmueble;
import com.duvarax.inmobiliariasinapi.request.ApiClientRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetalleContratosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Contrato> contratoMutable;

    public DetalleContratosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<Contrato> getContrato(){
        if(contratoMutable == null){
            contratoMutable = new MutableLiveData<>();
        }
        return contratoMutable;
    }

    public void setContrato(Inmueble inmueble){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClientRetrofit.EndPointInmobiliaria end = ApiClientRetrofit.getEndpointInmobiliaria();
        Call<Contrato> contratoCall = end.obtenerContrato(token, inmueble);
        contratoCall.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        contratoMutable.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {

            }
        });
    }
    // TODO: Implement the ViewModel
}
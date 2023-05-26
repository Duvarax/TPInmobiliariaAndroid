package com.duvarax.inmobiliariasinapi.ui.contratos;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.duvarax.inmobiliariasinapi.modelo.Contrato;
import com.duvarax.inmobiliariasinapi.modelo.Pago;
import com.duvarax.inmobiliariasinapi.request.ApiClientRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Pago>> listaPagosMutable;
    public PagosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Pago>> getListaPagos(){
        if(listaPagosMutable == null){
            listaPagosMutable = new MutableLiveData<>();
        }
        return listaPagosMutable;
    }

    public void setListaPagos(Contrato contrato){
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClientRetrofit.EndPointInmobiliaria end = ApiClientRetrofit.getEndpointInmobiliaria();
        Call<List<Pago>> callPagos = end.obtenerPagos(token, contrato);
        List<Pago> listaPagos = new ArrayList<>();
        callPagos.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Log.d("salida pagos", response.body().size()+"");
                        for (Pago pago:response.body()) {
                            Pago pag = new Pago(pago.getid(), pago.getidentificadorPago(), pago.getImporte(), pago.getfechaPago(), pago.getcontratoId());
                            Log.d("salida pago", pago.getidentificadorPago()+ " " + pag.getidentificadorPago());
                            listaPagos.add(pag);
                        }
                        listaPagosMutable.setValue(listaPagos);
                    }
                }else{
                    Log.d("salida pagos", response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {

            }
        });

    }

    // TODO: Implement the ViewModel
}
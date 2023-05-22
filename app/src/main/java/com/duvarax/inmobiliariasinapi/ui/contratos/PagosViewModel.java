package com.duvarax.inmobiliariasinapi.ui.contratos;


import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.duvarax.inmobiliariasinapi.modelo.Contrato;
import com.duvarax.inmobiliariasinapi.modelo.Pago;
import com.duvarax.inmobiliariasinapi.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

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
        ArrayList<Pago> listaPagos = ApiClient.getApi().obtenerPagos(contrato);
        listaPagosMutable.setValue(listaPagos);
    }

    // TODO: Implement the ViewModel
}
package com.duvarax.inmobiliariasinapi;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.duvarax.inmobiliariasinapi.modelo.Propietario;
import com.duvarax.inmobiliariasinapi.request.ApiClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Boolean> logearMutable;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Boolean> getLogear(){
        if(logearMutable == null){
            logearMutable = new MutableLiveData<>();
        }
        return logearMutable;
    }

    public void logear(String email, String clave){
        Propietario propietario = ApiClient.getApi().login(email, clave);
        if(propietario != null){
            logearMutable.setValue(true);
        }else{
            Toast.makeText(context, "Error en el inicio de sesion", Toast.LENGTH_SHORT).show();
        }
    }




}

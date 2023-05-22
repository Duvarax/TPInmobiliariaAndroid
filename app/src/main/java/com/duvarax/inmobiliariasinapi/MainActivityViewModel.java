package com.duvarax.inmobiliariasinapi;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.duvarax.inmobiliariasinapi.modelo.Propietario;
import com.duvarax.inmobiliariasinapi.modelo.Usuario;
import com.duvarax.inmobiliariasinapi.request.ApiClient;
import com.duvarax.inmobiliariasinapi.request.ApiClientRetrofit;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Boolean> logearMutable;
    private MutableLiveData<LeeSensor> llamadaMutable;

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

    public LiveData<LeeSensor> getLlamada(){
        if(llamadaMutable == null){
            llamadaMutable = new MutableLiveData<>();
        }
        return llamadaMutable;
    }

    public void setLlamadaListener(){
        llamadaMutable.setValue(new LeeSensor(context));
    }

    public void logear(String email, String clave){
        Usuario user = new Usuario(email, clave);
        ApiClientRetrofit.EndPointInmobiliaria end = ApiClientRetrofit.getEndpointInmobiliaria();
        Call<String> tokenCall = end.login(user);
        tokenCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("salida", response.toString());
                if(response.isSuccessful()){

                    if(response.body() != null){
                        Log.d("salida", response.body());
                        SharedPreferences sp = context.getSharedPreferences("token.xml", 0);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("token", "Bearer " +  response.body());
                        editor.commit();
                        logearMutable.setValue(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("salida", t.getMessage());
                Toast.makeText(context, "Error en el login" , Toast.LENGTH_SHORT).show();
            }
        });
    }




}

package com.duvarax.inmobiliariasinapi.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.duvarax.inmobiliariasinapi.modelo.EditInmuebleEstado;
import com.duvarax.inmobiliariasinapi.modelo.Inmueble;
import com.duvarax.inmobiliariasinapi.request.ApiClientRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInmuebleViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Inmueble> inmuebleMutable;
    public DetalleInmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
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
        Log.d("salida", disponible+"");
        EditInmuebleEstado editInmueble = new EditInmuebleEstado(inmueble.getIdInmueble(), disponible);
        SharedPreferences sp = context.getSharedPreferences("token.xml", -1);
        String token = sp.getString("token", "");
        ApiClientRetrofit.EndPointInmobiliaria end = ApiClientRetrofit.getEndpointInmobiliaria();
        Call<Integer> callInmueble = end.editarEstadoInmueble(token, editInmueble);
        callInmueble.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Log.d("salida estado", response.body().toString());
                        Toast.makeText(context, "Estado modificado", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Log.d("salida", response.toString());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(context, "Error al modificar el estado", Toast.LENGTH_SHORT).show();
                Log.d("salida", t.getMessage());
            }
        });
    }
    // TODO: Implement the ViewModel
}
package com.duvarax.inmobiliariasinapi.request;

import com.duvarax.inmobiliariasinapi.modelo.Contrato;
import com.duvarax.inmobiliariasinapi.modelo.EditInmuebleEstado;
import com.duvarax.inmobiliariasinapi.modelo.EditPropietario;
import com.duvarax.inmobiliariasinapi.modelo.Inmueble;
import com.duvarax.inmobiliariasinapi.modelo.Inquilino;
import com.duvarax.inmobiliariasinapi.modelo.Pago;
import com.duvarax.inmobiliariasinapi.modelo.Propietario;
import com.duvarax.inmobiliariasinapi.modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public class ApiClientRetrofit {
    private static final String PATH="http://192.168.0.15:5200/api/";
    private static  EndPointInmobiliaria endPointInmobiliaria;

    public static EndPointInmobiliaria getEndpointInmobiliaria(){

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        endPointInmobiliaria=retrofit.create(EndPointInmobiliaria.class);

        return endPointInmobiliaria;
    }
    public interface EndPointInmobiliaria{

        @POST("Propietario/login")
        Call<String> login(@Body Usuario user);

        @GET("Propietario/perfil")
        Call<Propietario> obtenerPerfil(@Header("Authorization") String token);

        @PUT("Propietario/editar")
        Call<Propietario> editarPerfil(@Header("Authorization") String token, @Body EditPropietario propietario);

        @GET("Inmueble/propiedades")
        Call<List<Inmueble>> obtenerInmuebles(@Header("Authorization") String token);
        @PUT("Inmueble/estado")
        Call<Integer> editarEstadoInmueble(@Header("Authorization") String token, @Body EditInmuebleEstado edit);
        @GET("Inmueble/propiedades-alquiladas")
        Call<List<Inmueble>> obtenerInmueblesAlquilados(@Header("Authorization") String token);
        @POST("Inquilino/")
        Call<Inquilino> obtenerInquilino(@Header("Authorization") String token, @Body Inmueble inmueble);
        @POST("Contrato/")
        Call<Contrato> obtenerContrato(@Header("Authorization") String token, @Body Inmueble inmueble);
        @POST("Pago/")
        Call<List<Pago>> obtenerPagos(@Header("Authorization") String token, @Body Contrato contrato);

    }
}

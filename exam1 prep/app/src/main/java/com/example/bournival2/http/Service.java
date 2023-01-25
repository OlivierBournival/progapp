package com.example.bournival2.http;


import com.example.bournival2.transfer.Nombre;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {

    @GET("exam/representations/{nombre}")
    Call<String> repString(@Path("nombre") String nombre);

    @GET("exam/representations/{nombre}")
    Call<List<Nombre>> repNombre(@Path("nombre") String nombre);


}

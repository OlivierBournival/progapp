package com.example.recyclerretrofit.http;

import com.example.recyclerretrofit.transfer.SImple;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {

    @GET("long/list")
    Call<Long[]> listUn();

    @GET("truc/list")
    Call<SImple[]> list2Objet();
}

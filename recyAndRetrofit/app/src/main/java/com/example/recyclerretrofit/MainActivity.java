package com.example.recyclerretrofit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.recyclerretrofit.databinding.ActivityMainBinding;
import com.example.recyclerretrofit.http.RetrofitUtil;
import com.example.recyclerretrofit.http.Service;
import com.example.recyclerretrofit.transfer.SImple;
import com.example.recyclerretrofit.transfer.SImpleAdapteur;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    SImpleAdapteur adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // appeler un service de liste et afficher dans le log

        final Service service = RetrofitUtil.get();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                remplirRecycler();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }


    }
    private void remplacer(){ //start recy
        adapter.list.clear();

        adapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void remplirRecycler() throws ParseException {
        final Service service = RetrofitUtil.get();

        service.list2Objet().enqueue(new Callback<SImple[]>() {


            @Override
            public void onResponse(Call<SImple[]>  call, Response <SImple[]>  response) {
                if (response.isSuccessful()) {

                    adapter.list.addAll(Arrays.asList(response.body()));
                    adapter.notifyDataSetChanged();
                    // http 200 http tout s'est bien passé
                    Log.i("RETROFIT", response.body()+"");

                } else {
                    // cas d'erreur http 400 404
                    Log.i("RETROFIT", response.code()+"");
                }
            }

            @Override
            public void onFailure(Call<SImple[]>  call, Throwable t) {
                // erreur accès réseau ou alors GSON
                Log.i("RETROFIT", t.getMessage());
            }
        });



    }

    private void initRecycler(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        adapter = new SImpleAdapteur();
        recyclerView.setAdapter(adapter);
    }
}

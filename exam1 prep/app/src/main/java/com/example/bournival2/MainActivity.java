package com.example.bournival2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.bournival2.http.RetrofitUtil;
import com.example.bournival2.http.Service;
import com.example.bournival2.model.NombrAdapteur;
import com.example.bournival2.transfer.Nombre;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    NombrAdapteur adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initRecycler();

        final Service service = RetrofitUtil.get();
        service.repString("123456").enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    // http 200 http tout s'est bien passé
                    String  resultat = response.body();
                    Toast.makeText(MainActivity.this, resultat, Toast.LENGTH_SHORT).show();
                    Log.i("RETROFIT", resultat);

                } else {
                    // cas d'erreur http 400 404
                    Log.i("RETROFIT", response.code()+"");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // erreur accès réseau ou alors GSON
                Log.i("RETROFIT", t.getMessage());
            }


        });
        service.repNombre("55554").enqueue(new Callback<List<Nombre>>() {
            @Override
            public void onResponse(Call<List<Nombre>> call, Response<List<Nombre>> response) {
                if (response.isSuccessful()) {
                    // http 200 http tout s'est bien passé
                    List<Nombre> resultat = response.body();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        try {
                            remplirRecycler(resultat);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    Log.i("RETROFIT", resultat.size()+"");

                } else {
                    // cas d'erreur http 400 404
                    Log.i("RETROFIT", response.code()+"");
                }
            }

            @Override
            public void onFailure(Call<List<Nombre>> call, Throwable t) {
                // erreur accès réseau ou alors GSON
                Log.i("RETROFIT", t.getMessage());
            }
        });


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void remplirRecycler(List<Nombre> d ) throws ParseException {


            adapter.list.addAll(d);

        adapter.notifyDataSetChanged();
    }

    private void initRecycler(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        adapter = new NombrAdapteur();
        recyclerView.setAdapter(adapter);
    }
}
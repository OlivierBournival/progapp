package com.example.miseenpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.miseenpage.modele.Items;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ItemAdapteur adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recycle init
        this.initRecycler();
        this.remplirRecycler();
    }
    public void initRecycler(){

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
// use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        adapter = new ItemAdapteur();
        recyclerView.setAdapter(adapter);


    }
    private void remplirRecycler() {
        for (int i = 0 ; i < 10 ; i++) {
            Items p = new Items();
            p.id = 1;
            p.text = "20" + (new Random().nextInt(20));
            adapter.list.add(p);
        }
        adapter.notifyDataSetChanged();
    }
}
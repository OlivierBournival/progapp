package com.example.bournival2.model;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bournival2.R;
import com.example.bournival2.transfer.Nombre;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class NombrAdapteur extends RecyclerView.Adapter<NombrAdapteur.MyViewHolder> {
    public List<Nombre> list;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvNombre;
        public TextView tvDesc;
        public TextView tvRep;


        public MyViewHolder(LinearLayout v) {
            super(v);
            tvNombre = v.findViewById(R.id.tvnombre);
            tvDesc = v.findViewById(R.id.tvdescription);
            tvRep = v.findViewById(R.id.tvrepresentation);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public NombrAdapteur() {
        list = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tache_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        Log.i("DEBOGAGE", "appel a onCreateViewHolder");
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Nombre TacheneCourante = list.get(position);
        holder.tvNombre.setText(" "+ TacheneCourante.nombre +"h");
        holder.tvDesc.setText(""+TacheneCourante.description + "%");
        holder.tvRep.setText("halp"+ TacheneCourante.representation);
        // TODO setText sur un integer crash ""+TacheneCourante.dateLimite.format(DateTimeFormatter.ofPattern("f"))


    }

    // renvoie la taille de la liste
    @Override
    public int getItemCount() {
        return list.size();
    }
}
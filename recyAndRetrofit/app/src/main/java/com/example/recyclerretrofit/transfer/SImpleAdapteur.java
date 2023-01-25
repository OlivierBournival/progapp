package com.example.recyclerretrofit.transfer;

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

import com.example.recyclerretrofit.R;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SImpleAdapteur extends RecyclerView.Adapter<SImpleAdapteur.MyViewHolder> {
    public List<SImple> list;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvNom;
        public TextView tvPourcentage;
        public TextView tvDate;
        public TextView tvTemp;

        public MyViewHolder(LinearLayout v) {
            super(v);
            tvNom = v.findViewById(R.id.tvNom);
            tvPourcentage = v.findViewById(R.id.tvPourcentage);
            tvDate = v.findViewById(R.id.tvDateLimite);
            tvTemp = v.findViewById(R.id.tvTempEcouler);
        }
    }
    public SImpleAdapteur() {
        list = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SImpleAdapteur.MyViewHolder onCreateViewHolder(ViewGroup parent,
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
        SImple TacheneCourante = list.get(position);
        holder.tvNom.setText(TacheneCourante.b);
        holder.tvDate.setText(TacheneCourante.c.toString());
        // TODO setText sur un integer crash ""+TacheneCourante.dateLimite.format(DateTimeFormatter.ofPattern("f"))
        Log.i("DEBOGAGE", "appel a onBindViewHolder " + position);

    }

    // renvoie la taille de la liste
    @Override
    public int getItemCount() {
        return list.size();
    }
}
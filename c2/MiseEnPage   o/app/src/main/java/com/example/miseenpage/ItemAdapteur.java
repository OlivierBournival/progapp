package com.example.miseenpage;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miseenpage.modele.Items;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapteur extends RecyclerView.Adapter<ItemAdapteur.MyViewHolder> {
    public List<Items> list;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvid;
        public TextView tvtext;
        public MyViewHolder(LinearLayout v) {
            super(v);
            tvid = v.findViewById(R.id._itemId);
            tvtext = v.findViewById(R.id._itemText);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ItemAdapteur() {
        list = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ItemAdapteur.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false); //!
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Items itemCourante = list.get(position);
        holder.tvid.setText(""+itemCourante.id);
        holder.tvtext.setText(""+itemCourante.text); // TODO setText sur un integer crash

    }

    // renvoie la taille de la liste
    @Override
    public int getItemCount() {
        return list.size();
    }
}
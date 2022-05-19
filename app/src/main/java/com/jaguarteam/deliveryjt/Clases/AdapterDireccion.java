package com.jaguarteam.deliveryjt.Clases;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaguarteam.deliveryjt.R;

import java.util.ArrayList;

public class AdapterDireccion extends RecyclerView.Adapter<AdapterDireccion.ViewHolder>{

    ArrayList<Direccion> direccionesLista;

    AdapterDireccion(ArrayList<Direccion> publicacionList){
        this.direccionesLista=publicacionList;
    }

    @NonNull
    @Override
    public AdapterDireccion.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterDireccion.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_direccion,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDireccion.ViewHolder holder, int position) {
        holder.bindItems(direccionesLista.get(position));
        //holder.setOnclikLiseners();
    }

    @Override
    public int getItemCount() {
        return direccionesLista.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Direccion miDireccion;

        ViewHolder(View itemView){
            super(itemView);
        }

        public  void bindItems(Direccion publicacion){
            miDireccion=publicacion;
        }

        public void setOnclikLiseners() {
        }

        @Override
        public void onClick(View view) {
        }
    }

}

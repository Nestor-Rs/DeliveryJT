package com.jaguarteam.deliveryjt.Clases;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaguarteam.deliveryjt.R;

import java.util.ArrayList;

public class AdapterDireccion extends RecyclerView.Adapter<AdapterDireccion.ViewHolder>{

    ArrayList<Direccion> direccionesLista;

    public AdapterDireccion(ArrayList<Direccion> publicacionList){
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
        TextView nombre,data;

        ViewHolder(View itemView){
            super(itemView);
            nombre=itemView.findViewById(R.id.nombreDireccion);
            data=itemView.findViewById(R.id.descripcionDireccion);
        }

        public  void bindItems(Direccion publicacion){
            miDireccion=publicacion;
            nombre.setText(miDireccion.getNombre());
            data.setText(miDireccion.getLatitud()+" "+miDireccion.getLogitud());
        }

        public void setOnclikLiseners() {
        }

        @Override
        public void onClick(View view) {
        }
    }

}

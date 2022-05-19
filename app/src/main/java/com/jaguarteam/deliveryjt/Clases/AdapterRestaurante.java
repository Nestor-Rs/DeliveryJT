package com.jaguarteam.deliveryjt.Clases;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jaguarteam.deliveryjt.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterRestaurante extends RecyclerView.Adapter<AdapterRestaurante.ViewHolder>{

    ArrayList<Restaurantes> publicacionList;
    boolean esDestacado;

    public AdapterRestaurante(ArrayList<Restaurantes> publicacionList, boolean esDestacado){
        super();
        this.publicacionList=publicacionList;
        this.esDestacado=esDestacado;
    }

    @NonNull
    @Override
    public AdapterRestaurante.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(esDestacado){
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_presentacion_destacada,parent,false));
        }
        else{
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_presentacion,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRestaurante.ViewHolder holder, int position) {
        holder.bindItems(publicacionList.get(position));
        //holder.setOnclikLiseners();
    }

    @Override
    public int getItemCount() {
        return publicacionList.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Restaurantes miRestaurante;
        TextView nombre,descripcion;
        ImageView miImagen;

        ViewHolder(View itemView){
            super(itemView);
            nombre=itemView.findViewById(R.id.nombreCardRestaurante);
            descripcion=itemView.findViewById(R.id.ubicacionCardRestaurante);
            miImagen=itemView.findViewById(R.id.imagenCardPresentacion);
        }

        public  void bindItems(Restaurantes publicacion){
            miRestaurante=publicacion;
            //dar datos a la card
            nombre.setText(miRestaurante.getNombre());
            descripcion.setText(miRestaurante.getUbicacion());
            //dar image
            Picasso.get().load(miRestaurante.getLogo()).error(R.mipmap.ic_launcher).into(miImagen);
        }

        public void setOnclikLiseners() {
        }

        @Override
        public void onClick(View view) {
        }
    }
}

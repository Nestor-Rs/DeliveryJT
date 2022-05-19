package com.jaguarteam.deliveryjt.Clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.jaguarteam.deliveryjt.Activity.Dashboard;
import com.jaguarteam.deliveryjt.Fragment.VistaRestaurante;
import com.jaguarteam.deliveryjt.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.ViewHolder>{

    ArrayList<Platillo> publicacionList;

    public AdapterMenu(ArrayList<Platillo> publicacionList){
        super();
        this.publicacionList=publicacionList;
    }

    @NonNull
    @Override
    public AdapterMenu.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterMenu.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_platillo,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMenu.ViewHolder holder, int position) {
        holder.bindItems(publicacionList.get(position));
    }

    @Override
    public int getItemCount() {
        return publicacionList.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Platillo miPlatillo;
        ImageButton miBoton;
        ImageView miImagen;
        TextView nombre,descrip;

        ViewHolder(View itemView){
            super(itemView);
            miBoton=itemView.findViewById(R.id.botonCardPlatillo);
            miImagen=itemView.findViewById(R.id.imagenCardPlatillo);
            nombre=itemView.findViewById(R.id.nombreCardPlatillo);
            descrip=itemView.findViewById(R.id.ubicacionCardPlatillo);
        }

        public  void bindItems(Platillo publicacion){
            miPlatillo=publicacion;
            Picasso.get().load(miPlatillo.getImagen()).error(R.mipmap.ic_launcher).into(miImagen);
            nombre.setText(miPlatillo.getNombre());
            descrip.setText(miPlatillo.getDescripcion()+" "+miPlatillo.getPrecio());
            miBoton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(itemView.getContext(), "Se añadio al carrito", Toast.LENGTH_SHORT).show();
        }
    }
}

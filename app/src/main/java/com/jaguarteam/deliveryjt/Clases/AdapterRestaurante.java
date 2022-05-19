package com.jaguarteam.deliveryjt.Clases;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.jaguarteam.deliveryjt.Activity.Dashboard;
import com.jaguarteam.deliveryjt.Activity.Login;
import com.jaguarteam.deliveryjt.Fragment.VistaRestaurante;
import com.jaguarteam.deliveryjt.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterRestaurante extends RecyclerView.Adapter<AdapterRestaurante.ViewHolder>{

    ArrayList<Restaurantes> publicacionList;
    boolean esDestacado;
    Context context;

    public AdapterRestaurante(ArrayList<Restaurantes> publicacionList, boolean esDestacado,Context context){
        super();
        this.publicacionList=publicacionList;
        this.esDestacado=esDestacado;
        this.context=context;
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
    }

    @Override
    public int getItemCount() {
        return publicacionList.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder {

        Restaurantes miRestaurante;
        TextView nombre,descripcion;
        ImageView miImagen;


        ViewHolder(View itemView){
            super(itemView);
            nombre=itemView.findViewById(R.id.nombreCardRestaurante);
            descripcion=itemView.findViewById(R.id.ubicacionCardRestaurante);
            miImagen=itemView.findViewById(R.id.imagenCardPresentacion);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment miFragment=new VistaRestaurante(miRestaurante);
                    cambioFracment(miFragment);
                    //Toast.makeText(itemView.getContext(), "Presion", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public  void bindItems(Restaurantes publicacion){
            miRestaurante=publicacion;
            //dar datos a la card
            nombre.setText(miRestaurante.getNombre());
            descripcion.setText(miRestaurante.getUbicacion());
            //dar image
            Picasso.get().load(miRestaurante.getLogo()).error(R.mipmap.ic_launcher).into(miImagen);
        }

        void cambioFracment(Fragment fragment){
            
            FragmentTransaction transaction = ((Dashboard)context).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.dashboardFragment, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}

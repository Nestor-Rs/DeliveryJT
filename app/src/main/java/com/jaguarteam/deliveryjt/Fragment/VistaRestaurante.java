package com.jaguarteam.deliveryjt.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jaguarteam.deliveryjt.Clases.AdapterDireccion;
import com.jaguarteam.deliveryjt.Clases.AdapterMenu;
import com.jaguarteam.deliveryjt.Clases.AdapterRestaurante;
import com.jaguarteam.deliveryjt.Clases.Direccion;
import com.jaguarteam.deliveryjt.Clases.Platillo;
import com.jaguarteam.deliveryjt.Clases.Restaurantes;
import com.jaguarteam.deliveryjt.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VistaRestaurante extends Fragment {

    private FirebaseFirestore db;
    private ArrayList<Platillo> misPlatillos;

    private AdapterMenu adapter;
    private RecyclerView recyclerView;

    Restaurantes miRestaurante;
    TextView nombre,direccion;
    ImageView logo;

    public VistaRestaurante(Restaurantes rest) {
        this.miRestaurante=rest;
        db = FirebaseFirestore.getInstance();
        misPlatillos=new ArrayList<Platillo>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_vista_restaurante, container, false);

        adapter=new AdapterMenu(misPlatillos);
        recyclerView=(RecyclerView) view.findViewById(R.id.platilloRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        nombre=view.findViewById(R.id.nombreVistaRestaurante);
        direccion=view.findViewById(R.id.direccionVistaRestaurante);
        logo=view.findViewById(R.id.logoVistaRestaurante);

        nombre.setText(miRestaurante.getNombre());
        direccion.setText(miRestaurante.getUbicacion());
        Picasso.get().load(miRestaurante.getLogo()).error(R.mipmap.ic_launcher).into(logo);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        misPlatillos.clear();
        getMenu();
    }

    private void getMenu(){
        String idRestaurante=miRestaurante.getIdRestaurante().trim();
        Log.w("AYUDA",miRestaurante.getIdRestaurante());
        db.collection("Restaurantes")
                .document(idRestaurante)
                .collection("Menu")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                misPlatillos.add( new Platillo(
                                        document.get("nombre").toString(),
                                        document.get("descripcion").toString(),
                                        document.get("imagen").toString(),
                                        document.get("precio").toString()
                                ));
                            }
                            adapter.notifyDataSetChanged();
                        }
                        else {
                            Log.w("FIREBASE", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jaguarteam.deliveryjt.Clases.AdapterRestaurante;
import com.jaguarteam.deliveryjt.Clases.Restaurantes;
import com.jaguarteam.deliveryjt.R;

import java.util.ArrayList;

public class ListaRestaurantes extends Fragment {

    private FirebaseFirestore db;
    private ArrayList<Restaurantes> misRestaurantes,misRestaurantesDestacados;
    private AdapterRestaurante adapter,adapterDestacado;
    private RecyclerView recyclerView,recyclerViewDestacado;

    public ListaRestaurantes() {
        db = FirebaseFirestore.getInstance();
        misRestaurantes= new ArrayList<Restaurantes>();
        misRestaurantesDestacados=new ArrayList<Restaurantes>();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_lista_restaurantes, container, false);


        adapterDestacado=new AdapterRestaurante(misRestaurantesDestacados,true,this.getActivity());
        recyclerViewDestacado=(RecyclerView) view.findViewById(R.id.restaurantesDestacadosRecycler);
        recyclerViewDestacado.setLayoutManager(new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false));
        recyclerViewDestacado.setAdapter(adapterDestacado);

        adapter=new AdapterRestaurante(misRestaurantes,false,this.getActivity());
        recyclerView=(RecyclerView) view.findViewById(R.id.restaurantesRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        misRestaurantesDestacados.clear();
        misRestaurantes.clear();
        getRestaurantes();
    }

    public void getRestaurantes(){
        db.collection("Restaurantes").
                get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                misRestaurantes.add(new Restaurantes(document.get("idRestaurante").toString(),
                                                document.get("nombre").toString(),
                                                document.get("ubicacion").toString(),
                                                document.get("logo").toString()
                                        )
                                );
                                misRestaurantesDestacados.add(new Restaurantes(document.get("idRestaurante").toString(),
                                                document.get("nombre").toString(),
                                                document.get("ubicacion").toString(),
                                                document.get("logo").toString()
                                        )
                                );
                            }
                            adapterDestacado.notifyDataSetChanged();
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.w("FIREBASE", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}
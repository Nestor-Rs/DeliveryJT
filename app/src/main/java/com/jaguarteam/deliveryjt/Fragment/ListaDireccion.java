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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jaguarteam.deliveryjt.Clases.AdapterDireccion;
import com.jaguarteam.deliveryjt.Clases.AdapterRestaurante;
import com.jaguarteam.deliveryjt.Clases.Direccion;
import com.jaguarteam.deliveryjt.R;

import java.util.ArrayList;

public class ListaDireccion extends Fragment {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private ArrayList<Direccion> misDirecciones;
    private AdapterDireccion adapter;
    private RecyclerView recyclerView;


    public ListaDireccion() {
        db = FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        misDirecciones=new ArrayList<Direccion>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_lista_direccion, container, false);

        adapter=new AdapterDireccion(misDirecciones);
        recyclerView=(RecyclerView) view.findViewById(R.id.direccionesRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        misDirecciones.clear();
        getDirecciones();
    }

    private void getDirecciones(){
        db.collection("InformacionUsuario")
                .document(mAuth.getUid())
                .collection("Ubicacion")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                misDirecciones.add( new Direccion(
                                        document.get("nombre").toString(),
                                        document.get("latitud").toString(),
                                        document.get("longitud").toString()
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
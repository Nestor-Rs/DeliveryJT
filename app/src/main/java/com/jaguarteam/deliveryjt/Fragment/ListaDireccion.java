package com.jaguarteam.deliveryjt.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.FirebaseFirestore;
import com.jaguarteam.deliveryjt.Clases.Direccion;
import com.jaguarteam.deliveryjt.R;

import java.util.ArrayList;

public class ListaDireccion extends Fragment {

    private FirebaseFirestore db;
    private ArrayList<Direccion> misDirecciones;


    public ListaDireccion() {
        db = FirebaseFirestore.getInstance();
        misDirecciones=new ArrayList<Direccion>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_lista_direccion, container, false);



        return view;
    }
}
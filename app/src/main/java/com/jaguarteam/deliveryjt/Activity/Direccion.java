package com.jaguarteam.deliveryjt.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jaguarteam.deliveryjt.Fragment.ActualizarDireccion;
import com.jaguarteam.deliveryjt.Fragment.ListaDireccion;
import com.jaguarteam.deliveryjt.R;

public class Direccion extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direccion);

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.direccionFragment,new ListaDireccion());
        fragmentTransaction.commit();
    }

    public void onClickDireccion(View view){
        Intent mapa = new Intent(this,MapsActivity.class);
        startActivity(mapa);
    }
   void cambioFracment(Fragment fragment){
       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
       transaction.replace(R.id.direccionFragment, fragment);
       transaction.addToBackStack(null);
       transaction.commit();
    }
}
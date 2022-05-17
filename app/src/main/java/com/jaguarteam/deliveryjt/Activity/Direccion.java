package com.jaguarteam.deliveryjt.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
        switch (view.getId()){
            case R.id.cardGPS:
                cambioFracment(new ActualizarDireccion());
                break;
            case R.id.seleccionarActualizarDireccion:
                //TODO:Hacer la actualizacion de la direccion
                onBackPressed();
                break;
            case R.id.cancelarActualizarDireccion:
                onBackPressed();
                break;
        }
    }
   void cambioFracment(Fragment fragment){
       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
       transaction.replace(R.id.direccionFragment, fragment);
       transaction.addToBackStack(null);
       transaction.commit();
    }
}
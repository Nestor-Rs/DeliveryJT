package com.jaguarteam.deliveryjt.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;
import com.jaguarteam.deliveryjt.Clases.IniciadorFragment;
import com.jaguarteam.deliveryjt.Fragment.ActualizarDireccion;
import com.jaguarteam.deliveryjt.Fragment.ListaDireccion;
import com.jaguarteam.deliveryjt.R;

public class Direccion extends AppCompatActivity {

    IniciadorFragment gestorFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direccion);

        gestorFragments=new IniciadorFragment(this,new ListaDireccion(),R.id.direccionFragment);
    }

    public void onClickDireccion(View view){
        switch (view.getId()){
            case R.id.cardGPS:
                gestorFragments.cambioFracment(new ActualizarDireccion(),R.id.direccionFragment);
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
}
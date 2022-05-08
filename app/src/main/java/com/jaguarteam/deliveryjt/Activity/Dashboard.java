package com.jaguarteam.deliveryjt.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jaguarteam.deliveryjt.Fragment.ListaRestaurantes;
import com.jaguarteam.deliveryjt.R;

public class Dashboard extends AppCompatActivity {


    Fragment fragmentInScreen;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentInScreen=new ListaRestaurantes();
        fragmentTransaction.add(R.id.dashboardFragment,fragmentInScreen);
        fragmentTransaction.commit();
    }

    public void onClickDashboard(View view){
        switch (view.getId()){
            case R.id.perfilDashboard:
                Intent perfil = new Intent(this,Perfil.class);
                startActivity(perfil);
                break;
            case R.id.direccionDashboard:
                Intent direccion = new Intent(this,Direccion.class);
                startActivity(direccion);
                break;
        }
    }
}
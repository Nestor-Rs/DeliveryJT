package com.jaguarteam.deliveryjt.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.jaguarteam.deliveryjt.Clases.IniciadorFragment;
import com.jaguarteam.deliveryjt.Fracment.ListaRestaurantes;
import com.jaguarteam.deliveryjt.Fracment.VistaPerfil;
import com.jaguarteam.deliveryjt.R;

public class Perfil extends AppCompatActivity {

    IniciadorFragment gestorFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        gestorFragments=new IniciadorFragment(this,new VistaPerfil(),R.id.perfilFragment);
    }
}
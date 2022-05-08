package com.jaguarteam.deliveryjt.Clases;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class IniciadorFragment {
    private Context context;
    private Fragment fragmentInScreen;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    public IniciadorFragment(Context context, Fragment fragment, int idFragment){
        this.context=context;
        this.fragmentManager=((AppCompatActivity) this.context).getSupportFragmentManager();
        this.fragmentTransaction=this.fragmentManager.beginTransaction();
        this.fragmentInScreen=fragment;
        this.fragmentTransaction.add(idFragment,this.fragmentInScreen);
        this.fragmentTransaction.commit();
    }

    public void cambioFracment(Fragment fragment,int idFragment){
        FragmentTransaction transaction = ((AppCompatActivity) this.context).getSupportFragmentManager().beginTransaction();
        transaction.replace(idFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

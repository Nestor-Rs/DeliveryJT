package com.jaguarteam.deliveryjt.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaguarteam.deliveryjt.Clases.Usuario;
import com.jaguarteam.deliveryjt.R;

public class VistaPerfil extends Fragment {



    public VistaPerfil() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vista_perfil, container, false);
    }
}
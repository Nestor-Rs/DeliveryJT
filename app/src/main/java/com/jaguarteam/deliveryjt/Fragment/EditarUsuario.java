package com.jaguarteam.deliveryjt.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputLayout;
import com.jaguarteam.deliveryjt.R;

public class EditarUsuario extends Fragment {

    TextInputLayout correoContenedor,telefonoContenedor,contrasenaContenedor,repiteContrasenaContenedor;

    public EditarUsuario() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_editar_usuario, container, false);
    }
}
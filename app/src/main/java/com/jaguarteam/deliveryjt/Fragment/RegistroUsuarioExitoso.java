package com.jaguarteam.deliveryjt.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaguarteam.deliveryjt.Clases.Usuario;
import com.jaguarteam.deliveryjt.R;


public class RegistroUsuarioExitoso extends Fragment {

    Usuario miUsuario;

    public RegistroUsuarioExitoso(Usuario myUser) {
        miUsuario=myUser;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro_usuario_exitoso, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        TextView finalTexto=getActivity().findViewById(R.id.textViewRegistro);

        finalTexto.setText("Registro exitoso. Bienvenido "+miUsuario.getNombre()+" "+miUsuario.getApellido());
    }
}
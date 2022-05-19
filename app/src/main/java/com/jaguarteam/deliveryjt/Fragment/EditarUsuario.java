package com.jaguarteam.deliveryjt.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jaguarteam.deliveryjt.R;

public class EditarUsuario extends Fragment {

    TextInputLayout correoContenedor,telefonoContenedor,contrasenaContenedor,repiteContrasenaContenedor;
    Button cambiarUsuario;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    public EditarUsuario() {
        db=FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_editar_usuario, container, false);

        correoContenedor=(TextInputLayout) view.findViewById(R.id.correoEditarUsuario);
        telefonoContenedor=(TextInputLayout) view.findViewById(R.id.telefonoEditarUsuario);
        //contrasenaContenedor=(TextInputLayout) view.findViewById(R.id.contrasenaEditarUsuario);
        //repiteContrasenaContenedor=(TextInputLayout) view.findViewById(R.id.repiteContraEditarUsuario);

        cambiarUsuario=(Button) view.findViewById(R.id.editarUsuario);

        cambiarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarDatos()){
                    editarUsuario();
                    getActivity().onBackPressed();
                }
            }
        });

        return view;
    }


    public void editarUsuario(){
        db.collection("InformacionUsuario")
                .document(mAuth.getUid())
                .update("correo",correoContenedor.getEditText().getText().toString());
        db.collection("InformacionUsuario")
                .document(mAuth.getUid())
                .update("telefono",telefonoContenedor.getEditText().getText().toString());
        //Todo:Hacer cambio de contraseña fireAuht
    }

    private boolean validarDatos(){
        if(correoContenedor.getEditText().getText().toString().isEmpty()){
            correoContenedor.setError("Ingresa tu correo");
           return false;
        }
        else if(telefonoContenedor.getEditText().getText().toString().isEmpty()){
            telefonoContenedor.setError("Ingresa tu telefono");
            return false;
        }
        else{
            return true;
        }
    }

}
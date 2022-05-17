package com.jaguarteam.deliveryjt.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jaguarteam.deliveryjt.Fragment.EditarUsuario;
import com.jaguarteam.deliveryjt.Fragment.VistaPerfil;
import com.jaguarteam.deliveryjt.R;

public class Perfil extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TextView nombre,tel,correo,direccion;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        mAuth = FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.perfilFragment,new VistaPerfil());
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        nombre=findViewById(R.id.nombrePerfil);
        tel=findViewById(R.id.telefonoPerfil);
        correo=findViewById(R.id.correoPerfil);
        direccion=findViewById(R.id.direccionPerfil);


    }

    public void onClickPerfil(View view){
        switch (view.getId()){
            case R.id.editarPerfil:
                cambioFracment(new EditarUsuario());
                break;
            case R.id.editarUsuario:
                //TODO: Crear metodo para editar usuario
                onBackPressed();
                break;
            case R.id.cancelarEditarUsuario:
                onBackPressed();
                break;
            case R.id.eliminarPerfil:
                FirebaseAuth.getInstance().signOut();
                finish();
                break;
        }
    }
    void cambioFracment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.perfilFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
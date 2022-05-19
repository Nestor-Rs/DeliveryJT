package com.jaguarteam.deliveryjt.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jaguarteam.deliveryjt.Clases.Usuario;
import com.jaguarteam.deliveryjt.Fragment.EditarUsuario;
import com.jaguarteam.deliveryjt.Fragment.VistaPerfil;
import com.jaguarteam.deliveryjt.R;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class Perfil extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private TextView nombre,tel,correo,direccion;

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
        nombre= (TextView) findViewById(R.id.nombrePerfil);
        tel=(TextView) findViewById(R.id.telefonoPerfil);
        correo=(TextView) findViewById(R.id.correoPerfil);
        direccion=(TextView) findViewById(R.id.direccionPerfil);

        getData();
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

    private void getData(){
        DocumentReference docRef = db.collection("InformacionUsuario").document(mAuth.getUid().toString());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        nombre.setText(document.getData().get("nombre").toString()+" "+document.getData().get("apellido").toString());
                        tel.setText(document.getData().get("telefono").toString());
                        correo.setText(document.getData().get("correo").toString());
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });


    }

}
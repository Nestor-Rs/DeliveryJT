package com.jaguarteam.deliveryjt.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jaguarteam.deliveryjt.Clases.Usuario;
import com.jaguarteam.deliveryjt.Fragment.LoginLogin;
import com.jaguarteam.deliveryjt.Fragment.RecuperarContrasena;
import com.jaguarteam.deliveryjt.Fragment.Registro;
import com.jaguarteam.deliveryjt.Fragment.RegistroUsuarioExitoso;
import com.jaguarteam.deliveryjt.R;

public class CrearCuenta extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private Fragment fragmentInScreen;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private TextInputLayout nombre,apellido,correo,telefono,contrasena,confirmaContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentInScreen=new Registro();
        fragmentTransaction.add(R.id.creraCuentaFragment,fragmentInScreen);
        fragmentTransaction.commit();

        mAuth = FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

    }

    public void onClickCrear(View view){
        switch (view.getId()) {
            case R.id.registrarseRegistroUsuario:
                if(comprobarCampos()){
                    crearCuenta(correo.getEditText().getText().toString(),contrasena.getEditText().getText().toString());
                }
                break;
            case R.id.cancelarRegistroUsuario:
                finish();
                break;
            case R.id.regristroExitoso:
                finish();
                break;
        }
    }

    public void cambioFracment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragmentInScreen=fragment;
        transaction.replace(R.id.creraCuentaFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    void crearCuenta(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Se creo la cuenta", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            crearDocumentoDatos(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Error con el registro", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CrearCuenta.this, "Error al crear la cuenta",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    void crearDocumentoDatos(FirebaseUser user){
        //Crear objeto
        Usuario myUser = new Usuario(
                user.getUid().toString(),
                nombre.getEditText().getText().toString(),
                apellido.getEditText().getText().toString(),
                correo.getEditText().getText().toString(),
                telefono.getEditText().getText().toString()
        );
        //Crear documento de fireStore
        db.collection("InformacionUsuario").document(user.getUid())
                .set(myUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Se creo el documento", "DocumentSnapshot successfully written!");
                        cambioFracment(new RegistroUsuarioExitoso(myUser));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("No se creo el documento", "Error writing document", e);
                        Toast.makeText(CrearCuenta.this, "Error al crear tus datos",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
//n
    boolean comprobarCampos(){
        nombre=findViewById(R.id.nombreRegistroUser);
        apellido=findViewById(R.id.apellidoRegistroUsuario);
        correo=findViewById(R.id.correoRegistroUsuario);
        telefono=findViewById(R.id.telefonoRegistroUsuario);
        contrasena=findViewById(R.id.contrasenaRegistroUsuario);
        confirmaContrasena=findViewById(R.id.repiteContraRegistroUsuario);
        if(nombre.getEditText().getText().toString().isEmpty()){
            nombre.setError("Ingresa tu nombre");
            return false;
        }
        else if(apellido.getEditText().getText().toString().isEmpty()){
            apellido.setError("Ingresa tu apellido");
            return false;
        }
        else if(correo.getEditText().getText().toString().isEmpty()){
            correo.setError("Ingresa tu correo");
            return false;
        }
        else if(telefono.getEditText().getText().toString().isEmpty()){
            telefono.setError("Ingresa tu telefono");
            return false;
        }
        else if(contrasena.getEditText().getText().toString().isEmpty()){
            contrasena.setError("Ingresa tu contraseña");
            return false;
        }
        else if(confirmaContrasena.getEditText().getText().toString().isEmpty()){
            confirmaContrasena.setError("Confirma tu contraseña");
            return false;
        }
        /*else if(contrasena.getEditText().getText().toString().equals(confirmaContrasena.getEditText().getText().toString())){
            confirmaContrasena.setError("La contraseña no coincide");
            return false;
        }*/
        else{
            return true;
        }
    }

}
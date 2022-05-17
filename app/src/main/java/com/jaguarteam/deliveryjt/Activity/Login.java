package com.jaguarteam.deliveryjt.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jaguarteam.deliveryjt.Clases.Usuario;
import com.jaguarteam.deliveryjt.Fragment.LoginLogin;
import com.jaguarteam.deliveryjt.Fragment.Registro;
import com.jaguarteam.deliveryjt.R;
import com.jaguarteam.deliveryjt.Fragment.RecuperarContrasena;
import com.jaguarteam.deliveryjt.Fragment.RegistroUsuarioExitoso;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Fragment fragmentInScreen;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentInScreen=new LoginLogin();
        fragmentTransaction.add(R.id.loginFragment,fragmentInScreen);
        fragmentTransaction.commit();

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            updateUI(user);
        }
    }

    public void onClickLogin(View view){
        switch (view.getId()) {
            case R.id.entrarLogin:
                TextInputLayout correo=(TextInputLayout) findViewById(R.id.UserLogin),
                contrasena= (TextInputLayout) findViewById(R.id.contraLogin);
                verificarUsuario(correo.getEditText().getText().toString(),contrasena.getEditText().getText().toString());
                break;
            case R.id.registroLogin:
                Intent registroI = new Intent(this,CrearCuenta.class);
                startActivity(registroI);
                break;
            case R.id.recuperarContraLogin:
                cambioFracment(new RecuperarContrasena());
                break;
            case R.id.recuperarRecuperarContrasena:
                //TODO:Hacer proceso para recuperar la cuenta
                onBackPressed();
                break;
            case R.id.cancelarRecuperarContrasena:
                onBackPressed();
                break;
        }
    }

    private void updateUI(FirebaseUser user) {
        Intent dashboard=new Intent(this,Dashboard.class);
        startActivity(dashboard);
    }

    public void cambioFracment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragmentInScreen=fragment;
        transaction.replace(R.id.loginFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void verificarUsuario(String correo,String contrasena){
        if(!correo.isEmpty() && !contrasena.isEmpty()){
            mAuth.signInWithEmailAndPassword(correo,contrasena)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Se autentico", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Error con autenticacion", "signInWithEmail:failure", task.getException());
                                Toast.makeText(Login.this, "Tu correo o contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });
        }
    }
}
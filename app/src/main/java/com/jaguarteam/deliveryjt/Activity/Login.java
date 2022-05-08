package com.jaguarteam.deliveryjt.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jaguarteam.deliveryjt.Fracment.LoginLogin;
import com.jaguarteam.deliveryjt.Fracment.Registro;
import com.jaguarteam.deliveryjt.R;
import com.jaguarteam.deliveryjt.Fracment.RecuperarContrasena;
import com.jaguarteam.deliveryjt.Fracment.RegistroUsuarioExitoso;

public class Login extends AppCompatActivity {

    Fragment fragmentInScreen;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentInScreen=new LoginLogin();
        fragmentTransaction.add(R.id.loginFragment,fragmentInScreen);
        fragmentTransaction.commit();
    }

    public void onClickLogin(View view){
        switch (view.getId()) {
            case R.id.entrarLogin:
                Intent dashboard=new Intent(this,Dashboard.class);
                startActivity(dashboard);
                break;
            case R.id.registroLogin:
                cambioFracment(new Registro());
                break;
            case R.id.recuperarContraLogin:
                cambioFracment(new RecuperarContrasena());
                break;
            case R.id.registrarseRegistroUsuario:
                cambioFracment(new RegistroUsuarioExitoso());
                break;
            case R.id.cancelarRegistroUsuario:
                onBackPressed();
                break;
            case R.id.regristroExitoso:
                //TODO:Saber cual es el la mejor solucion al registro exitoso
                //cambioFracment(new LoginLogin());
                onBackPressed();
                onBackPressed();
                break;
            case R.id.recuperarRecuperarContrasena:
                //TODO:Hacer proceso para recuperar la cuenta
                onBackPressed();
                break;
            case R.id.cancelarRecuperarContrasena:
                onBackPressed();
                break;
            /*case R.id.RegistrarCartilla:
            comprobarCampos();
            if(comprobarCampos()!=false){
                createCartilla();
            }
            break;
            case R.id.exitCRE:
            onBackPressed();
            finish();
            break;*/
        }
    }
    public void cambioFracment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.loginFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
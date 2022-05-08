package com.jaguarteam.deliveryjt.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.jaguarteam.deliveryjt.Clases.IniciadorFragment;
import com.jaguarteam.deliveryjt.Fragment.EditarUsuario;
import com.jaguarteam.deliveryjt.Fragment.VistaPerfil;
import com.jaguarteam.deliveryjt.R;

public class Perfil extends AppCompatActivity {

    IniciadorFragment gestorFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        gestorFragments=new IniciadorFragment(this,new VistaPerfil(),R.id.perfilFragment);
    }

    public void onClickPerfil(View view){
        switch (view.getId()){
            case R.id.editarPerfil:
                gestorFragments.cambioFracment(new EditarUsuario(),R.id.perfilFragment);
                break;
            case R.id.editarUsuario:
                //TODO: Crear metodo para editar usuario
                onBackPressed();
                break;
            case R.id.cancelarEditarUsuario:
                onBackPressed();
                break;
        }
    }
}
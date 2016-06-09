package com.fup.jennyferlopez.proyectokitetkiwe.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Usuario;

public class RegistrarseActivity extends AppCompatActivity {

    ImageView imagenUsuario;
    GestorBd bd;
    EditText nombreUsuario, contraseña;
    Button btn_registrar, btn_cancelar;
    TextView tvNombre, tvContra;
    String activity;
    String pathimg;
    String activitydos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        bd= new GestorBd(getApplicationContext());
        nombreUsuario = (EditText) findViewById(R.id.edt_usuario_registro);
        contraseña = (EditText) findViewById(R.id.edt_contraseña_registro);
        btn_cancelar = (Button) findViewById(R.id.btn_cancelar);
        btn_registrar = (Button) findViewById(R.id.btn_registrar);
        tvNombre= (TextView) findViewById(R.id.tvNombreR);
        tvContra= (TextView) findViewById(R.id.tv_contraR);

        String font_url ="font/dklemonyellowsun.otf";

        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        nombreUsuario.setTypeface(font);
        contraseña.setTypeface(font);
        btn_cancelar.setTypeface(font);
        btn_registrar.setTypeface(font);
        tvNombre.setTypeface(font);
        tvContra.setTypeface(font);
        activity= String.valueOf(RegistrarseActivity.class);
        activitydos="RegistrarseActivity";
    }


    public void registrar(View view) {
        pathimg= String.valueOf(R.drawable.avatar_blanco);
        String nombreUsu = nombreUsuario.getText().toString();
        String contra = contraseña.getText().toString();
        Usuario usuario= new Usuario(nombreUsu, contra, pathimg,  activitydos);
        bd.agregarUsuario(usuario);
        nombreUsuario.setText(null);
        contraseña.setText(null);
        Toast.makeText(this, "Registro satisfactorio", Toast.LENGTH_SHORT).show();
        Intent irLogin= new Intent(this, LogingActivity.class);
        startActivity(irLogin);
        finish();

    }


    public void cancelar(View view) {
        Intent irLogin= new Intent(this, LogingActivity.class);
        startActivity(irLogin);
        finish();
    }
}

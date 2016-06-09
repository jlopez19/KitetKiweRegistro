package com.fup.jennyferlopez.proyectokitetkiwe.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.DialogFragmentAvatar;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel2Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.Niveles11Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView cambiar_avatar, glosario, himno, jugar, galeria, simbolos, manual_usuario;
    SharedPreferences preferences;
    String avatarSeleccionado;
    GestorBd bd;
    String userName, activity, pass, pathImg;
    int id_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cambiar_avatar=(ImageView)findViewById(R.id.cambiarAvatar);
        glosario=(ImageView)findViewById(R.id.glosario);
        himno=(ImageView)findViewById(R.id.himno);
        jugar=(ImageView)findViewById(R.id.jugar);
        galeria=(ImageView)findViewById(R.id.galeriaFotos);
        simbolos=(ImageView)findViewById(R.id.simbolos);
        manual_usuario=(ImageView)findViewById(R.id.manualDeUsuario);
        bd=new GestorBd(getApplicationContext());

        cambiar_avatar.setOnClickListener(this);
        glosario.setOnClickListener(this);
        himno.setOnClickListener(this);
        jugar.setOnClickListener(this);
        galeria.setOnClickListener(this);
        simbolos.setOnClickListener(this);
        manual_usuario.setOnClickListener(this);

        preferences = getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        loadPreference();
        actualizarActivity();
    }

    private void actualizarActivity() {
        activity= "MenuActivity";
        userName =preferences.getString(Preference.USER_NAME, "");
        id_user =preferences.getInt(Preference.USER_ID, 0);
        pass =preferences.getString(Preference.PASSWORD, "");
        bd.actualizarActivity(userName , pass, avatarSeleccionado, activity, id_user);
    }

    private void loadPreference() {
        preferences = getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        avatarSeleccionado = preferences.getString(Preference.AVATAR_SEECCIONADO, "");
    }


    @Override
    public void onClick(View v) {

        int id=v.getId();
        if (id==R.id.cambiarAvatar){
            FragmentManager fm = getFragmentManager();
            DialogFragmentAvatar dialogFragment = new DialogFragmentAvatar ();
            dialogFragment.show(fm ,"");
        }else  if (id==R.id.glosario){
            Intent irGlosario = new Intent(this,GlosarioActivity.class);
            startActivity(irGlosario);

        }else  if (id==R.id.himno){
            Intent irMusica = new Intent(this, MusicaActivity.class);
            startActivity(irMusica);

        }else  if (id==R.id.jugar){
          //  if (avatarSeleccionado.equals(null)) {
     //           Toast.makeText(this, "selecciona tu avatar", Toast.LENGTH_SHORT).show();
       //     }else {
                Intent irNiveles = new Intent(this, NivelesActivity.class);
                startActivity(irNiveles);
         //   }
        }else  if (id==R.id.galeriaFotos){
            Intent irImagenes = new Intent(this, ImagenesActivity.class);
            startActivity(irImagenes);

        }else  if (id==R.id.simbolos){
            Intent irSimbolos = new Intent(this, SimbolosActivity.class);
            startActivity(irSimbolos);

        }else  if (id==R.id.manualDeUsuario){
            Intent irManual = new Intent(this, ManualActivity.class);
            startActivity(irManual);
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {new AlertDialog.Builder(this).setIcon(R.drawable.logo).setTitle(R.string.cerrar_app)
                .setMessage(R.string.salir_app).setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(Preference.IS_LOGGED, false);
                        editor.commit();
                        Intent intent = new Intent(getApplicationContext(), LogingActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

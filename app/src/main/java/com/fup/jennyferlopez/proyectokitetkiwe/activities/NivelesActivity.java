package com.fup.jennyferlopez.proyectokitetkiwe.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.VocalesActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

public class NivelesActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    ImageView correAvaatr, icAvatarNiveles;
    RelativeLayout rlLayout;
    TextView tv_puntos;
    int id_user;
    GestorBd db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveles);

        db=new GestorBd(getApplication());
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        correAvaatr = (ImageView) findViewById(R.id.correAvatar);
        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);

        correAvaatr.setOnClickListener(this);
        rlLayout= (RelativeLayout) findViewById(R.id.lyNivel1);
        loadPreference();
        cargarTextV();
    }
    private void cargarTextV() {
        id_user =db.obtenerId(userName);
        db.eliminarPuntaje(id_user);
        List<Puntos> pts=db.sumaPuntos(id_user);
        pts=db.sumaPuntos(id_user);
        int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
        tv_puntos.setText(""+ p);
    }

    private void loadPreference() {
        preferences = getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        avatarSeleccionado = preferences.getString(Preference.AVATAR_SEECCIONADO, "");
        userName =preferences.getString(Preference.USER_NAME, "");


        if (avatarSeleccionado.equals(null)) {
            correAvaatr.setBackgroundResource(Integer.parseInt(null));
            icAvatarNiveles.setBackgroundResource(Integer.parseInt(null));
        } else if (avatarSeleccionado.equals("1")) {
            correAvaatr.setBackgroundResource(R.drawable.nino_uno);
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_uno_n);
        } else if (avatarSeleccionado.equals("2")) {
            correAvaatr.setBackgroundResource(R.drawable.nino_dos);
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_dos_n);
        } else if (avatarSeleccionado.equals("3")) {
            correAvaatr.setBackgroundResource(R.drawable.nino_tres);
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_tres_n);
        } else if (avatarSeleccionado.equals("4")) {
            correAvaatr.setBackgroundResource(R.drawable.nina_uno);
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_uno_n);
        } else if (avatarSeleccionado.equals("5")) {
            correAvaatr.setBackgroundResource(R.drawable.nina_dos);;
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_dos_n);
        } else if (avatarSeleccionado.equals("6")) {
            correAvaatr.setBackgroundResource(R.drawable.nina_tres);;
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_tres_n);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.correAvatar){
            Intent irRecVocales= new Intent(this, VocalesActivity.class);
            startActivity(irRecVocales);
            Puntos puntos= new Puntos(id_user, 3);
            db.insertarPuntos(puntos);
            finish();
        }
    }
}
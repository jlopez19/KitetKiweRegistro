package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

public class ColorCorres3Activity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles;
    TextView tv_title;
    int id_user;
    GestorBd db;
    ImageView img_negro, img_blanco, img_amarillo, img_azul, img_rojo, img_verde, img_rosado, img_gris, img_morado, img_naranja, paint_montanias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_corres3);
        db=new GestorBd(getApplication());

        tv_title = (TextView) findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        paint_montanias = (ImageView) findViewById(R.id.imgMontanias);
        img_naranja = (ImageView) findViewById(R.id.img_naranja);
        img_negro = (ImageView) findViewById(R.id.img_negro);
        img_blanco = (ImageView) findViewById(R.id.img_blanco);
        img_amarillo = (ImageView) findViewById(R.id.img_amarillo);
        img_azul = (ImageView) findViewById(R.id.img_azul);
        img_rojo = (ImageView) findViewById(R.id.img_rojo);
        img_verde = (ImageView) findViewById(R.id.img_verde);
        img_rosado = (ImageView) findViewById(R.id.img_rosado);
        img_gris = (ImageView) findViewById(R.id.img_gris);
        img_morado = (ImageView) findViewById(R.id.img_morado);


        img_naranja.setOnClickListener(this);
        img_negro.setOnClickListener(this);
        img_blanco.setOnClickListener(this);
        img_amarillo.setOnClickListener(this);
        img_azul.setOnClickListener(this);
        img_rojo.setOnClickListener(this);
        img_verde.setOnClickListener(this);
        img_rosado.setOnClickListener(this);
        img_gris.setOnClickListener(this);
        img_morado.setOnClickListener(this);

        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);

        loadPreference();
        cargarTextV();
    }
    private void cargarTextV() {
        id_user =db.obtenerId(userName);
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
            icAvatarNiveles.setBackgroundResource(Integer.parseInt(null));
        } else if (avatarSeleccionado.equals("1")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_uno_n);
        } else if (avatarSeleccionado.equals("2")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_dos_n);
        } else if (avatarSeleccionado.equals("3")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_tres_n);
        } else if (avatarSeleccionado.equals("4")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_uno_n);
        } else if (avatarSeleccionado.equals("5")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_dos_n);
        } else if (avatarSeleccionado.equals("6")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_tres_n);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_verde) {
            paint_montanias.setBackgroundResource(R.drawable.montanias_pin);

            Puntos puntos= new Puntos(id_user, 3);
            db.insertarPuntos(puntos);
            List<Puntos> pts=db.sumaPuntos(id_user);
            int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
            tv_puntos.setText(""+ p);

            Thread timerThread = new Thread(){
                public void run(){
                    try{
                        sleep(1200);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }finally{
                        Intent ir=new Intent(getApplicationContext(), Nivel32Activity.class);
                        startActivity(ir);
                        finish();
                    }
                }
            };
            timerThread.start();
        }else {
            Toast.makeText(this, "intentalo de nuevo", Toast.LENGTH_SHORT).show();
        }
    }
}

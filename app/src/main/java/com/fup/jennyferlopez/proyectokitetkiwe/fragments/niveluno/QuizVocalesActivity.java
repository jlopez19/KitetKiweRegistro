package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel2Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Pregunta;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.ArrayList;
import java.util.List;

public class QuizVocalesActivity extends AppCompatActivity implements View.OnClickListener {
    RadioGroup radio;
    String respuesta;
    RadioButton rb1,rb2,rb3;
    String respuestaVerdadera;
    ImageView imagenVista;
    Button imagenSiguiente;
    TextView tv_title;

    ArrayList<Pregunta> arrayPreguntas;
    TextView textoPregunta;
    int[] posiciones;
    int cont=0;TextView  tv_puntos;
    ImageView correAvaatr, icAvatarNiveles;
    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    GestorBd db;
    int id_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_vocales);
        arrayPreguntas = new ArrayList<>();
        Pregunta p = new Pregunta("¿Cuales son las vocales concierre glotal?",
                "a’ e’ i’ u’",
                "a e i u",
                "ah eh ih uh",
                "ax ex ix ux");
        arrayPreguntas.add(p);
        p = new Pregunta("¿Cuales son las vocales nasales?",
                "a’ e’ i’ u’",
                "a~ e~ i~ u~",
                "a e i u",
                "ah eh ih uh");
        arrayPreguntas.add(p);
        p = new Pregunta("¿Cuales son las vocales nasales concierre glotal?",
                "a' e' i' u'",
                "a e i u",
                "ah eh ih uh",
                "a'~ e'~ i'~ u'~");
        arrayPreguntas.add(p);
        p = new Pregunta("¿Cuales son las vocales concierre glotal?",
                "a’ e’ i’ u’",
                "a e i u",
                "ah eh ih uh",
                "ax ex ix ux");
        arrayPreguntas.add(p);
        p = new Pregunta("¿Cuales son las vocales nasales concierre glotal?",
                "a' e' i' u'",
                "a e i u",
                "ah eh ih uh",
                "a'~ e'~ i'~ u'~");
        arrayPreguntas.add(p);
        posiciones = new int[arrayPreguntas.size()];
        for (int i=0;i<arrayPreguntas.size();i++){
            posiciones[i] = i;
        }
        textoPregunta = (TextView) findViewById(R.id.pregunta);
        radio = (RadioGroup) findViewById(R.id.preguntas);
        rb1 = (RadioButton) findViewById(R.id.rb_respuesta1);
        rb2 = (RadioButton) findViewById(R.id.rb_respuesta2);
        rb3 = (RadioButton) findViewById(R.id.rb_respuesta3);
        textoPregunta.setText(arrayPreguntas.get(posiciones[cont]).getPregunta());
        rb1.setText(arrayPreguntas.get(posiciones[cont]).getResp1());
        rb2.setText(arrayPreguntas.get(posiciones[cont]).getResp2());
        rb3.setText(arrayPreguntas.get(posiciones[cont]).getResp3());
        respuestaVerdadera = arrayPreguntas.get(posiciones[cont]).getResVer();
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_respuesta1:
                        respuesta = rb1.getText().toString();
                        break;
                    case R.id.rb_respuesta2:
                        respuesta = rb2.getText().toString();
                        break;
                    case R.id.rb_respuesta3:
                        respuesta = rb3.getText().toString();
                        break;
                }

                new Esperar().execute();
            }
        });
        imagenSiguiente = (Button) findViewById(R.id.fab);
        imagenSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagenVista.setVisibility(View.INVISIBLE);
                imagenSiguiente.setVisibility(View.INVISIBLE);
                validarDisponibilidad();
                rb1.setEnabled(true);
                rb2.setEnabled(true);
                rb3.setEnabled(true);
            }
        });

        radio.setVisibility(View.VISIBLE);
        db=new GestorBd(getApplication());
        tv_title = (TextView) findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        textoPregunta.setTypeface(font);
        rb1.setTypeface(font);
        rb2.setTypeface(font);
        rb3.setTypeface(font);

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
        if (v.getId()== R.id.correAvatar){
            Toast.makeText(this, "No disponible", Toast.LENGTH_SHORT).show();
        }
    }



    private class Esperar extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            radio.setEnabled(false);
            rb1.setEnabled(false);
            rb2.setEnabled(false);
            rb3.setEnabled(false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            cont++;
            if (respuestaVerdadera.equalsIgnoreCase(respuesta)){
                imagenVista.setVisibility(View.VISIBLE);
                imagenSiguiente.setVisibility(View.VISIBLE);
                rb1.setEnabled(false);
                rb2.setEnabled(false);
                rb3.setEnabled(false);
            }else {
                Toast.makeText(QuizVocalesActivity.this, "respuesta incorrecta", Toast.LENGTH_SHORT).show();
                validarDisponibilidad();
                rb1.setEnabled(false);
                rb2.setEnabled(false);
                rb3.setEnabled(false);
            }

            rb1.setEnabled(true);
            rb2.setEnabled(true);
            rb3.setEnabled(true);
        }
    }

    public void validarDisponibilidad(){
        if (cont<arrayPreguntas.size()){
            asignarPreguntas();
        }else {
            Toast.makeText(this,"Felicidades has respondido todas las preguntas",Toast.LENGTH_SHORT).show();
            radio.setVisibility(View.GONE);
            startActivity(new Intent(this, Nivel2Activity.class));
            finish();
        }
    }

    public void asignarPreguntas(){
        textoPregunta.setText(arrayPreguntas.get(posiciones[cont]).getPregunta());
        rb1.setText(arrayPreguntas.get(posiciones[cont]).getResp1());
        rb2.setText(arrayPreguntas.get(posiciones[cont]).getResp2());
        rb3.setText(arrayPreguntas.get(posiciones[cont]).getResp3());
        respuestaVerdadera = arrayPreguntas.get(posiciones[cont]).getResVer();


    }
}

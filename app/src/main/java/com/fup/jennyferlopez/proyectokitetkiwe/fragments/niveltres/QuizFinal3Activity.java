package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.activities.NivelesActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.Nivel4Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel2Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.models.User;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.ServicioUsuario;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class QuizFinal3Activity extends AppCompatActivity implements View.OnClickListener {


    TextView tvPreguntaUno, tvPreguntaDos;
    RadioGroup rgPreUno, rgPreDos;
    RadioButton rbColorUnoP, rbColorUnoN, rbColorUnoN2, rbColorUnoN3, rbColorDosP, rbColorDosN, rbColorDosN2, rbColorDosN3;

    ServicioUsuario servicioUsuario;
    SharedPreferences preferences;
    String userName;
    int id_user, cont_good=0, cont_fail=0, cont_intentos=0;
    TextView tvPreguntaTres, tvPreguntaCuatro, tv_puntos;
    RadioGroup rgPreTres, rgPreCuatro;
    RadioButton rbColorTresP, rbColorTresN, rbColorTresN2, rbColorTresN3, rbColorCuatroP, rbColorCuatroN, rbColorCuatroN2, rbColorCuatroN3;

    int conVB=0, conVM=0,conRB=0, conRM=0,conAB=0, conAM=0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_final3);

        tvPreguntaUno =(TextView)  findViewById(R.id.pregUnoCol);
        tvPreguntaDos =(TextView)  findViewById(R.id.pregDosCol);
        tv_puntos =(TextView)  findViewById(R.id.tv_puntos);
        rgPreUno =(RadioGroup)  findViewById(R.id.rgPreguntaUno);
        rgPreDos =(RadioGroup)  findViewById(R.id.rgPreguntados);
        rbColorUnoP =(RadioButton)  findViewById(R.id.rbColorUnoP);
        rbColorUnoN =(RadioButton)  findViewById(R.id.rbColorUnoN);
        rbColorUnoN2 =(RadioButton)  findViewById(R.id.rbColorUnoN2);
        rbColorUnoN3 =(RadioButton)  findViewById(R.id.rbColorUnoN3);
        rbColorDosP =(RadioButton)  findViewById(R.id.rbColorDosP);
        rbColorDosN =(RadioButton)  findViewById(R.id.rbColorDosN);
        rbColorDosN2 =(RadioButton)  findViewById(R.id.rbColorDosN2);
        rbColorDosN3 =(RadioButton)  findViewById(R.id.rbColorDosN3);


        tvPreguntaTres =(TextView)  findViewById(R.id.pregTresCol);
        tvPreguntaCuatro =(TextView)  findViewById(R.id.pregCuatroCol);
        rgPreTres =(RadioGroup)  findViewById(R.id.rgPreguntaTres);
        rgPreCuatro =(RadioGroup)  findViewById(R.id.rgPreguntaCuatro);
        rbColorTresP =(RadioButton)  findViewById(R.id.rbColorTresP);
        rbColorTresN =(RadioButton)  findViewById(R.id.rbColorTresN);
        rbColorTresN2 =(RadioButton)  findViewById(R.id.rbColorTresN2);
        rbColorTresN3 =(RadioButton)  findViewById(R.id.rbColorTresN3);
        rbColorCuatroP =(RadioButton)  findViewById(R.id.rbColorCuatroP);
        rbColorCuatroN =(RadioButton)  findViewById(R.id.rbColorCuatroN);
        rbColorCuatroN2 =(RadioButton)  findViewById(R.id.rbColorCuatroN2);
        rbColorCuatroN3 =(RadioButton)  findViewById(R.id.rbColorCuatroN3);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tvPreguntaUno.setTypeface(font);
        tvPreguntaDos.setTypeface(font);
        tvPreguntaTres.setTypeface(font);
        tvPreguntaCuatro.setTypeface(font);
        rbColorUnoP.setOnClickListener(this);
        rbColorUnoN.setOnClickListener(this);
        rbColorUnoN2.setOnClickListener(this);
        rbColorUnoN3.setOnClickListener(this);
        rbColorDosP.setOnClickListener(this);
        rbColorDosN.setOnClickListener(this);
        rbColorDosN2.setOnClickListener(this);
        rbColorDosN3.setOnClickListener(this);
        rbColorTresP.setOnClickListener(this);
        rbColorTresN.setOnClickListener(this);
        rbColorTresN2.setOnClickListener(this);
        rbColorTresN3.setOnClickListener(this);
        rbColorCuatroP.setOnClickListener(this);
        rbColorCuatroN.setOnClickListener(this);
        rbColorCuatroN2.setOnClickListener(this);
        rbColorCuatroN3.setOnClickListener(this);

        loadDatos();
        loadRealm();
        actualizarPuntos();
    }


    private void loadDatos() {
        preferences = getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        userName =preferences.getString(Preference.USER_NAME, "");
    }


    private void loadRealm() {
        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("Test1")
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

        servicioUsuario = new ServicioUsuario(Realm.getDefaultInstance());

    }

    @Override
    public void onClick(View v) {
        int id =v.getId();
        if (id== R.id.rbColorUnoP){
            conVB=1;
            enabledColorUno();
        }else if (id== R.id.rbColorUnoN || id== R.id.rbColorUnoN2 || id== R.id.rbColorUnoN3) {
            enabledColorUno();
            cont_intentos=cont_intentos+1;
            conVM=1;
        }else if (id== R.id.rbColorDosP ){
            enabledColorDos();
            conRB=1;
        }else if (id== R.id.rbColorDosN || id== R.id.rbColorDosN2 || id== R.id.rbColorDosN3){
            enabledColorDos();
            cont_intentos=cont_intentos+1;
            conRM=1;
        }if (id== R.id.rbColorTresP){
            enabledColorTres();
            conAB=1;
        }else if (id== R.id.rbColorTresN || id== R.id.rbColorTresN2 || id== R.id.rbColorTresN3) {
            enabledColorTres();
            cont_intentos=cont_intentos+1;
            conAM=1;
        }else if (id== R.id.rbColorCuatroP ){
            if (conVB==1 && conRB==1 && conAB==1){
                SumarPuntos(3);
                puntosGanados(3);
                irActivity();
            }
        }else if (id== R.id.rbColorCuatroN || id== R.id.rbColorCuatroN2 || id== R.id.rbColorCuatroN3){
            enabledColorCuatro();
            if ((conVM==1 && conRB==1 && conAB==1) || (conVB==1 && conRM==1 && conAB==1) || (conVB==1 && conRB==1 && conAM==1)) {
                SumarPuntos(2);
                puntosGanados(2);
                irActivity();
            }
            else {
                if (cont_intentos>=2){
                    Toast.makeText(this, "Quiz no superado debes repetir el nivel 3", Toast.LENGTH_LONG).show();
                    Intent irNivelDos=new Intent(this, Nivel3Activity.class);
                    startActivity(irNivelDos);
                }
            }
        }
    }

    private void irActivity() {
        Intent irNivelCuatro=new Intent(this, Nivel4Activity.class);
        startActivity(irNivelCuatro);
        finish();
    }
    private void enabledColorUno() {
        rbColorUnoP.setEnabled(false);
        rbColorUnoN.setEnabled(false);
        rbColorUnoN2.setEnabled(false);
        rbColorUnoN3.setEnabled(false);
        rgPreDos.setEnabled(false);
    }

    private void enabledColorDos() {
        rbColorDosP.setEnabled(false);
        rbColorDosN.setEnabled(false);
        rbColorDosN2.setEnabled(false);
        rbColorDosN3.setEnabled(false);
        rgPreDos.setEnabled(false);
    }
    private void enabledColorTres() {
        rbColorTresP.setEnabled(false);
        rbColorTresN.setEnabled(false);
        rbColorTresN2.setEnabled(false);
        rbColorTresN3.setEnabled(false);
    }

    private void enabledColorCuatro() {
        rbColorCuatroP.setEnabled(false);
        rbColorCuatroN.setEnabled(false);
        rbColorCuatroN2.setEnabled(false);
        rbColorCuatroN3.setEnabled(false);
    }

    private void SumarPuntos(int puntos) {
        userName =preferences.getString(Preference.USER_NAME, "");
        User usuario_por_id = servicioUsuario.obtenerUsuarioPorId(userName);
        if (usuario_por_id!=null) {

            int p=Integer.parseInt(String.valueOf(usuario_por_id.getPuntos()));

            servicioUsuario.actualizarPuntos(usuario_por_id,puntos+p);
            int p1=Integer.parseInt(String.valueOf(usuario_por_id.getPuntos()));
            tv_puntos.setText(""+ p1);
        }
    }
    private void actualizarPuntos() {
        userName =preferences.getString(Preference.USER_NAME, "");
        User usuario_por_id = servicioUsuario.obtenerUsuarioPorId(userName);
        if (usuario_por_id!=null) {
            int p=Integer.parseInt(String.valueOf(usuario_por_id.getPuntos()));
            tv_puntos.setText(""+ p);
        }
    }
    private void puntosGanados(int puntos) {
        Toast toasta = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_personalizado,
                (ViewGroup) findViewById(R.id.lytLayout));

        TextView txtMsg = (TextView)layout.findViewById(R.id.tvMsjToast);
        ImageView imgToast =(ImageView) layout.findViewById(R.id.imgToast);
        imgToast.setBackgroundResource(R.drawable.ic_oros);
        txtMsg.setText("Ganaste "+ puntos +" semillas");
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        txtMsg.setTypeface(font);
        toasta.setDuration(Toast.LENGTH_SHORT);
        toasta.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0,0);
        toasta.setView(layout);
        toasta.show();
    }
}

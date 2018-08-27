package com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelsiete;

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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.activities.MenuActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.Nivel4Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelseis.Nivel6Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.models.User;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.ServicioUsuario;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class QuizNivel7Activity extends AppCompatActivity implements View.OnClickListener{
    TextView tvPreguntaUno, tvPreguntaDos, tv_puntos;
    RadioGroup rgPreUno, rgPreDos;
    RadioButton rbNumUnoP, rbNumUnoN, rbNumUnoN2, rbNumUnoN3, rbNumDosP, rbNumDosN, rbNumDosN2, rbNumDosN3;
    TextView tvPreguntaTres, tvPreguntaCuatro;
    RadioGroup rgPreTres, rgPreCuatro;
    RadioButton rbNumTresP, rbNumTresN, rbNumTresN2, rbNumTresN3, rbNumCuatroP, rbNumCuatroN, rbNumCuatroN2, rbNumCuatroN3;
    ServicioUsuario servicioUsuario;
    SharedPreferences preferences;
    String userName;
    int id_user, cont_good=0, cont_fail=0, cont_intentos=0;
    int conCB=0, conCM=0,conDOB=0, conDOM=0,conDIB=0, conDIM=0 , conDUB=0, conDUM=0, conNum=0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_nivel7);

        tv_puntos =(TextView)  findViewById(R.id.tv_puntos);
        tvPreguntaUno =(TextView)  findViewById(R.id.pregUnoNum);
        tvPreguntaDos =(TextView)  findViewById(R.id.pregDosNum);
        rgPreUno =(RadioGroup)  findViewById(R.id.rgPreguntaUno);
        rgPreDos =(RadioGroup)  findViewById(R.id.rgPreguntados);
        rbNumUnoP =(RadioButton)  findViewById(R.id.rbNumUnoP);
        rbNumUnoN =(RadioButton)  findViewById(R.id.rbNumUnoN);
        rbNumUnoN2 =(RadioButton)  findViewById(R.id.rbNumUnoN2);
        rbNumUnoN3 =(RadioButton)  findViewById(R.id.rbNumUnoN3);
        rbNumDosP =(RadioButton)  findViewById(R.id.rbNumDosP);
        rbNumDosN =(RadioButton)  findViewById(R.id.rbNumDosN);
        rbNumDosN2 =(RadioButton)  findViewById(R.id.rbNumDosN2);
        rbNumDosN3 =(RadioButton)  findViewById(R.id.rbNumDosN3);
        tvPreguntaTres =(TextView)  findViewById(R.id.pregTresNum);
        tvPreguntaCuatro =(TextView)  findViewById(R.id.pregCuatroNum);
        rgPreTres =(RadioGroup)  findViewById(R.id.rgPreguntaTres);
        rgPreCuatro =(RadioGroup)  findViewById(R.id.rgPreguntaCuatro);
        rbNumTresP =(RadioButton)  findViewById(R.id.rbNumTresP);
        rbNumTresN =(RadioButton)  findViewById(R.id.rbNumTresN);
        rbNumTresN2 =(RadioButton)  findViewById(R.id.rbNumTresN2);
        rbNumTresN3 =(RadioButton)  findViewById(R.id.rbNumTresN3);
        rbNumCuatroP =(RadioButton)  findViewById(R.id.rbNumCuatroP);
        rbNumCuatroN =(RadioButton)  findViewById(R.id.rbNumCuatroN);
        rbNumCuatroN2 =(RadioButton)  findViewById(R.id.rbNumCuatroN2);
        rbNumCuatroN3 =(RadioButton)  findViewById(R.id.rbNumCuatroN3);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tvPreguntaUno.setTypeface(font);
        tvPreguntaDos.setTypeface(font);
        tvPreguntaTres.setTypeface(font);
        tvPreguntaCuatro.setTypeface(font);

        rbNumUnoP.setOnClickListener(this);
        rbNumUnoN. setOnClickListener(this);
        rbNumUnoN2. setOnClickListener(this);
        rbNumUnoN3. setOnClickListener(this);
        rbNumDosP. setOnClickListener(this);
        rbNumDosN. setOnClickListener(this);
        rbNumDosN2. setOnClickListener(this);
        rbNumDosN3. setOnClickListener(this);
        rbNumTresP. setOnClickListener(this);
        rbNumTresN. setOnClickListener(this);
        rbNumTresN2. setOnClickListener(this);
        rbNumTresN3. setOnClickListener(this);
        rbNumCuatroP. setOnClickListener(this);
        rbNumCuatroN. setOnClickListener(this);
        rbNumCuatroN2. setOnClickListener(this);
        rbNumCuatroN3. setOnClickListener(this);

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
        if (id== R.id.rbNumUnoP){
            conCB=1;
            enabledNumUno();
        }else if (id== R.id.rbNumUnoN || id== R.id.rbNumUnoN2 || id== R.id.rbNumUnoN3) {
            enabledNumUno();
            cont_intentos=cont_intentos+1;
            conCM=1;
        }else if (id== R.id.rbNumDosP ){
            enabledNumDos();
            conDOB=1;
        }else if (id== R.id.rbNumDosN || id== R.id.rbNumDosN2 || id== R.id.rbNumDosN3){
            enabledNumDos();
            cont_intentos=cont_intentos+1;
            conDOM=1;
        }if (id== R.id.rbNumTresP){
            enabledNumTres();
            conDIB=1;
        }else if (id== R.id.rbNumTresN || id== R.id.rbNumTresN2 || id== R.id.rbNumTresN3) {
            enabledNumTres();
            cont_intentos=cont_intentos+1;
            conDIM=1;
        }else if (id== R.id.rbNumCuatroP ){
            if (conCB==1 && conDOB==1 && conDIB==1){
                SumarPuntos(3);
                puntosGanados(3);
                irActivity();
            }
        }else if (id== R.id.rbNumCuatroN || id== R.id.rbNumCuatroN2 || id== R.id.rbNumCuatroN3){
            if ((conCM==1 && conDOB==1 && conDIB==1) || (conCB==1 && conDOM==1 && conDIB==1) || (conCB==1 && conDOB==1 && conDIM==1)) {
                SumarPuntos(2);
                puntosGanados(2);
                irActivity();
            }
            else {
                if (cont_intentos>=2){
                    Toast.makeText(this, "Quiz no superado debes repetir el nivel 3", Toast.LENGTH_LONG).show();
                    Intent irNivelDos=new Intent(this, Nivel7Activity.class);
                    startActivity(irNivelDos);
                }
            }
        }
    }

    private void irActivity() {
        Toast.makeText(this, "Felicitaciones ha superado todos los niveles", Toast.LENGTH_LONG).show();
        Intent irNivelCuatro=new Intent(this, MenuActivity.class);
        startActivity(irNivelCuatro);
        finish();
    }

    private void enabledNumUno() {
        rbNumUnoP.setEnabled(false);
        rbNumUnoN.setEnabled(false);
        rbNumUnoN2.setEnabled(false);
        rbNumUnoN3.setEnabled(false);
        rgPreDos.setEnabled(false);
    }

    private void enabledNumDos() {
        rbNumDosP.setEnabled(false);
        rbNumDosN.setEnabled(false);
        rbNumDosN2.setEnabled(false);
        rbNumDosN3.setEnabled(false);
        rgPreDos.setEnabled(false);
    }

    private void enabledNumTres() {
        rbNumTresP.setEnabled(false);
        rbNumTresN.setEnabled(false);
        rbNumTresN2.setEnabled(false);
        rbNumTresN3.setEnabled(false);
    }

    private void enabledNumCuatro() {
        rbNumCuatroP.setEnabled(false);
        rbNumCuatroN.setEnabled(false);
        rbNumCuatroN2.setEnabled(false);
        rbNumCuatroN3.setEnabled(false);
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

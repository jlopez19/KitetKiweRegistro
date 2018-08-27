package com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelseis;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.activities.SplashTodosActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcinco.Nivel52Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.LaberintoActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.models.User;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.DrawingView;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.ServicioUsuario;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LaberintoCuerpoActivity extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles, imgAyuda;
    TextView tv_title;
    int id_user, cont=0;
    ServicioUsuario servicioUsuario;
    EditText tv_cabeza, tv_codo, tv_cuello, tv_pie;
    Button btn_cabeza, btn_codo, btn_cuello, btn_pie;
    String cabeza, codo, cuello, pie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laberinto_cuerpo);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_cabeza = (EditText) findViewById(R.id.tv_cabeza);
        tv_codo = (EditText) findViewById(R.id.tv_codo);
        tv_cuello = (EditText) findViewById(R.id.tv_cuello);
        tv_pie = (EditText) findViewById(R.id.tv_pie);
        btn_cabeza = (Button) findViewById(R.id.btn_cabeza);
        btn_codo = (Button) findViewById(R.id.btn_codo);
        btn_cuello = (Button) findViewById(R.id.btn_cuello);
        btn_pie = (Button) findViewById(R.id.btn_pie);

        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);

        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);

        imgAyuda = (ImageView) findViewById(R.id.img_ayuda);
        imgAyuda.setOnClickListener(this);
        btn_cabeza.setOnClickListener(this);
        btn_cuello.setOnClickListener(this);
        btn_codo.setOnClickListener(this);
        btn_pie.setOnClickListener(this);
        loadPreference();
        loadSplash();
        loadRealm();
        loadPuntos();
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


    private void loadSplash() {
        final Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        imgAyuda.startAnimation(zoomAnimation);
        Bundle b= new Bundle();
        b.putString("text_uno", "Escribe en la caja de texto en nasa ");
        b.putString("text_dos", "la parter del cuerpo correspondiente despues presiona la impagen para validar");
        b.putInt("img_uno", 0);
        b.putInt("img_dos", R.drawable.toast_good);
        Intent irActivity= new Intent(LaberintoCuerpoActivity.this, SplashTodosActivity.class);
        irActivity.putExtras(b);
        startActivity(irActivity);
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
    private void loadPuntos() {
        userName =preferences.getString(Preference.USER_NAME, "");
        User usuario_por_id = servicioUsuario.obtenerUsuarioPorId(userName);
        if (usuario_por_id!=null) {
            int p=Integer.parseInt(String.valueOf(usuario_por_id.getPuntos()));
            tv_puntos.setText(""+ p);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.img_ayuda) {
            loadSplash();
        } else if (id == R.id.btn_cabeza) {
            cabeza=tv_cabeza.getText().toString().trim();
            if (cabeza.isEmpty()){
                tv_cabeza.setError("Escriba cabeza en nasa");
            }else{
                if (cabeza.equals("dxiikthe")){
                    Toast.makeText(this, "Muy bien", Toast.LENGTH_SHORT).show();
                    tv_cabeza.setEnabled(false);
                    if (cont==3){
                        irActivity();
                    }
                }else if (!cabeza.equals("dxiikthe")){
                    Toast.makeText(this, "Mal escrita", Toast.LENGTH_SHORT).show();
                    tv_cabeza.setEnabled(false);
                    if (cont==3){
                        irActivity();
                    }
                }
            }
        } else if (id == R.id.btn_codo) {
            codo=tv_codo.getText().toString().trim();
            if (codo.isEmpty()){
                tv_codo.setError("Escriba codo en nasa");
            }else {
                cont=cont+1;
                if (codo.equals("ku'ta")){
                    Toast.makeText(this, "Muy bien", Toast.LENGTH_SHORT).show();
                    tv_codo.setEnabled(false);
                    if (cont==3){
                        irActivity();
                    }
                }else if (!codo.equals("ku'ta")){
                    Toast.makeText(this, "Mal escrita", Toast.LENGTH_SHORT).show();
                    tv_codo.setEnabled(false);
                    if (cont==3){
                        irActivity();
                    }
                }
            }
        } else if (id == R.id.btn_cuello) {
            cuello=tv_cuello.getText().toString().trim();
            if (cuello.isEmpty()){
                tv_cuello.setError("Escriba cuello en nasa");
            }else {
                cont=cont+1;
                if (cuello.equals("txi'kh")){
                    Toast.makeText(this, "Muy bien", Toast.LENGTH_SHORT).show();
                    tv_cuello.setEnabled(false);
                    if (cont==3){
                        irActivity();
                    }
                }else if (!cuello.equals("txi'kh")){
                    Toast.makeText(this, "Mal escrita", Toast.LENGTH_SHORT).show();
                    tv_cuello.setEnabled(false);
                    if (cont==3){
                        irActivity();
                    }
                }
            }
        } else if (id == R.id.btn_pie) {
            pie=tv_pie.getText().toString().trim();
            if (pie.isEmpty()){
                tv_pie.setError("Escriba pie en nasa");
            }else {
                cont=cont+1;
                if (pie.equals("cxida")){
                    Toast.makeText(this, "Muy bien", Toast.LENGTH_SHORT).show();
                    tv_pie.setEnabled(false);
                    if (cont==3){
                        irActivity();
                    }
                }else if (!pie.equals("cxida")){
                    Toast.makeText(this, "Mal escrita", Toast.LENGTH_SHORT).show();
                    tv_pie.setEnabled(false);
                    if (cont==3){
                        irActivity();
                    }
                }
            }
        }

    }

    private void irActivity() {
        Intent irNumeros= new Intent(getApplication(), Nivel63Activity.class);
        startActivity(irNumeros);
    }
}

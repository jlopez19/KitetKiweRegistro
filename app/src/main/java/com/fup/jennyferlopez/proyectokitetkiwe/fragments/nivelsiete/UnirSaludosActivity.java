package com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelsiete;

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
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.LaberintoActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelseis.LaberintoCuerpoActivity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelseis.Nivel63Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.models.User;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.DrawingView;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.ServicioUsuario;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class UnirSaludosActivity extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles, imgAyuda;
    TextView tv_title;
    int id_user, cont=0;
    ServicioUsuario servicioUsuario;
    EditText tv_comoestas, tv_adios, tv_hastaluego, tv_quehacen;
    Button btn_comoestas, btn_adios, btn_hastaluego, btn_quehacen;
    String comoestas, adios, hastaluego, quehacen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unir_saludos);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_comoestas = (EditText) findViewById(R.id.tv_comoestas);
        tv_adios = (EditText) findViewById(R.id.tv_adios);
        tv_hastaluego = (EditText) findViewById(R.id.tv_hastaluego);
        tv_quehacen = (EditText) findViewById(R.id.tv_quehacen);
        btn_comoestas = (Button) findViewById(R.id.btn_comoestas);
        btn_adios = (Button) findViewById(R.id.btn_adios);
        btn_hastaluego = (Button) findViewById(R.id.btn_hastaluego);
        btn_quehacen = (Button) findViewById(R.id.btn_quehacen);

        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);

        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);

        imgAyuda = (ImageView) findViewById(R.id.img_ayuda);
        imgAyuda.setOnClickListener(this);
        btn_comoestas.setOnClickListener(this);
        btn_hastaluego.setOnClickListener(this);
        btn_adios.setOnClickListener(this);
        btn_quehacen.setOnClickListener(this);
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
        Intent irActivity= new Intent(UnirSaludosActivity.this, SplashTodosActivity.class);
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
        } else if (id == R.id.btn_comoestas) {
            comoestas=tv_comoestas.getText().toString().trim();
            if (comoestas.isEmpty()){
                tv_comoestas.setError("Escriba como estas en nasa");
            }else{
                if (comoestas.equals("mau u'pga")){
                    Toast.makeText(this, "Muy bien", Toast.LENGTH_SHORT).show();
                    tv_comoestas.setEnabled(false);
                    if (cont==3){
                        irActivity();
                    }
                }else if (!comoestas.equals("mau u'pga")){
                    Toast.makeText(this, "Mal escrita", Toast.LENGTH_SHORT).show();
                    tv_comoestas.setEnabled(false);
                    if (cont==3){
                        irActivity();
                    }
                }
            }
        } else if (id == R.id.btn_adios) {
            adios=tv_adios.getText().toString().trim();
            if (adios.isEmpty()){
                tv_adios.setError("Escriba adios en nasa");
            }else {
                cont=cont+1;
                if (adios.equals("ne'uth")){
                    Toast.makeText(this, "Muy bien", Toast.LENGTH_SHORT).show();
                    tv_adios.setEnabled(false);
                    if (cont==3){
                        irActivity();
                    }
                }else if (!adios.equals("ne'uth")){
                    Toast.makeText(this, "Mal escrita", Toast.LENGTH_SHORT).show();
                    tv_adios.setEnabled(false);
                    if (cont==3){
                        irActivity();
                    }
                }
            }
        } else if (id == R.id.btn_hastaluego) {
            hastaluego=tv_hastaluego.getText().toString().trim();
            if (hastaluego.isEmpty()){
                tv_hastaluego.setError("Escriba hastaluego en nasa");
            }else {
                cont=cont+1;
                if (hastaluego.equals("putx uyudkhw")){
                    Toast.makeText(this, "Muy bien", Toast.LENGTH_SHORT).show();
                    tv_hastaluego.setEnabled(false);
                    if (cont==3){
                        irActivity();
                    }
                }else if (!hastaluego.equals("putx uyudkhw")){
                    Toast.makeText(this, "Mal escrita", Toast.LENGTH_SHORT).show();
                    tv_hastaluego.setEnabled(false);
                    if (cont==3){
                        irActivity();
                    }
                }
            }
        } else if (id == R.id.btn_quehacen) {
            quehacen=tv_quehacen.getText().toString().trim();
            if (quehacen.isEmpty()){
                tv_quehacen.setError("Escriba que hacen en nasa");
            }else {
                cont=cont+1;
                if (quehacen.equals("kih kweyu'")){
                    Toast.makeText(this, "Muy bien", Toast.LENGTH_SHORT).show();
                    tv_quehacen.setEnabled(false);
                    if (cont==3){
                        irActivity();
                    }
                }else if (!quehacen.equals("kih kweyu'")){
                    Toast.makeText(this, "Mal escrita", Toast.LENGTH_SHORT).show();
                    tv_quehacen.setEnabled(false);
                    if (cont==3){
                        irActivity();
                    }
                }
            }
        }

    }

    private void irActivity() {
        Intent irNumeros= new Intent(getApplication(), Nivel73Activity.class);
        startActivity(irNumeros);
    }
}

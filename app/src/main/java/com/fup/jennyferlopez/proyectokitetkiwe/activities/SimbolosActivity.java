package com.fup.jennyferlopez.proyectokitetkiwe.activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.fup.jennyferlopez.proyectokitetkiwe.R;

public class SimbolosActivity extends AppCompatActivity {

    TextView btn_bandera, btn_baston, btn_chumbe, btn_escudo, btn_casa, btn_jigra, btn_colibri, btn_epiral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simbolos);

        btn_bandera=(TextView) findViewById(R.id.tv_bandera);
        btn_baston=(TextView)findViewById(R.id.tv_baston);
        btn_chumbe=(TextView)findViewById(R.id.tv_chumbe);
        btn_escudo=(TextView)findViewById(R.id.tv_escudo);
        btn_casa=(TextView)findViewById(R.id.tv_casa);
        btn_jigra=(TextView)findViewById(R.id.tv_jigra);
        btn_colibri=(TextView)findViewById(R.id.tv_colibri);
        btn_epiral=(TextView)findViewById(R.id.tv_espiral);

        String font_url ="font/dklemonyellowsun.otf";

        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        btn_bandera.setTypeface(font);
        btn_baston.setTypeface(font);
        btn_chumbe.setTypeface(font);
        btn_escudo.setTypeface(font);
        btn_casa.setTypeface(font);
        btn_jigra.setTypeface(font);
        btn_colibri.setTypeface(font);
        btn_epiral.setTypeface(font);
    }
}

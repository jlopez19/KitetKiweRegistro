package com.fup.jennyferlopez.proyectokitetkiwe.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.ElServicio;

public class MusicaActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvHimno;
    ImageView btnPlay, btnPause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musica);

        tvHimno=(TextView)findViewById(R.id.letraHimno);
        btnPlay=(ImageView) findViewById(R.id.btnPlay);
        btnPause=(ImageView) findViewById(R.id.btnPause);

        String font_url ="font/dklemonyellowsun.otf";

        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tvHimno.setTypeface(font);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay:
                startService(new Intent(this, ElServicio.class));
                break;
            case R.id.btnPause:
                stopService(new Intent(this, ElServicio.class));
                break;
        }
    }
}

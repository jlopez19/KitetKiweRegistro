package com.fup.jennyferlopez.proyectokitetkiwe.activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.fup.jennyferlopez.proyectokitetkiwe.R;

public class ImagenesActivity extends AppCompatActivity {

    TextView tvNombre, tvDescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes);

        tvNombre=(TextView)findViewById(R.id.tvNombreI);
        tvDescripcion=(TextView)findViewById(R.id.tvDescripcion);

        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tvNombre.setTypeface(font);
        tvDescripcion.setTypeface(font);
    }
}

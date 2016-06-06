package com.fup.jennyferlopez.proyectokitetkiwe.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

public class SplashActivity extends AppCompatActivity {

    Boolean logged;
    TextView tvSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tvSplash=(TextView)findViewById(R.id.tvSplash);

        String font_url ="font/dklemonyellowsun.otf";

        Typeface font = Typeface.createFromAsset(SplashActivity.this.getResources().getAssets(), font_url);
        tvSplash.setTypeface(font);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    loadLogin();
                }
            }
        };
        timerThread.start();
    }

    private void loadLogin() {
        SharedPreferences preferences = getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        logged =preferences.getBoolean(Preference.IS_LOGGED, false);

        if (logged){
            Intent ir=new Intent(this, MenuActivity.class);
            startActivity(ir);
            finish();
        }else {
            Intent ir=new Intent(this, LogingActivity.class);
            startActivity(ir);
            finish();
        }

    }
}

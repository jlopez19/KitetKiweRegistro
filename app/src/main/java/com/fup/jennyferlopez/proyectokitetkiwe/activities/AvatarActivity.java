package com.fup.jennyferlopez.proyectokitetkiwe.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.fragments.DialogFragmentAvatar;
import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.Niveles11Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

public class AvatarActivity extends AppCompatActivity {

    // esto es una preueba
    String avatarSeleccionado;
    ImageView imgAvatarS;
    SharedPreferences preferences;


    private int modificarX=2;
    private int modificary=2;
    boolean detectView;
    float x, x1, y, y1, h,h1, l, l1;

    ImageView select_nivel, colisionar;

    int cont=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        imgAvatarS =(ImageView) findViewById(R.id.avatarSeleccionado);
        colisionar = (ImageView) findViewById(R.id.colisionar);

        select_nivel.setOnTouchListener(handlerMover);
        select_nivel.setOnLongClickListener(detect);

        colisionar.setOnTouchListener(handlerMover);
        colisionar.setOnLongClickListener(detect);
        loadPreference();
    }

    private void loadPreference() {
        preferences = getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        avatarSeleccionado = preferences.getString(Preference.AVATAR_SEECCIONADO, "");

        if (avatarSeleccionado.equals(null)){
            imgAvatarS.setBackgroundResource(Integer.parseInt(null));
        }else if (avatarSeleccionado.equals("1")){
            imgAvatarS.setBackgroundResource(R.drawable.nino_uno);
        }else if (avatarSeleccionado.equals("2")){
            imgAvatarS.setBackgroundResource(R.drawable.nino_dos);
        }else if (avatarSeleccionado.equals("3")){
            imgAvatarS.setBackgroundResource(R.drawable.nino_tres);
        }else if (avatarSeleccionado.equals("4")){
            imgAvatarS.setBackgroundResource(R.drawable.nina_uno);
        }else if (avatarSeleccionado.equals("5")){
            imgAvatarS.setBackgroundResource(R.drawable.nina_dos);
        }else if (avatarSeleccionado.equals("6")){
            imgAvatarS.setBackgroundResource(R.drawable.nina_tres);
        }
    }


    public void cambiarAvatar(View view) {
        FragmentManager fm = getFragmentManager();
        DialogFragmentAvatar dialogFragment = new DialogFragmentAvatar ();
        dialogFragment.show(fm ,"");
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {new AlertDialog.Builder(this).setIcon(R.drawable.bandera).setTitle(R.string.cerrar_app)
                .setMessage(R.string.salir_app).setNegativeButton(android.R.string.cancel, null)//sin listener
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {//un listener que al pulsar, cierre la aplicacion
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(Preference.IS_LOGGED, false);
                        editor.commit();
                        Intent intent = new Intent(getApplicationContext(), LogingActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    View.OnLongClickListener detect= new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            detectView=true;
            return false;
        }
    };
    View.OnTouchListener handlerMover=new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {
            PointF DownPT= new PointF();
            PointF StartPT= new PointF();

            x=select_nivel.getX();
            y=select_nivel.getY();
            h=select_nivel.getWidth();
            l=select_nivel.getHeight();
            int eid= motionEvent.getAction();

            switch (eid){
                case MotionEvent.ACTION_MOVE:
                    if (detectView){
                        StartPT = new PointF(v.getX(), v.getY());
                        PointF mv=new PointF(motionEvent.getX()-DownPT.x, motionEvent.getY()-DownPT.y);
                        v.setX((StartPT.x+mv.x)-modificarX);
                        v.setY((StartPT.y+mv.y)-modificary);
                    }
                    break;
                case MotionEvent.ACTION_DOWN:
                    DownPT.x=motionEvent.getX();
                    DownPT.y=motionEvent.getY();
                    break;
                case MotionEvent.ACTION_UP:

                    switch (v.getId()){
                        case R.id.selecNivel:
                            x=v.getX();
                            y=v.getY();
                            h=v.getWidth();
                            l=v.getHeight();
                            break;
                        case R.id.colisionar:
                            x1=v.getX();
                            y1=v.getY();
                            h1=v.getWidth();
                            l1=v.getHeight();
                            break;

                    }
                    if ((x>=x1 && x<=(x1+h1) && y >= y1 && y <= (y1+l1)) || ((x+h)>=x1 && x<=(x1+h1) && y >= y1 && y <= (y1+l1))
                            || (x>=x1 && x<=(x1+h1) && (y+l) >= y1 && (y+l) <= (y1+l1)) || ((x+h)>=x1 && (x+h)<=(x1+h1) && (y+l) >= y1 && (y+l) <= (y1+l1))){
                        Toast.makeText(getApplication(), "!!!!!Se encontraron 1 punto", Toast.LENGTH_SHORT).show();
                    }


                    detectView=false;
                    break;
                default:
                    break;
            }

            return false;
        }
    };
}

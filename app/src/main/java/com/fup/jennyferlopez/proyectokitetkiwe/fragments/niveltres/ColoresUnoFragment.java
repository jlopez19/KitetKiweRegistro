package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres;


import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColoresUnoFragment extends Fragment implements View.OnClickListener{

    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles;
    TextView tv_title;
    int idUsuario;
    GestorBd db;
    int id_user;
    ImageView c_amarillo, c_azul, c_rojo, c_blanco, c_negro;

    public ColoresUnoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_colores_uno, container, false);

        c_amarillo =(ImageView) view.findViewById(R.id.c_amarillo);
        c_azul =(ImageView) view.findViewById(R.id.c_azul);
        c_rojo =(ImageView) view.findViewById(R.id.c_rojo);
        c_blanco =(ImageView) view.findViewById(R.id.c_blanco);
        c_negro =(ImageView) view.findViewById(R.id.c_negro);

        c_amarillo.setOnClickListener(this);
        c_azul.setOnClickListener(this);
        c_rojo.setOnClickListener(this);
        c_blanco.setOnClickListener(this);
        c_negro.setOnClickListener(this);

        tv_title = (TextView) view.findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) view.findViewById(R.id.ic_avatarNiveles);
        tv_puntos = (TextView) view.findViewById(R.id.tv_puntos);
        String font_url = "font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);
        db=new GestorBd(getContext());

        loadPreference();
        cargarTextV();

        return view;
    }
    private void cargarTextV() {
        id_user =db.obtenerId(userName);
        List<Puntos> pts=db.sumaPuntos(id_user);
        pts=db.sumaPuntos(id_user);
        int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
        tv_puntos.setText(""+ p);
    }


    private void loadPreference() {
        preferences = getActivity().getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        avatarSeleccionado = preferences.getString(Preference.AVATAR_SEECCIONADO, "");
        userName = preferences.getString(Preference.USER_NAME, "");
        idUsuario = preferences.getInt(Preference.USER_ID, 0);

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
        if (v.getId() == R.id.c_amarillo) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.negro);
            mp.start();
        }else if (v.getId() == R.id.c_azul) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.azul);
            mp.start();
        }else if (v.getId() == R.id.c_rojo) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.rojo);
            mp.start();
        }else if (v.getId() == R.id.c_blanco) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.blanco);
            mp.start();
        }else if (v.getId() == R.id.c_negro) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.negro);
            mp.start();
        }
    }

}

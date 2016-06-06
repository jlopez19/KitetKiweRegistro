package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro.Nivel4Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel2Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizCoBFragment extends Fragment implements CompoundButton.OnCheckedChangeListener{

    TextView tvPreguntaTres, tvPreguntaCuatro;
    RadioGroup rgPreTres, rgPreCuatro;
    RadioButton rbColorTresP, rbColorTresN, rbColorTresN2, rbColorTresN3, rbColorCuatroP, rbColorCuatroN, rbColorCuatroN2, rbColorCuatroN3;
    GestorBd db;
    SharedPreferences preferences;
    String userName;
    int id_user, cont_good=0, cont_fail=0, cont_intentos=0;

    public QuizCoBFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_quiz_co_b, container, false);

        db=new GestorBd(getActivity());
        tvPreguntaTres =(TextView) view.findViewById(R.id.pregTresCol);
        tvPreguntaCuatro =(TextView) view.findViewById(R.id.pregCuatroCol);
        rgPreTres =(RadioGroup) view.findViewById(R.id.rgPreguntaTres);
        rgPreCuatro =(RadioGroup) view.findViewById(R.id.rgPreguntaCuatro);
        rbColorTresP =(RadioButton) view.findViewById(R.id.rbColorTresP);
        rbColorTresN =(RadioButton) view.findViewById(R.id.rbColorTresN);
        rbColorTresN2 =(RadioButton) view.findViewById(R.id.rbColorTresN2);
        rbColorTresN3 =(RadioButton) view.findViewById(R.id.rbColorTresN3);
        rbColorCuatroP =(RadioButton) view.findViewById(R.id.rbColorCuatroP);
        rbColorCuatroN =(RadioButton) view.findViewById(R.id.rbColorCuatroN);
        rbColorCuatroN2 =(RadioButton) view.findViewById(R.id.rbColorCuatroN2);
        rbColorCuatroN3 =(RadioButton) view.findViewById(R.id.rbColorCuatroN3);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tvPreguntaTres.setTypeface(font);
        tvPreguntaCuatro.setTypeface(font);
        rbColorTresP.setTypeface(font);
        rbColorTresN.setTypeface(font);
        rbColorTresN2.setTypeface(font);
        rbColorTresN3.setTypeface(font);
        rbColorCuatroP.setTypeface(font);
        rbColorCuatroN.setTypeface(font);
        rbColorCuatroN2.setTypeface(font);
        rbColorCuatroN3.setTypeface(font);

        rbColorTresP.setOnCheckedChangeListener(this);
        rbColorTresN.setOnCheckedChangeListener(this);
        rbColorTresN2.setOnCheckedChangeListener(this);
        rbColorTresN3.setOnCheckedChangeListener(this);
        rbColorCuatroP.setOnCheckedChangeListener(this);
        rbColorCuatroN.setOnCheckedChangeListener(this);
        rbColorCuatroN2.setOnCheckedChangeListener(this);
        rbColorCuatroN3.setOnCheckedChangeListener(this);

        loadDatos();
        return view;
    }

    private void loadDatos() {
        preferences = getActivity().getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        userName =preferences.getString(Preference.USER_NAME, "");
        id_user =db.obtenerId(userName);
        List<Puntos> pts=db.sumaPuntos(id_user);
        pts=db.sumaPuntos(id_user);
        int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (rbColorTresP.isChecked() && rbColorCuatroP.isChecked())
        {
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            Toast.makeText(getActivity(), "Correcto ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else  if (rbColorTresP.isChecked()){
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            cont_good=cont_good+1;
            irActivity();
            Toast.makeText(getActivity(), "Muy bien! ", Toast.LENGTH_SHORT).show();
            enabledColorTresadas();
        }else  if (rbColorCuatroP.isChecked()){
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            cont_intentos=cont_intentos+1;
            irActivity();
            Toast.makeText(getActivity(), "Muy bien! ", Toast.LENGTH_SHORT).show();
            enabledColorCuatroadas();
        }else if (rbColorTresN.isChecked()){
            enabledColorTresadas();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbColorTresN2.isChecked() ){
            enabledColorTresadas();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbColorTresN3.isChecked()){
            enabledColorTresadas();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbColorCuatroN.isChecked()){
            enabledColorCuatroadas();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbColorCuatroN2.isChecked()){
            enabledColorCuatroadas();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbColorCuatroN3.isChecked()){
            enabledColorCuatroadas();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }
    }

    private void irActivity() {
        if (cont_intentos>=1 && cont_good>=1){
            Intent irNivelDos=new Intent(getActivity(), Nivel4Activity.class);
            startActivity(irNivelDos);
            getActivity().finish();
        }
    }


    private void enabledColorTresadas() {
        rbColorTresP.setEnabled(false);
        rbColorTresN.setEnabled(false);
        rbColorTresN2.setEnabled(false);
        rbColorTresN3.setEnabled(false);
    }

    private void enabledColorCuatroadas() {
        rbColorCuatroP.setEnabled(false);
        rbColorCuatroN.setEnabled(false);
        rbColorCuatroN2.setEnabled(false);
        rbColorCuatroN3.setEnabled(false);
    }
}

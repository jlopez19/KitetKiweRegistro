package com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro;


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
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcinco.Nivel5Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizNBFragment extends Fragment implements CompoundButton.OnCheckedChangeListener{

    TextView tvPreguntaTres, tvPreguntaCuatro;
    RadioGroup rgPreTres, rgPreCuatro;
    RadioButton rbNumTresP, rbNumTresN, rbNumTresN2, rbNumTresN3, rbNumCuatroP, rbNumCuatroN, rbNumCuatroN2, rbNumCuatroN3;
    GestorBd db;
    SharedPreferences preferences;
    String userName;
    int id_user, cont_good=0, cont_fail=0, cont_intentos=0;
    public QuizNBFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_quiz_nb, container, false);
        db=new GestorBd(getActivity());
        tvPreguntaTres =(TextView) view.findViewById(R.id.pregDosNum);
        tvPreguntaCuatro =(TextView) view.findViewById(R.id.pregUnoNum);
        rgPreTres =(RadioGroup) view.findViewById(R.id.rgPreguntaTres);
        rgPreCuatro =(RadioGroup) view.findViewById(R.id.rgPreguntaCuatro);
        rbNumTresP =(RadioButton) view.findViewById(R.id.rbNumTresP);
        rbNumTresN =(RadioButton) view.findViewById(R.id.rbNumTresN);
        rbNumTresN2 =(RadioButton) view.findViewById(R.id.rbNumTresN2);
        rbNumTresN3 =(RadioButton) view.findViewById(R.id.rbNumTresN3);
        rbNumCuatroP =(RadioButton) view.findViewById(R.id.rbNumCuatroP);
        rbNumCuatroN =(RadioButton) view.findViewById(R.id.rbNumCuatroN);
        rbNumCuatroN2 =(RadioButton) view.findViewById(R.id.rbNumCuatroN2);
        rbNumCuatroN3 =(RadioButton) view.findViewById(R.id.rbNumCuatroN3);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tvPreguntaTres.setTypeface(font);
        tvPreguntaCuatro.setTypeface(font);
        rbNumTresP.setTypeface(font);
        rbNumTresN.setTypeface(font);
        rbNumTresN2.setTypeface(font);
        rbNumTresN3.setTypeface(font);
        rbNumCuatroP.setTypeface(font);
        rbNumCuatroN.setTypeface(font);
        rbNumCuatroN2.setTypeface(font);
        rbNumCuatroN3.setTypeface(font);

        rbNumTresP.setOnCheckedChangeListener(this);
        rbNumTresN.setOnCheckedChangeListener(this);
        rbNumTresN2.setOnCheckedChangeListener(this);
        rbNumTresN3.setOnCheckedChangeListener(this);
        rbNumCuatroP.setOnCheckedChangeListener(this);
        rbNumCuatroN.setOnCheckedChangeListener(this);
        rbNumCuatroN2.setOnCheckedChangeListener(this);
        rbNumCuatroN3.setOnCheckedChangeListener(this);

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
        if (rbNumTresP.isChecked() && rbNumCuatroP.isChecked())
        {
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            Toast.makeText(getActivity(), "Correcto ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else  if (rbNumTresP.isChecked()){
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            cont_good=cont_good+1;
            irActivity();
            Toast.makeText(getActivity(), "Muy bien! ", Toast.LENGTH_SHORT).show();
            enabledNumTresadas();
        }else  if (rbNumCuatroP.isChecked()){
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            cont_intentos=cont_intentos+1;
            irActivity();
            Toast.makeText(getActivity(), "Muy bien! ", Toast.LENGTH_SHORT).show();
            enabledNumCuatroadas();
        }else if (rbNumTresN.isChecked()){
            enabledNumTresadas();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbNumTresN2.isChecked() ){
            enabledNumTresadas();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbNumTresN3.isChecked()){
            enabledNumTresadas();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbNumCuatroN.isChecked()){
            enabledNumCuatroadas();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbNumCuatroN2.isChecked()){
            enabledNumCuatroadas();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbNumCuatroN3.isChecked()){
            enabledNumCuatroadas();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }
    }

    private void irActivity() {
        if (cont_intentos>=1 && cont_good>=1){
            Intent irNivelDos=new Intent(getActivity(), Nivel5Activity.class);
            startActivity(irNivelDos);
            getActivity().finish();
        }
    }


    private void enabledNumTresadas() {
        rbNumTresP.setEnabled(false);
        rbNumTresN.setEnabled(false);
        rbNumTresN2.setEnabled(false);
        rbNumTresN3.setEnabled(false);
    }

    private void enabledNumCuatroadas() {
        rbNumCuatroP.setEnabled(false);
        rbNumCuatroN.setEnabled(false);
        rbNumCuatroN2.setEnabled(false);
        rbNumCuatroN3.setEnabled(false);
    }


}

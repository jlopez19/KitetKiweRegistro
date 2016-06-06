package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos;


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
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres.Nivel3Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizCBFragment extends Fragment implements CompoundButton.OnCheckedChangeListener{

    TextView tvPreguntaTres, tvPreguntaCuatro;
    RadioGroup rgPreTres, rgPreCuatro;
    RadioButton rbAspCP, rbAspCN, rbAspCN2, rbAspCN3, rbPalCP, rbPalCN, rbPalCN2, rbPalCN3;
    GestorBd db;
    SharedPreferences preferences;
    String userName;
    int id_user, cont_good=0, cont_fail=0, cont_intentos=0;

    public QuizCBFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_quiz_cb, container, false);
        db=new GestorBd(getActivity());
        tvPreguntaTres =(TextView) view.findViewById(R.id.pregTresVoc);
        tvPreguntaCuatro =(TextView) view.findViewById(R.id.pregCuatroVoc);
        rgPreTres =(RadioGroup) view.findViewById(R.id.rgPreguntaTres);
        rgPreCuatro =(RadioGroup) view.findViewById(R.id.rgPreguntaCuatro);
        rbAspCP =(RadioButton) view.findViewById(R.id.rbAspCP);
        rbAspCN =(RadioButton) view.findViewById(R.id.rbAspCN);
        rbAspCN2 =(RadioButton) view.findViewById(R.id.rbAspCN2);
        rbAspCN3 =(RadioButton) view.findViewById(R.id.rbAspCN3);
        rbPalCP =(RadioButton) view.findViewById(R.id.rbPalCP);
        rbPalCN =(RadioButton) view.findViewById(R.id.rbPalCN);
        rbPalCN2 =(RadioButton) view.findViewById(R.id.rbPalCN2);
        rbPalCN3 =(RadioButton) view.findViewById(R.id.rbPalCN3);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tvPreguntaTres.setTypeface(font);
        tvPreguntaCuatro.setTypeface(font);
        rbAspCP.setTypeface(font);
        rbAspCN.setTypeface(font);
        rbAspCN2.setTypeface(font);
        rbAspCN3.setTypeface(font);
        rbPalCP.setTypeface(font);
        rbPalCN.setTypeface(font);
        rbPalCN2.setTypeface(font);
        rbPalCN3.setTypeface(font);

        rbAspCP.setOnCheckedChangeListener(this);
        rbAspCN.setOnCheckedChangeListener(this);
        rbAspCN2.setOnCheckedChangeListener(this);
        rbAspCN3.setOnCheckedChangeListener(this);
        rbPalCP.setOnCheckedChangeListener(this);
        rbPalCN.setOnCheckedChangeListener(this);
        rbPalCN2.setOnCheckedChangeListener(this);
        rbPalCN3.setOnCheckedChangeListener(this);

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
        if (rbAspCP.isChecked() && rbPalCP.isChecked())
        {
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            Toast.makeText(getActivity(), "Correcto ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else  if (rbAspCP.isChecked()){
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            cont_good=cont_good+1;
            irActivity();
            Toast.makeText(getActivity(), "Muy bien! ", Toast.LENGTH_SHORT).show();
            enabledAspiradasC();
        }else  if (rbPalCP.isChecked()){
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            cont_intentos=cont_intentos+1;
            irActivity();
            Toast.makeText(getActivity(), "Muy bien! ", Toast.LENGTH_SHORT).show();
            enabledAlargadasC();
        }else if (rbAspCN.isChecked()){
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
            enabledAspiradasC();
        }else if (rbAspCN2.isChecked() ){
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
            enabledAspiradasC();
        }else if (rbAspCN3.isChecked()){
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
            enabledAspiradasC();
        }else if (rbPalCN.isChecked()){
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
            enabledAlargadasC();
        }else if (rbPalCN2.isChecked()){
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
            enabledAlargadasC();
        }else if (rbPalCN3.isChecked()){
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
            enabledAlargadasC();
        }
    }

    private void irActivity() {
        if (cont_intentos>=1 && cont_good>=1){
            Intent irNivelDos=new Intent(getActivity(), Nivel3Activity.class);
            startActivity(irNivelDos);
            getActivity().finish();
        }
    }


    private void enabledAspiradasC() {
        rbAspCP.setEnabled(false);
        rbAspCN.setEnabled(false);
        rbAspCN2.setEnabled(false);
        rbAspCN3.setEnabled(false);
    }

    private void enabledAlargadasC() {
        rbPalCP.setEnabled(false);
        rbPalCN.setEnabled(false);
        rbPalCN2.setEnabled(false);
        rbPalCN3.setEnabled(false);
    }


}

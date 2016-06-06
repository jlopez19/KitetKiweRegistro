package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveltres;


import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno.QuizBFragment;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizCoAFragment extends Fragment implements CompoundButton.OnCheckedChangeListener{


    TextView tvPreguntaUno, tvPreguntaDos;
    RadioGroup rgPreUno, rgPreDos;
    RadioButton rbColorUnoP, rbColorUnoN, rbColorUnoN2, rbColorUnoN3, rbColorDosP, rbColorDosN, rbColorDosN2, rbColorDosN3;

    GestorBd db;
    SharedPreferences preferences;
    String userName;
    int id_user, cont_good=0, cont_fail=0, cont_intentos=0;

    public QuizCoAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_quiz_co_a, container, false);

        db=new GestorBd(getActivity());

        tvPreguntaUno =(TextView) view.findViewById(R.id.pregUnoCol);
        tvPreguntaDos =(TextView) view.findViewById(R.id.pregDosCol);
        rgPreUno =(RadioGroup) view.findViewById(R.id.rgPreguntaUno);
        rgPreDos =(RadioGroup) view.findViewById(R.id.rgPreguntados);
        rbColorUnoP =(RadioButton) view.findViewById(R.id.rbColorUnoP);
        rbColorUnoN =(RadioButton) view.findViewById(R.id.rbColorUnoN);
        rbColorUnoN2 =(RadioButton) view.findViewById(R.id.rbColorUnoN2);
        rbColorUnoN3 =(RadioButton) view.findViewById(R.id.rbColorUnoN3);
        rbColorDosP =(RadioButton) view.findViewById(R.id.rbColorDosP);
        rbColorDosN =(RadioButton) view.findViewById(R.id.rbColorDosN);
        rbColorDosN2 =(RadioButton) view.findViewById(R.id.rbColorDosN2);
        rbColorDosN3 =(RadioButton) view.findViewById(R.id.rbColorDosN3);

        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tvPreguntaUno.setTypeface(font);
        tvPreguntaDos.setTypeface(font);
        rbColorUnoP.setTypeface(font);
        rbColorUnoN.setTypeface(font);
        rbColorUnoN2.setTypeface(font);
        rbColorUnoN3.setTypeface(font);
        rbColorDosP.setTypeface(font);
        rbColorDosN.setTypeface(font);
        rbColorDosN2.setTypeface(font);
        rbColorDosN3.setTypeface(font);

        rbColorUnoP.setOnCheckedChangeListener(this);
        rbColorUnoN.setOnCheckedChangeListener(this);
        rbColorUnoN2.setOnCheckedChangeListener(this);
        rbColorUnoN3.setOnCheckedChangeListener(this);
        rbColorDosP.setOnCheckedChangeListener(this);
        rbColorDosN.setOnCheckedChangeListener(this);
        rbColorDosN2.setOnCheckedChangeListener(this);
        rbColorDosN3.setOnCheckedChangeListener(this);

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
        if (rbColorUnoP.isChecked() && rbColorDosP.isChecked())
        {
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            Toast.makeText(getActivity(), "Correcto ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else  if (rbColorDosP.isChecked()){
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            cont_intentos=cont_intentos+1;
            irActivity();
            Toast.makeText(getActivity(), "Muy bien! ", Toast.LENGTH_SHORT).show();
            enabledColorDos();
        }else  if (rbColorUnoP.isChecked()){
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            cont_good=cont_good+1;
            irActivity();
            Toast.makeText(getActivity(), "Muy bien! ", Toast.LENGTH_SHORT).show();
            enabledColorUno();
        }else if (rbColorUnoN3.isChecked()){
            enabledColorUno();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbColorUnoN2.isChecked() ){
            enabledColorUno();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbColorUnoN.isChecked()){
            enabledColorUno();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbColorDosN3.isChecked()){
            enabledColorDos();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbColorDosN2.isChecked()){
            enabledColorDos();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbColorDosN.isChecked()){
            enabledColorDos();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }



    }

    private void irActivity() {
        if (cont_intentos>=1 && cont_good>=1){
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            trans.replace(R.id.fragmentCoA, new QuizCoBFragment());
            trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            trans.addToBackStack(null);
            trans.commit();
            ocultarDatos();

        }
    }

    private void ocultarDatos() {
        tvPreguntaUno.setVisibility(View.INVISIBLE);
        tvPreguntaDos.setVisibility(View.INVISIBLE);
        rgPreUno.setVisibility(View.INVISIBLE);
        rgPreDos.setVisibility(View.INVISIBLE);
        rbColorUnoP.setVisibility(View.INVISIBLE);
        rbColorUnoN.setVisibility(View.INVISIBLE);
        rbColorUnoN2.setVisibility(View.INVISIBLE);
        rbColorUnoN3.setVisibility(View.INVISIBLE);
        rbColorDosP.setVisibility(View.INVISIBLE);
        rbColorDosN.setVisibility(View.INVISIBLE);
        rbColorDosN2.setVisibility(View.INVISIBLE);
        rbColorDosN3.setVisibility(View.INVISIBLE);
    }

    private void enabledColorUno() {
        rbColorUnoP.setEnabled(false);
        rbColorUnoN.setEnabled(false);
        rbColorUnoN2.setEnabled(false);
        rbColorUnoN3.setEnabled(false);
        rgPreDos.setEnabled(false);
    }

    private void enabledColorDos() {
        rbColorDosP.setEnabled(false);
        rbColorDosN.setEnabled(false);
        rbColorDosN2.setEnabled(false);
        rbColorDosN3.setEnabled(false);
        rgPreDos.setEnabled(false);
    }
}

package com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro;


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
public class QuizNAFragment extends Fragment implements CompoundButton.OnCheckedChangeListener{

    TextView tvPreguntaUno, tvPreguntaDos;
    RadioGroup rgPreUno, rgPreDos;
    RadioButton rbNumUnoP, rbNumUnoN, rbNumUnoN2, rbNumUnoN3, rbNumDosP, rbNumDosN, rbNumDosN2, rbNumDosN3;

    GestorBd db;
    SharedPreferences preferences;
    String userName;
    int id_user, cont_good=0, cont_fail=0, cont_intentos=0;
    public QuizNAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_quiz_na, container, false);

        db=new GestorBd(getActivity());

        tvPreguntaUno =(TextView) view.findViewById(R.id.pregUnoNum);
        tvPreguntaDos =(TextView) view.findViewById(R.id.pregDosNum);
        rgPreUno =(RadioGroup) view.findViewById(R.id.rgPreguntaUno);
        rgPreDos =(RadioGroup) view.findViewById(R.id.rgPreguntados);
        rbNumUnoP =(RadioButton) view.findViewById(R.id.rbNumUnoP);
        rbNumUnoN =(RadioButton) view.findViewById(R.id.rbNumUnoN);
        rbNumUnoN2 =(RadioButton) view.findViewById(R.id.rbNumUnoN2);
        rbNumUnoN3 =(RadioButton) view.findViewById(R.id.rbNumUnoN3);
        rbNumDosP =(RadioButton) view.findViewById(R.id.rbNumDosP);
        rbNumDosN =(RadioButton) view.findViewById(R.id.rbNumDosN);
        rbNumDosN2 =(RadioButton) view.findViewById(R.id.rbNumDosN2);
        rbNumDosN3 =(RadioButton) view.findViewById(R.id.rbNumDosN3);

        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tvPreguntaUno.setTypeface(font);
        tvPreguntaDos.setTypeface(font);
        rbNumUnoP.setTypeface(font);
        rbNumUnoN.setTypeface(font);
        rbNumUnoN2.setTypeface(font);
        rbNumUnoN3.setTypeface(font);
        rbNumDosP.setTypeface(font);
        rbNumDosN.setTypeface(font);
        rbNumDosN2.setTypeface(font);
        rbNumDosN3.setTypeface(font);

        rbNumUnoP.setOnCheckedChangeListener(this);
        rbNumUnoN.setOnCheckedChangeListener(this);
        rbNumUnoN2.setOnCheckedChangeListener(this);
        rbNumUnoN3.setOnCheckedChangeListener(this);
        rbNumDosP.setOnCheckedChangeListener(this);
        rbNumDosN.setOnCheckedChangeListener(this);
        rbNumDosN2.setOnCheckedChangeListener(this);
        rbNumDosN3.setOnCheckedChangeListener(this);

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
        if (rbNumUnoP.isChecked() && rbNumDosP.isChecked())
        {
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            Toast.makeText(getActivity(), "Correcto ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else  if (rbNumDosP.isChecked()){
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            cont_intentos=cont_intentos+1;
            irActivity();
            Toast.makeText(getActivity(), "Muy bien! ", Toast.LENGTH_SHORT).show();
            enabledNumDos();
        }else  if (rbNumUnoP.isChecked()){
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            cont_good=cont_good+1;
            irActivity();
            Toast.makeText(getActivity(), "Muy bien! ", Toast.LENGTH_SHORT).show();
            enabledNumUno();
        }else if (rbNumUnoN3.isChecked()){
            enabledNumUno();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbNumUnoN2.isChecked() ){
            enabledNumUno();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbNumUnoN.isChecked()){
            enabledNumUno();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbNumDosN3.isChecked()){
            enabledNumDos();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbNumDosN2.isChecked()){
            enabledNumDos();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbNumDosN.isChecked()){
            enabledNumDos();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }



    }

    private void irActivity() {
        if (cont_intentos>=1 && cont_good>=1){
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            trans.replace(R.id.fragmentNuA, new QuizNBFragment());
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
        rbNumUnoP.setVisibility(View.INVISIBLE);
        rbNumUnoN.setVisibility(View.INVISIBLE);
        rbNumUnoN2.setVisibility(View.INVISIBLE);
        rbNumUnoN3.setVisibility(View.INVISIBLE);
        rbNumDosP.setVisibility(View.INVISIBLE);
        rbNumDosN.setVisibility(View.INVISIBLE);
        rbNumDosN2.setVisibility(View.INVISIBLE);
        rbNumDosN3.setVisibility(View.INVISIBLE);
    }

    private void enabledNumUno() {
        rbNumUnoP.setEnabled(false);
        rbNumUnoN.setEnabled(false);
        rbNumUnoN2.setEnabled(false);
        rbNumUnoN3.setEnabled(false);
        rgPreDos.setEnabled(false);
    }

    private void enabledNumDos() {
        rbNumDosP.setEnabled(false);
        rbNumDosN.setEnabled(false);
        rbNumDosN2.setEnabled(false);
        rbNumDosN3.setEnabled(false);
        rgPreDos.setEnabled(false);
    }

}

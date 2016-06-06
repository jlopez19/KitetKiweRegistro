package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos;


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
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizCAFragment extends Fragment implements CompoundButton.OnCheckedChangeListener{

    TextView tvPreguntaUno, tvPreguntaDos;
    RadioGroup rgPreUno, rgPreDos;
    RadioButton rbBasicasP, rbBasicasN, rbBasicasN2, rbBasicasN3, rbPalatalaesP, rbPalatalaesN, rbPalatalaesN2, rbPalatalaesN3;

    GestorBd db;
    SharedPreferences preferences;
    String userName;
    int id_user, cont_good=0, cont_fail=0, cont_intentos=0;

    public QuizCAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_quiz_ca, container, false);
        db=new GestorBd(getActivity());

        tvPreguntaUno =(TextView) view.findViewById(R.id.pregUnoCon);
        tvPreguntaDos =(TextView) view.findViewById(R.id.pregDosCon);
        rgPreUno =(RadioGroup) view.findViewById(R.id.rgPreguntaUnoC);
        rgPreDos =(RadioGroup) view.findViewById(R.id.rgPreguntadosC);
        rbBasicasP =(RadioButton) view.findViewById(R.id.rbBasicasP);
        rbBasicasN =(RadioButton) view.findViewById(R.id.rbBasicasN);
        rbBasicasN2 =(RadioButton) view.findViewById(R.id.rbBasicasN2);
        rbBasicasN3 =(RadioButton) view.findViewById(R.id.rbBasicasN3);
        rbPalatalaesP =(RadioButton) view.findViewById(R.id.rbPalatalesP);
        rbPalatalaesN =(RadioButton) view.findViewById(R.id.rbPalatalesN);
        rbPalatalaesN2 =(RadioButton) view.findViewById(R.id.rbPalatalesN2);
        rbPalatalaesN3 =(RadioButton) view.findViewById(R.id.rbPalatalesN3);

        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tvPreguntaUno.setTypeface(font);
        tvPreguntaDos.setTypeface(font);
        rbBasicasP.setTypeface(font);
        rbBasicasN.setTypeface(font);
        rbBasicasN2.setTypeface(font);
        rbBasicasN3.setTypeface(font);
        rbPalatalaesP.setTypeface(font);
        rbPalatalaesN.setTypeface(font);
        rbPalatalaesN2.setTypeface(font);
        rbPalatalaesN3.setTypeface(font);

        rbBasicasP.setOnCheckedChangeListener(this);
        rbBasicasN.setOnCheckedChangeListener(this);
        rbBasicasN2.setOnCheckedChangeListener(this);
        rbBasicasN3.setOnCheckedChangeListener(this);
        rbPalatalaesP.setOnCheckedChangeListener(this);
        rbPalatalaesN.setOnCheckedChangeListener(this);
        rbPalatalaesN2.setOnCheckedChangeListener(this);
        rbPalatalaesN3.setOnCheckedChangeListener(this);

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
        if (rbBasicasP.isChecked() && rbPalatalaesP.isChecked())
        {
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            Toast.makeText(getActivity(), "Correcto ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else  if (rbPalatalaesP.isChecked()){
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            cont_intentos=cont_intentos+1;
            irActivity();
            Toast.makeText(getActivity(), "Muy bien! ", Toast.LENGTH_SHORT).show();
            enabledNasales();
        }else  if (rbBasicasP.isChecked()){
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            cont_good=cont_good+1;
            irActivity();
            Toast.makeText(getActivity(), "Muy bien! ", Toast.LENGTH_SHORT).show();
            enabledOrales();
        }else if (rbBasicasN3.isChecked()){
            enabledOrales();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbBasicasN2.isChecked() ){
            enabledOrales();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbBasicasN.isChecked()){
            enabledOrales();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbPalatalaesN3.isChecked()){
            enabledNasales();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbPalatalaesN2.isChecked()){
            enabledNasales();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbPalatalaesN.isChecked()){
            enabledNasales();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }



    }

    private void irActivity() {
        if (cont_intentos>=1 && cont_good>=1){
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            trans.replace(R.id.fragmentCa, new QuizCBFragment());
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
        rbBasicasP.setVisibility(View.INVISIBLE);
        rbBasicasN.setVisibility(View.INVISIBLE);
        rbBasicasN2.setVisibility(View.INVISIBLE);
        rbBasicasN3.setVisibility(View.INVISIBLE);
        rbPalatalaesP.setVisibility(View.INVISIBLE);
        rbPalatalaesN.setVisibility(View.INVISIBLE);
        rbPalatalaesN2.setVisibility(View.INVISIBLE);
        rbPalatalaesN3.setVisibility(View.INVISIBLE);
    }

    private void enabledOrales() {
        rbBasicasP.setEnabled(false);
        rbBasicasN.setEnabled(false);
        rbBasicasN2.setEnabled(false);
        rbBasicasN3.setEnabled(false);
        rgPreDos.setEnabled(false);
    }

    private void enabledNasales() {
        rbPalatalaesP.setEnabled(false);
        rbPalatalaesN.setEnabled(false);
        rbPalatalaesN2.setEnabled(false);
        rbPalatalaesN3.setEnabled(false);
        rgPreDos.setEnabled(false);

    }

}

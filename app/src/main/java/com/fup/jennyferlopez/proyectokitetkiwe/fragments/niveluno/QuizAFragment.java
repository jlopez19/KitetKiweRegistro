package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

public class QuizAFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    TextView tvPreguntaUno, tvPreguntaDos;
    RadioGroup rgPreUno, rgPreDos;
    RadioButton rbOralesP, rbOralesN, rbOralesN2, rbOralesN3, rbNasalesP, rbNasalesN, rbNasalesN2, rbNasalesN3;

    GestorBd db;
    SharedPreferences preferences;
    String userName;
    int id_user, cont_good=0, cont_fail=0, cont_intentos=0;
    public QuizAFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_quiz_a, container, false);
        db=new GestorBd(getActivity());

        tvPreguntaUno =(TextView) view.findViewById(R.id.pregUnoVoc);
        tvPreguntaDos =(TextView) view.findViewById(R.id.pregDosVoc);
        rgPreUno =(RadioGroup) view.findViewById(R.id.rgPreguntaUno);
        rgPreDos =(RadioGroup) view.findViewById(R.id.rgPreguntados);
        rbOralesP =(RadioButton) view.findViewById(R.id.rbOralesP);
        rbOralesN =(RadioButton) view.findViewById(R.id.rbOralesN);
        rbOralesN2 =(RadioButton) view.findViewById(R.id.rbOralesN2);
        rbOralesN3 =(RadioButton) view.findViewById(R.id.rbOralesN3);
        rbNasalesP =(RadioButton) view.findViewById(R.id.rbNasalesP);
        rbNasalesN =(RadioButton) view.findViewById(R.id.rbNasalesN);
        rbNasalesN2 =(RadioButton) view.findViewById(R.id.rbNasalesN2);
        rbNasalesN3 =(RadioButton) view.findViewById(R.id.rbNasalesN3);

        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tvPreguntaUno.setTypeface(font);
        tvPreguntaDos.setTypeface(font);
        rbOralesP.setTypeface(font);
        rbOralesN.setTypeface(font);
        rbOralesN2.setTypeface(font);
        rbOralesN3.setTypeface(font);
        rbNasalesP.setTypeface(font);
        rbNasalesN.setTypeface(font);
        rbNasalesN2.setTypeface(font);
        rbNasalesN3.setTypeface(font);

        rbOralesP.setOnCheckedChangeListener(this);
        rbOralesN.setOnCheckedChangeListener(this);
        rbOralesN2.setOnCheckedChangeListener(this);
        rbOralesN3.setOnCheckedChangeListener(this);
        rbNasalesP.setOnCheckedChangeListener(this);
        rbNasalesN.setOnCheckedChangeListener(this);
        rbNasalesN2.setOnCheckedChangeListener(this);
        rbNasalesN3.setOnCheckedChangeListener(this);

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
        if (rbOralesP.isChecked() && rbNasalesP.isChecked())
        {
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            Toast.makeText(getActivity(), "Correcto ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else  if (rbNasalesP.isChecked()){
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            cont_intentos=cont_intentos+1;
            irActivity();
            Toast.makeText(getActivity(), "Muy bien! ", Toast.LENGTH_SHORT).show();
            enabledNasales();
        }else  if (rbOralesP.isChecked()){
            Puntos puntos= new Puntos(id_user, 1);
            db.insertarPuntos(puntos);
            cont_good=cont_good+1;
            irActivity();
            Toast.makeText(getActivity(), "Muy bien! ", Toast.LENGTH_SHORT).show();
            enabledOrales();
        }else if (rbOralesN3.isChecked()){
            enabledOrales();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbOralesN2.isChecked() ){
            enabledOrales();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbOralesN.isChecked()){
            enabledOrales();
            cont_good=cont_good+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbNasalesN3.isChecked()){
            enabledNasales();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbNasalesN2.isChecked()){
            enabledNasales();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }else if (rbNasalesN.isChecked()){
            enabledNasales();
            cont_intentos=cont_intentos+1;
            Toast.makeText(getActivity(), "Fallaste ", Toast.LENGTH_SHORT).show();
            irActivity();
        }



    }

    private void irActivity() {
        if (cont_intentos>=1 && cont_good>=1){
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            trans.replace(R.id.fragmentA, new QuizBFragment());
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
        rbOralesP.setVisibility(View.INVISIBLE);
        rbOralesN.setVisibility(View.INVISIBLE);
        rbOralesN2.setVisibility(View.INVISIBLE);
        rbOralesN3.setVisibility(View.INVISIBLE);
        rbNasalesP.setVisibility(View.INVISIBLE);
        rbNasalesN.setVisibility(View.INVISIBLE);
        rbNasalesN2.setVisibility(View.INVISIBLE);
        rbNasalesN3.setVisibility(View.INVISIBLE);
    }

    private void enabledOrales() {
        rbOralesP.setEnabled(false);
        rbOralesN.setEnabled(false);
        rbOralesN2.setEnabled(false);
        rbOralesN3.setEnabled(false);
        rgPreDos.setEnabled(false);
    }

    private void enabledNasales() {
        rbNasalesP.setEnabled(false);
        rbNasalesN.setEnabled(false);
        rbNasalesN2.setEnabled(false);
        rbNasalesN3.setEnabled(false);
        rgPreDos.setEnabled(false);
    }
}

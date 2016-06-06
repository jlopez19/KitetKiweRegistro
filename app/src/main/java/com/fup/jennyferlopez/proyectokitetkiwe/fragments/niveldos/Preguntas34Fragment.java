package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fup.jennyferlopez.proyectokitetkiwe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Preguntas34Fragment extends Fragment {
    String[]numeros;
    ListView lista;

    public Preguntas34Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout= inflater.inflate(R.layout.fragment_preguntas34, container, false);
        return layout;
    }



}

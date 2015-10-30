package com.tocaj.martin.dwmb2.uiFragmentMap;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tocaj.martin.dwmb2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondScreenFragment extends Fragment {


    public SecondScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_second_screen, container, false);

        initializeComponents();
        return view;
    }

    private void initializeComponents() {
        FragmentManager fm = getFragmentManager();

        MapFragment mapFragment =(MapFragment)fm.findFragmentById(R.id.fragment_map);
        SliderFragment sliderFragment =(SliderFragment)fm.findFragmentById(R.id.fragment_slider);



    }


}

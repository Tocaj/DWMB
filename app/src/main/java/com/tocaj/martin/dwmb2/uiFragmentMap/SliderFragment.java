package com.tocaj.martin.dwmb2.uiFragmentMap;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.tocaj.martin.dwmb2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SliderFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private ArrayAdapter adapter;
    private Spinner spinner;
    private SecondScreenFragment secondScreenFragment;
    private SecondScreenActivity secondScreenActivity;

   // private String[]items = new String[] {"1","5","10"}; //Alternativ till ändring i String-list

    public SliderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_slider, container, false);

        initializeComponents(view);

        return view;
    }

    public void initializeComponents(View view) {

        spinner = (Spinner) view.findViewById(R.id.spSpinner);
        adapter = ArrayAdapter.createFromResource
                (this.getActivity().getApplicationContext(), R.array.object_array, android.R.layout.simple_spinner_item);

        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        //  spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items));

        spinner.setOnItemSelectedListener(this);



        //     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //         Toast.makeText(secondScreenActivity.getApplicationContext(), "Avstånd valt: " + position, Toast.LENGTH_LONG).show();

        //    }

        //   }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

package com.tocaj.martin.dwmb2.uiFragmentMap;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;


import com.tocaj.martin.dwmb2.R;

public class SecondScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        initializeFragment();
    }

    private void initializeFragment() {
        FragmentManager fm = getFragmentManager();

        SecondScreenFragment fragment = (SecondScreenFragment)fm.findFragmentById(R.id.fragment_second_screen);

    }


}

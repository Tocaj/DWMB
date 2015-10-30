package com.tocaj.martin.dwmb2;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tocaj.martin.dwmb2.uiFragmentMap.SecondScreenFragment;

public class MainActivity extends Activity {


    private SecondScreenFragment secondScreenFragment;
    private Button btnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        initializeFragment();
    }

    private void initializeComponents() {
        btnButton = (Button) findViewById(R.id.btnButton);

        btnButton.setOnClickListener(new StartListener());

    }

    public void initializeFragment(){
        FragmentManager fm = getFragmentManager();

        SecondScreenFragment fragment = (SecondScreenFragment)fm.findFragmentById(R.id.fragment_second_screen);



}



private class StartListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        Intent intent;
        intent=new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);

    }
}


}

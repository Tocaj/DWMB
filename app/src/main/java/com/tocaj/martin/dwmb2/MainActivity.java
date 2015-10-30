package com.tocaj.martin.dwmb2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tocaj.martin.dwmb2.uiFragmentMap.SecondScreenActivity;

public class MainActivity extends Activity {


    private Button btnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    private void initializeComponents() {
        btnButton = (Button)findViewById(R.id.btnButton);

        btnButton.setOnClickListener(new StartListener());



}



private class StartListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        Intent intent;
        intent=new Intent(MainActivity.this, SecondScreenActivity.class);
        startActivity(intent);

    }
}


}

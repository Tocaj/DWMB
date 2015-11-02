package com.tocaj.martin.dwmb2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.tocaj.martin.dwmb2.uiFragmentMap.MapFragment;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener{


    private MapFragment mapFragment;
    private Button btnButton;
    private Spinner spinner;

    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
     //   initializeFragment();
    }

    private void initializeComponents() {
        btnButton = (Button) findViewById(R.id.btnButton);
        spinner = (Spinner) findViewById(R.id.spSpinner);
        adapter = ArrayAdapter.createFromResource
                (this, R.array.object_array, android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);
        //  spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items));

        spinner.setOnItemSelectedListener(this);

        btnButton.setOnClickListener(new StartListener());


    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    public void onNothingSelected(AdapterView<?> parent) {

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

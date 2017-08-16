package com.example.bogusz.bank;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Historia extends AppCompatActivity {

    private LinearLayout placeToLog;
    private ArrayList<String> read = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia);

        //init GUI
        placeToLog = (LinearLayout) findViewById(R.id.placeToLog);
        loadLog();
        showingAllText();

    }


    private void loadLog(){

        String reading;
        FileInputStream inputStream = null;
        try {
            inputStream = openFileInput(MainActivity.NAME_FILE_LOG);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(streamReader);
        StringBuffer buffer = new StringBuffer();

        try {
            while (( reading = reader.readLine() ) != null){
                buffer.append(reading);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] strings = buffer.toString().split(",");

        for (int i = 0; i<strings.length; i++) {
            read.add(strings[i]);

        }
    }

    private void showingAllText(){

        for (int i = 0; i < read.size(); i+= 3){

            LinearLayout container = new LinearLayout(this);
            TextView actualBalance = new TextView(this);
            TextView changing = new TextView(this);

            // setting TextViews
            actualBalance.setText("Stan: " + read.get(i + 2) + " $" );

            if(read.get(i + 1).toLowerCase().equals("in") ) {
                changing.setTextColor(Color.GREEN);
                changing.setText("+" + read.get(i) + " $");

            }else if(read.get(i + 1).toLowerCase().equals("out") ) {

                changing.setTextColor(Color.RED);
                changing.setText("-" + read.get(i) + " $");

            }

            actualBalance.setTextSize(TypedValue.COMPLEX_UNIT_SP , 25);
            changing.setTextSize(TypedValue.COMPLEX_UNIT_SP , 25);


            // setting container on TextViews

            container.setOrientation(LinearLayout.HORIZONTAL);

            //adding Texts to container
            ViewGroup.LayoutParams lP = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1);
            ViewGroup.LayoutParams lP2 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,2);
            actualBalance.setGravity(Gravity.LEFT);
            changing.setGravity(Gravity.RIGHT);
            actualBalance.setLayoutParams(lP2);
            changing.setLayoutParams(lP);
            container.addView(actualBalance);
            container.addView(changing);
            container.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            container.setPadding(10,2,10,2);
            //adding container to screen

            placeToLog.addView(container);


        }
    }
}

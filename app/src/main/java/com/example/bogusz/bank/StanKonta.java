package com.example.bogusz.bank;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StanKonta extends AppCompatActivity {

    private ArrayList<String> read = new ArrayList<>();

    //GUI
    TextView txValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stan_konta);

        //GUI init
        txValue =(TextView) findViewById(R.id.txValue);

        try {
            readValue();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void readValue() throws FileNotFoundException {

        String reading;
        FileInputStream inputStream = openFileInput(MainActivity.NAME_FILE);
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(streamReader);
        StringBuffer buffer = new StringBuffer();

        try {
            while (( reading = reader.readLine() ) != null){
                buffer.append(reading + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] strings = buffer.toString().split(",");

        for (int i = 0; i<strings.length; i++) {

            read.add(strings[i].replace("\n",""));

        }

        txValue.setText(read.get(1) + " $");
    }
}

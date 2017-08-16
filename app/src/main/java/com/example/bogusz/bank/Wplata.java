package com.example.bogusz.bank;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Wplata extends AppCompatActivity {

    //UI
    private SeekBar sbWplata;
    private TextView txValue;
    private Button butWplac;
    private TextView textBelka;

    //logic value
    private int valueLoad;
    private int accBalance;
    private ArrayList<String> read = new ArrayList<>(); // to text from file

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wplata);

        // UI init
        sbWplata = (SeekBar) findViewById(R.id.sbWplata);
        txValue = (TextView) findViewById(R.id.txValue);
        butWplac = (Button) findViewById(R.id.butWplac);
        textBelka = (TextView) findViewById(R.id.textBelka);

        // setting textBelka
        textBelka.setText( "Witaj " + ChooseActivity.signUzytkownik);

        firstSetUp();
    }

    private void firstSetUp(){

        //seekbar init
        sbWplata.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                valueLoad = sbWplata.getProgress() * 5;
                txValue.setText(valueLoad + " $");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //button init
        butWplac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saving balance and finish that activity
                saveAcount();
                saveHistory();
                finish();
            }
        });

        // checking account balance
        try {
            readValue();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        accBalance = Integer.parseInt(read.get(1));
    }


    public void readValue() throws FileNotFoundException {

        //function to reading text from files

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
    }


    private void saveAcount(){
        File file = new File(getFilesDir(), MainActivity.NAME_FILE);
        FileOutputStream outputStream;
        String string = read.get(0) + "," + (valueLoad + accBalance);

        try {
            outputStream = openFileOutput(MainActivity.NAME_FILE, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void saveHistory(){
        FileOutputStream outputStream = null;
        try {
            outputStream = openFileOutput(MainActivity.NAME_FILE_LOG, MODE_APPEND);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String string = "," + valueLoad +",in" + "," + accBalance;

        try {
            outputStream.write(string.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

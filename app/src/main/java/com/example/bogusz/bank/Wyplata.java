package com.example.bogusz.bank;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Wyplata extends AppCompatActivity {

    //GUI
    private TextView txValue;
    private SeekBar sbWyplata;
    private Button butWyplac;
    private TextView textBelka;

    //logic value
    private ArrayList<String> read = new ArrayList<>(); // to text from file
    private int accBalance;
    private int getValue;       // how much you what to get from bank

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyplata);

        // GUI init
        txValue = (TextView) findViewById(R.id.txValue);
        sbWyplata  = (SeekBar) findViewById(R.id.sbWyplata);
        butWyplac = (Button) findViewById(R.id.butWyplac);
        textBelka = (TextView) findViewById(R.id.textBelka);

        // setting textBelka
        textBelka.setText( "Witaj " + ChooseActivity.signUzytkownik);

        try {
            readValue();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        setUpSbWyplata();
        butWyplac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValue = sbWyplata.getProgress();
                saveAcount();
                saveHistory();
                finish();
            }
        });


    }

    private void readValue() throws FileNotFoundException {

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

        accBalance = Integer.parseInt(read.get(1)); // read how much have you on you account
    }

    private void setUpSbWyplata(){

        sbWyplata.setMax(accBalance);
        sbWyplata.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txValue.setText(seekBar.getProgress() + " $");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void saveAcount(){
        File file = new File(getFilesDir(), MainActivity.NAME_FILE);
        FileOutputStream outputStream;
        String string = read.get(0) + "," + (accBalance - getValue);

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

        String string = "," + getValue +",out" + "," + accBalance;

        try {
            outputStream.write(string.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

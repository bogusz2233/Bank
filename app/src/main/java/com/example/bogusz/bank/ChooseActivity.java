package com.example.bogusz.bank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChooseActivity extends AppCompatActivity {


    private ListView lista;
    private TextView textBelka;

    private static String signUzytkownik;
    public static String tagLog = "tagi";

    private static int DP;

    private static boolean udaloSie;
    //Baza danych

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        makeDP();
        //GUI init
        lista =(ListView) findViewById(R.id.lista);
        textBelka = (TextView) findViewById(R.id.textBelka);

        stworzPrzyciski(4);
        textBelka.setText(( signUzytkownik + ", witaj w naszym banku").toUpperCase());


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(tagLog, "onStart" );
    }


    private void makeDP() {
        DisplayMetrics dmetric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dmetric);
        DP = (int) dmetric.density;
    }

    private View.OnClickListener imWylogujOnClicked() {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                przejdzDoMain();
            }
        };

        return clickListener;
    }

    private void przejdzDoMain() {
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void stworzPrzyciski(int licz_przyc) {


        // creating and adding buttons

        ArrayList<CustomButtonInfo> custom = new ArrayList<CustomButtonInfo>();


        custom.add(new CustomButtonInfo("Stan Konta", R.drawable.dolar));
        custom.add(new CustomButtonInfo("Wpłać", R.drawable.wplac));
        custom.add(new CustomButtonInfo("Wypłać", R.drawable.wyplac));
        custom.add(new CustomButtonInfo("MiniGra", R.drawable.controller));

        CuBuAdapter cuBuAdapter = new CuBuAdapter(this,custom);
        lista.setAdapter(cuBuAdapter);


    }

    private void setUpPrzyciski(){

    }



    private void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public static int getDP() {
        return DP;
    }

    public static void setDP(int DP) {
        ChooseActivity.DP = DP;
    }

    public static String getSignUzytkownik() {
        return signUzytkownik;
    }

    public static void setSignUzytkownik(String signUzytkownik) {
        ChooseActivity.signUzytkownik = signUzytkownik;
    }
}


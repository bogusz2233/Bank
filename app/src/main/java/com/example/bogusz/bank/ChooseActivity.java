package com.example.bogusz.bank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ChooseActivity extends AppCompatActivity {


    private LinearLayout scrollView;
    private TextView textBelka;
    private ArrayList<CustomButton> customButtons = new ArrayList<>();

    private static String signUzytkownik;
    public static String tagLog = "tagi";

    private static int DP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        makeDP();
        //GUI init
        scrollView =(LinearLayout) findViewById(R.id.scrollView);
        textBelka = (TextView) findViewById(R.id.textBelka);



        //making button fragments
        for(int i = 0; i < 5; i++) {
            customButtons.add(new CustomButton());
        }
        for(int i = 0; i < customButtons.size(); i++) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(scrollView.getId(), customButtons.get(i));
            ft.commit();

        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(tagLog, "onStart" );
        settingBut();
    }


    private void makeDP() {
        //that function getting size of screen in dp
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




    public static int getDP() {
        return DP;
    }

    public static void setSignUzytkownik(String signUzytkownik) {
        ChooseActivity.signUzytkownik = signUzytkownik;
    }
    private void settingBut(){
        //that function setUp all buttons

        //first button
        customButtons.get(0).changeText(R.string.account_balance);
        customButtons.get(0).changeImage(R.drawable.stan);

        //second button
        customButtons.get(1).changeText(R.string.deposit);
        customButtons.get(1).changeImage(R.drawable.wplac);


        //third button
        customButtons.get(2).changeText(R.string.payoff);
        customButtons.get(2).changeImage(R.drawable.wyplac);

        //fourth button
        customButtons.get(3).changeText(R.string.mini_game);
        customButtons.get(3).changeImage(R.drawable.controller);

        // fifth button
        customButtons.get(4).changeText(R.string.about_us);
        customButtons.get(4).changeImage(R.drawable.dolar);
    }
}


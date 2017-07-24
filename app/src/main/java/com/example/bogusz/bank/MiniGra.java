package com.example.bogusz.bank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MiniGra extends AppCompatActivity {

    private ImageView imWyloguj;
    private FrameLayout pulpit;
    private TextView tvPozycja;
    private ImageView ivGwiazdka;

    private ArrayList<Star> stars = new ArrayList<Star>();

    private Timer czasAnimacji;
    private int czasAnim = 1;
    float x = 0;
    float y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_gra);


        imWyloguj = (ImageView) findViewById(R.id.imWyloguj);
        imWyloguj.setOnClickListener(imWylogujOnClicked());

        pulpit = (FrameLayout) findViewById(R.id.pulpit);
        pulpit.setOnTouchListener(onTouchE());

        tvPozycja = (TextView) findViewById(R.id.tvPozycja);
        ivGwiazdka = (ImageView) findViewById(R.id.ivGwiazdka);


        czasAnimacji = new Timer();

        czasAnimacji.schedule(new TimerTask() {
                                  @Override
                                  public void run() {
                                      grawitacjaStars();
                                      czasAnim ++;
                                  }
                              }
                ,0, 50);
    }



    @Override
    protected void onPause() {
        czasAnimacji.cancel();
        Log.i(ChooseActivity.tagLog,"onPause - MiniGra");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(ChooseActivity.tagLog,"onStop - MiniGra");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(ChooseActivity.tagLog,"onDestroy - MiniGra");
        super.onDestroy();
    }

    private View.OnClickListener imWylogujOnClicked(){
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                przejdzDoMain();
            }
        };

        return clickListener;
    }


    private View.OnTouchListener onTouchE(){
        View.OnTouchListener touch = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                x = motionEvent.getX();
                y = motionEvent.getY();


                ivGwiazdka.setY(y - ivGwiazdka.getHeight() / 2);
                ivGwiazdka.setX(x - ivGwiazdka.getWidth() / 2);


                if (czasAnim > 1) {

                    CharSequence wiadomosc = "x = " +(int)x + " ; y = " + (int)y + " arraylist= " + stars.size();
                    tvPozycja.setText(wiadomosc);

                    stworzGwiazde(x, y);
                    czasAnim = 0;
                }
                return true;
            }

        };

        return touch;
    }
    private void przejdzDoMain(){
        Intent intent;
        intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void grawitacjaStars(){




        for (int i=0; i<stars.size(); i++){

            stars.get(i).grawitacja();

            if(stars.get(i).getY() > 2000) {
                stars.remove(i);
            }

        }
    }

    private void stworzGwiazde(float x,float y) {
        if(stars.size() < 200) {
            stars.add(new Star(getApplicationContext(), x, y));
            pulpit.addView(stars.get(stars.size() - 1).getRootView());
            stars.get(stars.size() - 1).setSize(30);
        }

    }



}

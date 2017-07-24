package com.example.bogusz.bank;

import android.content.Context;

import java.util.Random;

/**
 * Created by bogusz on 18.07.17.
 */

public class Star extends android.support.v7.widget.AppCompatImageView {

    private float grav;

    public Star(Context context,float x, float y) {
        super(context);
        setImageResource(R.drawable.dolar);
        setX(x);
        setY(y);

        Random rand = new Random();

        grav = (float) (0.5 + rand.nextFloat() * 5) * 2;    // nextFloat gives numbers from 0 to 1.0
    }

    public void setSize(int size){
        getLayoutParams().width = size * ChooseActivity.getDP();
        getLayoutParams().height= size * ChooseActivity.getDP();
    }

    public void grawitacja(){
        setY( getY() + grav * ChooseActivity.getDP() );
    }


}

package com.example.bogusz.bank;

import android.content.Context;

/**
 * Created by bogusz on 18.07.17.
 */

public class Star extends android.support.v7.widget.AppCompatImageView {

    private float grav = 0.8f;

    public Star(Context context,float x, float y) {
        super(context);
        setImageResource(R.drawable.dolar);
        setX(x);
        setY(y);
    }

    public void setSize(int size){
        getLayoutParams().width = size * ChooseActivity.getDP();
        getLayoutParams().height= size * ChooseActivity.getDP();
    }

    public void grawitacja(){
        grav += 0.5;
        setY( getY() + grav * ChooseActivity.getDP() );
    }


}

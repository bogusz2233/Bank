package com.example.bogusz.bank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomButton extends Fragment {

    private ImageView imCusBut;
    private TextView txCusBut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_button, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(ChooseActivity.tagLog,"CustomButton - onActivityCreated");

        imCusBut = (ImageView) this.getView().findViewById(R.id.imCusBut);
        txCusBut = (TextView) this.getView().findViewById(R.id.txCusBut);

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(ChooseActivity.tagLog,"CustomButton - onStart");


    }

    public void changeText(String text){

        String string = text.toUpperCase();

        txCusBut.setText(string);
    }
    public void changeText(int resId){
        String string = getString(resId);
        string = string.toUpperCase();

        txCusBut.setText(string);
    }

    public void changeImage(int idImage){

        imCusBut.setImageResource(idImage);
    }
}

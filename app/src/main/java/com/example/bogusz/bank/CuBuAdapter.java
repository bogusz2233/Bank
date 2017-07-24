package com.example.bogusz.bank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bogusz on 23.07.17.
 */

public class CuBuAdapter extends ArrayAdapter<CustomButtonInfo>{

    public CuBuAdapter(@NonNull Context context, ArrayList<CustomButtonInfo> objects) {
        super(context, 0, objects);
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.custom_button, parent, false);
        }

        // wyciąga element z tablicy
        CustomButtonInfo but = getItem(position);

        // wyciąga i ustawia tekst
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.txCustomButton);
        nameTextView.setText(but.getText());

        // wyciąga i ustawia zdjecie
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.imCustomButton);
        iconView.setImageResource(but.getImageId());

        return listItemView;

    }



}


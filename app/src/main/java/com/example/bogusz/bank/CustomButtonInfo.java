package com.example.bogusz.bank;

/**
 * Created by bogusz on 23.07.17.
 */

public class CustomButtonInfo {

    private String text;
    private int imageId;

    public CustomButtonInfo(String text, int imageId){
        this.text = text;
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

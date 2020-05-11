package com.lbg.lbgtest.model;

import com.google.gson.annotations.SerializedName;

public class AlbumImage {

    @SerializedName("#text")
    private String text;

    @SerializedName("size")
    private String size;

    public String getText() {
        return text;
    }

    public String getSize() {
        return size;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

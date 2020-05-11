package com.lbg.lbgtest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Artist {

    @SerializedName("name")
    private String name;

    @SerializedName("listeners")
    private String listeners;

    @SerializedName("url")
    private String url;

    @SerializedName("image")
    private List<AlbumImage> imageList;

    public String getName() {
        return name;
    }

    public String getListeners() {
        return listeners;
    }

    public String getUrl() {
        return url;
    }

    public List<AlbumImage> getImageList() {
        return imageList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImageList(List<AlbumImage> imageList) {
        this.imageList = imageList;
    }
}

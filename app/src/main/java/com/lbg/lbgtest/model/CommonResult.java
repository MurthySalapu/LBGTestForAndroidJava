package com.lbg.lbgtest.model;

public class CommonResult {

    private String name;

    private String url;

    private String listeners;

    private  String artist;

    private String imageUrl;

    private int type;


    public CommonResult(String name, String url, String listeners, String artist,String imageUrl,int type) {
        this.name = name;
        this.url = url;
        this.listeners = listeners;
        this.artist = artist;
        this.imageUrl = imageUrl;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getListeners() {
        return listeners;
    }

    public String getArtist() {
        return artist;
    }

    public int getType() {
        return type;
    }
}

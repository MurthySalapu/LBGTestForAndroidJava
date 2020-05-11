package com.lbg.lbgtest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlbumMatcher {

    @SerializedName("album")
    private List<Album> albums;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}

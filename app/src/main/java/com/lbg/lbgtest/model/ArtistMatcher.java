package com.lbg.lbgtest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistMatcher {

    @SerializedName("artist")
    private List<Artist> artist;

    public List<Artist> getArtist() {
        return artist;
    }

    public void setArtist(List<Artist> artist) {
        this.artist = artist;
    }
}

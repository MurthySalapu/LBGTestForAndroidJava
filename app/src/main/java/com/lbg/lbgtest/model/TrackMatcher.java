package com.lbg.lbgtest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrackMatcher {

    @SerializedName("track")
    private List<Track> track;

    public List<Track> getTrack() {
        return track;
    }

    public void setTrack(List<Track> track) {
        this.track = track;
    }
}

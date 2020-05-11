package com.lbg.lbgtest.model;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("albummatches")
    private AlbumMatcher albumMatcher;

    @SerializedName("artistmatches")
    private ArtistMatcher artistMatcher;

    @SerializedName("trackmatches")
    private TrackMatcher trackMatcher;

    public AlbumMatcher getAlbumMatcher() {
        return albumMatcher;
    }

    public ArtistMatcher getArtistMatcher() {
        return artistMatcher;
    }

    public TrackMatcher getTrackMatcher() {
        return trackMatcher;
    }

    public void setAlbumMatcher(AlbumMatcher albumMatcher) {
        this.albumMatcher = albumMatcher;
    }

    public void setArtistMatcher(ArtistMatcher artistMatcher) {
        this.artistMatcher = artistMatcher;
    }

    public void setTrackMatcher(TrackMatcher trackMatcher) {
        this.trackMatcher = trackMatcher;
    }
}

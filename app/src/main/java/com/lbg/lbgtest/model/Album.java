package com.lbg.lbgtest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Album {

  @SerializedName("name")
  private String name;

  @SerializedName("artist")
  private String artist;

  @SerializedName("url")
  private String url;

  @SerializedName("image")
  private List<AlbumImage> imageList;

  public String getName() {
    return name;
  }

  public String getArtist() {
    return artist;
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

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setImageList(List<AlbumImage> imageList) {
    this.imageList = imageList;
  }
}

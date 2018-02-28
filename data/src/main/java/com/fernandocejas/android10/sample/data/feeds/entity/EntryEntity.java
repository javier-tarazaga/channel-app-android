package com.fernandocejas.android10.sample.data.feeds.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class EntryEntity {
  private final String id;

  private String title;
  private String summary;
  @SerializedName("thumbnail") private List<ThumbnailEntity> thumbnailEntityList;

  public EntryEntity(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public List<ThumbnailEntity> getThumbnailEntityList() {
    return thumbnailEntityList;
  }

  public void setThumbnailEntityList(List<ThumbnailEntity> thumbnailEntityList) {
    this.thumbnailEntityList = thumbnailEntityList;
  }

  public static class ThumbnailEntity {
    private final String url;

    public ThumbnailEntity(String url) {
      this.url = url;
    }

    public String getUrl() {
      return url;
    }
  }
}

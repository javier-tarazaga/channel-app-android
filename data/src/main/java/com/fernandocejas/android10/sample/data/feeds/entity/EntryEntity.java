package com.fernandocejas.android10.sample.data.feeds.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class EntryEntity {
  private final String id;

  private String title;
  private SummaryEntity summary;
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

  public SummaryEntity getSummary() {
    return summary;
  }

  public void setSummary(SummaryEntity summary) {
    this.summary = summary;
  }

  public List<ThumbnailEntity> getThumbnailEntityList() {
    return thumbnailEntityList;
  }

  public void setThumbnailEntityList(List<ThumbnailEntity> thumbnailEntityList) {
    this.thumbnailEntityList = thumbnailEntityList;
  }

  public static class SummaryEntity {
    private final String content;

    public SummaryEntity(String content) {
      this.content = content;
    }

    public String getContent() {
      return content;
    }
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

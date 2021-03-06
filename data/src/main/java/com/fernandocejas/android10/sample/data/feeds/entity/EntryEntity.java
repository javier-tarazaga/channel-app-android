package com.fernandocejas.android10.sample.data.feeds.entity;

import android.support.annotation.Nullable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class EntryEntity {
  private final String id;

  private String title;
  private ContentEntity content;
  private ContentEntity summary;
  private VisualEntity visual;
  @SerializedName("thumbnail") private List<ThumbnailEntity> thumbnailEntityList;

  public EntryEntity(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  @Nullable public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Nullable public ContentEntity getContent() {
    return content;
  }

  public void setContent(ContentEntity content) {
    this.content = content;
  }

  @Nullable public ContentEntity getSummary() {
    return summary;
  }

  public void setSummary(ContentEntity summary) {
    this.summary = summary;
  }

  @Nullable public VisualEntity getVisual() {
    return visual;
  }

  public void setVisual(VisualEntity visual) {
    this.visual = visual;
  }

  @Nullable public List<ThumbnailEntity> getThumbnailEntityList() {
    return thumbnailEntityList;
  }

  public void setThumbnailEntityList(List<ThumbnailEntity> thumbnailEntityList) {
    this.thumbnailEntityList = thumbnailEntityList;
  }

  public static class ContentEntity {
    private final String content;

    public ContentEntity(String content) {
      this.content = content;
    }

    public String getContent() {
      return content;
    }
  }

  public static class VisualEntity {
    private final String url;

    public VisualEntity(String url) {
      this.url = url;
    }

    public String getUrl() {
      return url;
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

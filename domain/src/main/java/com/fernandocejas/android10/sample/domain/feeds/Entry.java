package com.fernandocejas.android10.sample.domain.feeds;

import android.support.annotation.Nullable;
import java.util.List;

public class Entry {
  private final String id;

  private String title;
  private Content content;
  private Content summary;
  private Visual visual;
  private List<Thumbnail> thumbnailList;

  public Entry(String id) {
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

  @Nullable public Content getContent() {
    return content;
  }

  public void setContent(Content content) {
    this.content = content;
  }

  @Nullable public Content getSummary() {
    return summary;
  }

  public void setSummary(Content summary) {
    this.summary = summary;
  }

  @Nullable public Visual getVisual() {
    return visual;
  }

  public void setVisual(Visual visual) {
    this.visual = visual;
  }

  @Nullable public List<Thumbnail> getThumbnailList() {
    return thumbnailList;
  }

  public void setThumbnailList(List<Thumbnail> thumbnailList) {
    this.thumbnailList = thumbnailList;
  }

  public static class Content {
    private final String content;

    public Content(String content) {
      this.content = content;
    }

    public String getContent() {
      return content;
    }
  }

  public static class Visual {
    private final String url;

    public Visual(String url) {
      this.url = url;
    }

    public String getUrl() {
      return url;
    }
  }

  public static class Thumbnail {
    private final String url;

    public Thumbnail(String url) {
      this.url = url;
    }

    public String getUrl() {
      return url;
    }
  }
}

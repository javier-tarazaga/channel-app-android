package com.fernandocejas.android10.sample.domain.feeds;

import java.util.List;

public class Entry {
  private final String id;

  private String title;
  private Summary summary;
  private List<Thumbnail> thumbnailList;

  public Entry(String id) {
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

  public Summary getSummary() {
    return summary;
  }

  public void setSummary(Summary summary) {
    this.summary = summary;
  }

  public List<Thumbnail> getThumbnailList() {
    return thumbnailList;
  }

  public void setThumbnailList(List<Thumbnail> thumbnailList) {
    this.thumbnailList = thumbnailList;
  }

  public static class Summary {
    private final String content;

    public Summary(String content) {
      this.content = content;
    }

    public String getContent() {
      return content;
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

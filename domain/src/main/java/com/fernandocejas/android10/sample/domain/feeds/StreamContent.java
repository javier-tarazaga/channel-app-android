package com.fernandocejas.android10.sample.domain.feeds;

import java.util.List;

public class StreamContent {
  private final String id;
  private String title;
  private List<Entry> entryEntityList;

  public StreamContent(String id) {
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

  public List<Entry> getEntryEntityList() {
    return entryEntityList;
  }

  public void setEntryList(List<Entry> entryEntityList) {
    this.entryEntityList = entryEntityList;
  }
}

package com.fernandocejas.android10.sample.data.feeds.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class StreamContentEntity {
  private final String id;
  private String title;
  @SerializedName("items") private List<EntryEntity> entryEntityList;

  public StreamContentEntity(String id) {
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

  public List<EntryEntity> getEntryEntityList() {
    return entryEntityList;
  }

  public void setEntryEntityList(List<EntryEntity> entryEntityList) {
    this.entryEntityList = entryEntityList;
  }
}

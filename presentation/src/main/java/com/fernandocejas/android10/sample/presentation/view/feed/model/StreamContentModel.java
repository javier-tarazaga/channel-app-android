package com.fernandocejas.android10.sample.presentation.view.feed.model;

import java.util.List;

public class StreamContentModel {

  private final String id;
  private String title;
  private List<EntryModel> entryModelList;

  public StreamContentModel(String id) {
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

  public List<EntryModel> getEntryModelList() {
    return entryModelList;
  }

  public void setEntryModelList(List<EntryModel> entryModelList) {
    this.entryModelList = entryModelList;
  }
}

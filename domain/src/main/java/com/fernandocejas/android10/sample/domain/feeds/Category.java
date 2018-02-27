package com.fernandocejas.android10.sample.domain.feeds;

public class Category {
  private final String id;
  private final String label;
  private String description;

  public Category(String id, String label) {
    this.id = id;
    this.label = label;
  }

  public String getId() {
    return id;
  }

  public String getLabel() {
    return label;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}

package com.fernandocejas.android10.sample.domain.feeds;

import java.util.List;

public class Subscription {
  private final String id;
  private final String title;
  private final String website;

  private List<Category> categories;
  private List<String> topics;

  private String iconUrl;
  private String visualUrl;

  public Subscription(String id, String title, String website) {
    this.id = id;
    this.title = title;
    this.website = website;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getWebsite() {
    return website;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  public List<String> getTopics() {
    return topics;
  }

  public void setTopics(List<String> topics) {
    this.topics = topics;
  }

  public String getIconUrl() {
    return iconUrl;
  }

  public void setIconUrl(String iconUrl) {
    this.iconUrl = iconUrl;
  }

  public String getVisualUrl() {
    return visualUrl;
  }

  public void setVisualUrl(String visualUrl) {
    this.visualUrl = visualUrl;
  }
}

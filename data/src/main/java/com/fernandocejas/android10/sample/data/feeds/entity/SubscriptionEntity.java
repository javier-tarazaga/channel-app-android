package com.fernandocejas.android10.sample.data.feeds.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SubscriptionEntity {
  private final String id;
  private final String title;
  private final String website;

  private List<CategoryEntity> categories;
  private List<String> topics;

  @SerializedName("sortId") private String sortId;

  private String iconUrl;
  private String visualUrl;

  public SubscriptionEntity(String id, String title, String website) {
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

  public List<CategoryEntity> getCategories() {
    return categories;
  }

  public void setCategories(List<CategoryEntity> categories) {
    this.categories = categories;
  }

  public List<String> getTopics() {
    return topics;
  }

  public void setTopics(List<String> topics) {
    this.topics = topics;
  }

  public String getSortId() {
    return sortId;
  }

  public void setSortId(String sortId) {
    this.sortId = sortId;
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

package com.fernandocejas.android10.sample.domain.feeds;

import java.util.List;

public class Category {

  private final String id;
  private final String title;

  private List<Subscription> subscriptionList;

  public Category(String id, String title) {
    this.id = id;
    this.title = title;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setSubscriptionList(List<Subscription> subscriptionList) {
    this.subscriptionList = subscriptionList;
  }

  public List<Subscription> getSubscriptionList() {
    return subscriptionList;
  }
}

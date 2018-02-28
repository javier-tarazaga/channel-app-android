package com.fernandocejas.android10.sample.domain.feeds;

import java.util.ArrayList;
import java.util.List;

public class Category {

  private final String id;
  private final String title;

  private List<Subscription> subscriptionList = new ArrayList<>();

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

  public List<Subscription> getSubscriptionList() {
    return subscriptionList;
  }
}

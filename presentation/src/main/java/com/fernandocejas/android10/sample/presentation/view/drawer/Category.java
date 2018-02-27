package com.fernandocejas.android10.sample.presentation.view.drawer;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import java.util.List;
import java.util.Objects;

public class Category extends ExpandableGroup<Feed> {

  public Category(String title, List<Feed> items) {
    super(title, items);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Category)) return false;

    Category category = (Category) o;

    return Objects.equals(getTitle(), category.getTitle());

  }

  @Override
  public int hashCode() {
    return getTitle().hashCode();
  }
}


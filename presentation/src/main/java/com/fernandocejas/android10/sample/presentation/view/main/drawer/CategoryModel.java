package com.fernandocejas.android10.sample.presentation.view.main.drawer;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import java.util.List;
import java.util.Objects;

public class CategoryModel extends ExpandableGroup<FeedModel> {

  public CategoryModel(String title, List<FeedModel> items) {
    super(title, items);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CategoryModel)) return false;

    CategoryModel categoryModel = (CategoryModel) o;

    return Objects.equals(getTitle(), categoryModel.getTitle());

  }

  @Override
  public int hashCode() {
    return getTitle().hashCode();
  }
}


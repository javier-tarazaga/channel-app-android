package com.fernandocejas.android10.sample.presentation.view.drawer;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import java.util.List;
import java.util.Objects;

public class Genre extends ExpandableGroup<Artist> {

  public Genre(String title, List<Artist> items) {
    super(title, items);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Genre)) return false;

    Genre genre = (Genre) o;

    return Objects.equals(getTitle(), genre.getTitle());

  }

  @Override
  public int hashCode() {
    return getTitle().hashCode();
  }
}


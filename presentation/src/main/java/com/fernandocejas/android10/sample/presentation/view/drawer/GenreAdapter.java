package com.fernandocejas.android10.sample.presentation.view.drawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fernandocejas.android10.sample.presentation.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import java.util.List;

public class GenreAdapter extends ExpandableRecyclerViewAdapter<GenreViewHolder, ArtistViewHolder> {

  public OnArtistItemClickListener listener;

  public GenreAdapter(List<? extends ExpandableGroup> groups) {
    super(groups);
  }

  @Override
  public GenreViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.row_item_genre, parent, false);
    return new GenreViewHolder(view);
  }

  @Override
  public ArtistViewHolder onCreateChildViewHolder(final ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.row_item_artist, parent, false);

    return new ArtistViewHolder(view);
  }

  @Override
  public void onBindChildViewHolder(ArtistViewHolder holder, int flatPosition,
      ExpandableGroup group, int childIndex) {

    final Artist artist = ((Genre) group).getItems().get(childIndex);
    holder.setArtistName(artist.getName());
    holder.setArtistIcon(artist.getIconResId());
    holder.setOnArtistClickListener(new OnArtistClickListener() {
      @Override public boolean onArtistClick(int flatPos) {
        if (listener != null) {
          listener.onArtistClick(artist);
          return true;
        }

        return false;
      }
    });
  }

  @Override
  public void onBindGroupViewHolder(GenreViewHolder holder, int flatPosition,
      ExpandableGroup group) {

    holder.setGenreTitle(group);
  }

  public void setOnArticleItemClickListener(OnArtistItemClickListener listener) {
    this.listener = listener;
  }
}

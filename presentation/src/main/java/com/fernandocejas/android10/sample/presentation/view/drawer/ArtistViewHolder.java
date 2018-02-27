package com.fernandocejas.android10.sample.presentation.view.drawer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.fernandocejas.android10.sample.presentation.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class ArtistViewHolder extends ChildViewHolder {

  private TextView childTextView;
  private ImageView icon;

  public ArtistViewHolder(View itemView) {
    super(itemView);
    childTextView = itemView.findViewById(R.id.list_item_artist_name);
    icon = itemView.findViewById(R.id.list_item_genre_icon);
  }

  public void setArtistName(String name) {
    childTextView.setText(name);
  }

  public void setArtistIcon(int resId) {
    icon.setBackgroundResource(resId);
  }
}
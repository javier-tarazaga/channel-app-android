package com.fernandocejas.android10.sample.presentation.view.main.drawer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.fernandocejas.android10.sample.presentation.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class FeedViewHolder extends ChildViewHolder implements View.OnClickListener {

  @BindView(R.id.list_item_artist_name) TextView childTextView;
  @BindView(R.id.list_item_genre_icon) ImageView iconView;

  private OnFeedClickListener listener;

  public FeedViewHolder(View itemView) {
    super(itemView);

    ButterKnife.bind(this, itemView);
    itemView.setOnClickListener(this);
  }

  public void setSubscriptionName(String name) {
    childTextView.setText(name);
  }

  public void setSubscriptionIcon(String iconUrl) {
    Glide.with(itemView).load(iconUrl).into(iconView);
  }

  @Override public void onClick(View view) {
    listener.onFeedClick(getAdapterPosition());
  }

  public void setOnSubscriptionClickListener(OnFeedClickListener listener) {
    this.listener = listener;
  }
}
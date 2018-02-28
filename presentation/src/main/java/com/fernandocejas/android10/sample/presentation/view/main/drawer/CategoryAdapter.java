package com.fernandocejas.android10.sample.presentation.view.main.drawer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fernandocejas.android10.sample.presentation.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import java.util.List;

public class CategoryAdapter extends ExpandableRecyclerViewAdapter<CategoryViewHolder, FeedViewHolder> {
  private OnFeedItemClickListener listener;

  public CategoryAdapter(List<? extends ExpandableGroup> groups) {
    super(groups);
  }

  @Override public CategoryViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_category, parent, false);
    return new CategoryViewHolder(view);
  }

  @Override public FeedViewHolder onCreateChildViewHolder(final ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_subscription, parent, false);

    return new FeedViewHolder(view);
  }

  @Override
  public void onBindChildViewHolder(FeedViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {

    final FeedModel feedModel = ((CategoryModel) group).getItems().get(childIndex);
    holder.setSubscriptionName(feedModel.getTitle());
    holder.setSubscriptionIcon(feedModel.getIconUrl());
    holder.setOnSubscriptionClickListener(new OnFeedClickListener() {
      @Override public boolean onFeedClick(int flatPos) {
        if (listener != null) {
          listener.onFeedItemClick(feedModel);
          return true;
        }

        return false;
      }
    });
  }

  @Override public void onBindGroupViewHolder(CategoryViewHolder holder, int flatPosition, ExpandableGroup group) {
    holder.setGenreTitle(group);
  }

  public void setOnArticleItemClickListener(OnFeedItemClickListener listener) {
    this.listener = listener;
  }
}

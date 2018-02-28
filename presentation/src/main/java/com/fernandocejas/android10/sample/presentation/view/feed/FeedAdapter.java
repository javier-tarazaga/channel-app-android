/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.sample.presentation.view.feed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.view.feed.model.EntryModel;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * Adapter that manages a collection of {@link EntryModel}.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.EntryViewHolder> {

  public interface OnItemClickListener {
    void onEntryItemClicked(EntryModel userModel);
  }

  private List<EntryModel> entryList;
  private final LayoutInflater layoutInflater;

  private OnItemClickListener onItemClickListener;

  @Inject FeedAdapter(Context context) {
    this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.entryList = Collections.emptyList();
  }

  @Override public int getItemCount() {
    return (this.entryList != null) ? this.entryList.size() : 0;
  }

  @Override public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.row_entry, parent, false);
    return new EntryViewHolder(view);
  }

  @Override public void onBindViewHolder(EntryViewHolder holder, final int position) {
    final EntryModel entryModel = this.entryList.get(position);
    holder.tv_title.setText(entryModel.getTitle());

    RequestOptions requestOptions = new RequestOptions();
    requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
    Glide.with(holder.itemView)
        .load(entryModel.getImageUrl())
        .apply(requestOptions)
        .into(holder.iv_thumbnail);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (FeedAdapter.this.onItemClickListener != null) {
          FeedAdapter.this.onItemClickListener.onEntryItemClicked(entryModel);
        }
      }
    });
  }

  @Override public long getItemId(int position) {
    return position;
  }

  void setEntryList(Collection<EntryModel> entryList) {
    this.validateEntryList(entryList);
    this.entryList = (List<EntryModel>) entryList;
    this.notifyDataSetChanged();
  }

  void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  private void validateEntryList(Collection<EntryModel> entryList) {
    if (entryList == null) {
      throw new IllegalArgumentException("The list cannot be null");
    }
  }

  static class EntryViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_title) TextView tv_title;
    @BindView(R.id.iv_thumbnail) ImageView iv_thumbnail;

    EntryViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}

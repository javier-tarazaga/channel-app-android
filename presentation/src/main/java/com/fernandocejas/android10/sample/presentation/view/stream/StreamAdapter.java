/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.sample.presentation.view.stream;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.view.stream.model.EntryModel;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * Adapter that manages a collection of {@link EntryModel}.
 */
public class StreamAdapter extends RecyclerView.Adapter<StreamAdapter.EntryViewHolder> {

  public interface OnItemClickListener {
    void onEntryItemClicked(EntryModel userModel);
  }

  private List<EntryModel> entryList;
  private final LayoutInflater layoutInflater;

  private OnItemClickListener onItemClickListener;

  @Inject StreamAdapter(Context context) {
    this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.entryList = Collections.emptyList();
  }

  @Override public int getItemCount() {
    return (this.entryList != null) ? this.entryList.size() : 0;
  }

  @Override public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.row_user, parent, false);
    return new EntryViewHolder(view);
  }

  @Override public void onBindViewHolder(EntryViewHolder holder, final int position) {
    final EntryModel entryModel = this.entryList.get(position);
    holder.textViewTitle.setText(entryModel.getTitle());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (StreamAdapter.this.onItemClickListener != null) {
          StreamAdapter.this.onItemClickListener.onEntryItemClicked(entryModel);
        }
      }
    });
  }

  @Override public long getItemId(int position) {
    return position;
  }

  public void setEntryList(Collection<EntryModel> entryList) {
    this.validateEntryList(entryList);
    this.entryList = (List<EntryModel>) entryList;
    this.notifyDataSetChanged();
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  private void validateEntryList(Collection<EntryModel> entryList) {
    if (entryList == null) {
      throw new IllegalArgumentException("The list cannot be null");
    }
  }

  static class EntryViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.title) TextView textViewTitle;

    EntryViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
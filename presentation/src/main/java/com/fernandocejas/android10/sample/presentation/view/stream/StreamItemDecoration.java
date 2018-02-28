package com.fernandocejas.android10.sample.presentation.view.stream;

import android.graphics.Rect;
import android.support.annotation.IntRange;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class StreamItemDecoration extends RecyclerView.ItemDecoration {

  private final int margin;

  /**
   * Constructor
   *
   * @param margin desirable margin size in px between the views in the recyclerView
   */
  StreamItemDecoration(@IntRange(from = 0) int margin) {
    this.margin = margin;
  }

  /**
   * Set different margins for the items inside the recyclerView
   */
  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    int position = parent.getChildLayoutPosition(view);

    // Set margin to all
    outRect.bottom = margin;
    outRect.left = margin;
    outRect.right = margin;

    // Make sure we only setup the top margin for the first element
    if (position == 0) {
      outRect.top = margin;
    }
  }
}
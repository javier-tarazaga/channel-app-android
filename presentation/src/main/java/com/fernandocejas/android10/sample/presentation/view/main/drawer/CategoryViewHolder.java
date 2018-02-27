package com.fernandocejas.android10.sample.presentation.view.main.drawer;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.fernandocejas.android10.sample.presentation.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class CategoryViewHolder extends GroupViewHolder {

  @BindView(R.id.list_item_genre_name) TextView genreName;
  @BindView(R.id.list_item_genre_arrow) ImageView arrow;

  public CategoryViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void setGenreTitle(ExpandableGroup genre) {
    if (genre instanceof CategoryModel) {
      genreName.setText(genre.getTitle());
    }
  }

  @Override
  public void expand() {
    animateExpand();
  }

  @Override
  public void collapse() {
    animateCollapse();
  }

  private void animateExpand() {
    RotateAnimation rotate =
        new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
    rotate.setDuration(300);
    rotate.setFillAfter(true);
    arrow.setAnimation(rotate);
  }

  private void animateCollapse() {
    RotateAnimation rotate =
        new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
    rotate.setDuration(300);
    rotate.setFillAfter(true);
    arrow.setAnimation(rotate);
  }
}

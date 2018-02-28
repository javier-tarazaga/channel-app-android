/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.sample.presentation.view.feed.entry;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.view.BaseFragment;
import com.fernandocejas.android10.sample.presentation.view.feed.entry.di.DaggerFeedEntryDetailsComponent;
import com.fernandocejas.android10.sample.presentation.view.feed.entry.di.FeedEntryDetailsComponent;
import com.fernandocejas.android10.sample.presentation.view.feed.model.EntryModel;
import com.fernandocejas.android10.sample.utils.Preconditions;
import javax.inject.Inject;

/**
 * Fragment that shows an feed entry details.
 */
public class FeedEntryDetailsFragment extends BaseFragment implements FeedEntryDetailsView {

  private static final String PARAM_FEED_ENTRY_MODEL = "param_feed_model";

  public static FeedEntryDetailsFragment newInstance(EntryModel entryModel) {
    final FeedEntryDetailsFragment entryDetailsFragment = new FeedEntryDetailsFragment();
    final Bundle arguments = new Bundle();
    arguments.putSerializable(PARAM_FEED_ENTRY_MODEL, entryModel);
    entryDetailsFragment.setArguments(arguments);

    return entryDetailsFragment;
  }

  @Inject FeedEntryDetailsPresenter presenter;

  @BindView(R.id.tb_generic) Toolbar tb_feed_entry;
  @BindView(R.id.iv_entry) ImageView iv_entry;
  @BindView(R.id.tv_title) TextView tv_title;
  @BindView(R.id.tv_content) TextView tv_content;

  private Unbinder viewUnbinder;

  public FeedEntryDetailsFragment() {
    setRetainInstance(true);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    FeedEntryDetailsComponent feedComponent =
        DaggerFeedEntryDetailsComponent.builder().applicationComponent(getApplicationComponent()).build();

    feedComponent.inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_feed_entry_details, container, false);
    viewUnbinder = ButterKnife.bind(this, fragmentView);

    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.presenter.setView(this);
    this.setupView();

    if (savedInstanceState == null) {
      this.presenter.initialize(currentFeedEntryModel());
    }
  }

  @Override public void onResume() {
    super.onResume();
    this.presenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.presenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    viewUnbinder.unbind();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.presenter.destroy();
  }

  @Override public void renderFeedEntryInView(EntryModel entryModel) {

    RequestOptions requestOptions = new RequestOptions();
    requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(8));
    Glide.with(this)
        .load(entryModel.getImageUrl())
        .apply(requestOptions)
        .into(this.iv_entry);

    this.tv_title.setText(entryModel.getTitle());
  }

  private void setupView() {
    this.setupToolbar();
  }

  private void setupToolbar() {
    this.tb_feed_entry.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
  }

  /**
   * Get current feed entry from fragments arguments.
   */
  private EntryModel currentFeedEntryModel() {
    final Bundle arguments = getArguments();
    Preconditions.checkNotNull(arguments, "Fragment arguments cannot be null");
    return (EntryModel) arguments.getSerializable(PARAM_FEED_ENTRY_MODEL);
  }
}

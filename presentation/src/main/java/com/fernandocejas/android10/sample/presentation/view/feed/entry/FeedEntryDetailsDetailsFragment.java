/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.sample.presentation.view.feed.entry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
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
public class FeedEntryDetailsDetailsFragment extends BaseFragment implements FeedEntryDetailsView {

  private static final String PARAM_FEED_ENTRY_MODEL = "param_feed_model";

  public static FeedEntryDetailsDetailsFragment newInstance(EntryModel entryModel) {
    final FeedEntryDetailsDetailsFragment entryDetailsFragment = new FeedEntryDetailsDetailsFragment();
    final Bundle arguments = new Bundle();
    arguments.putSerializable(PARAM_FEED_ENTRY_MODEL, entryModel);
    entryDetailsFragment.setArguments(arguments);

    return entryDetailsFragment;
  }

  @Inject FeedEntryDetailsPresenter presenter;



  private Unbinder viewUnbinder;

  public FeedEntryDetailsDetailsFragment() {
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

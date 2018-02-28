/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.sample.presentation.view.feed;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.view.BaseFragment;
import com.fernandocejas.android10.sample.presentation.view.feed.di.FeedComponent;
import com.fernandocejas.android10.sample.presentation.view.main.drawer.FeedModel;
import com.fernandocejas.android10.sample.presentation.view.feed.di.DaggerFeedComponent;
import com.fernandocejas.android10.sample.presentation.view.feed.model.EntryModel;
import com.fernandocejas.android10.sample.utils.Preconditions;
import java.util.List;
import javax.inject.Inject;

/**
 * Fragment that shows a stream.
 */
public class FeedFragment extends BaseFragment implements FeedView {

  private static final String PARAM_FEED_MODEL = "param_feed_model";

  public static FeedFragment newInstance(FeedModel feedModel) {
    final FeedFragment feedFragment = new FeedFragment();
    final Bundle arguments = new Bundle();
    arguments.putParcelable(PARAM_FEED_MODEL, feedModel);
    feedFragment.setArguments(arguments);

    return feedFragment;
  }

  /**
   * Interface for listening stream events.
   */
  public interface FeedListListener {
    void renderFeedEntry(final EntryModel entryModel);
  }

  @Inject FeedPresenter feedPresenter;
  @Inject FeedAdapter feedAdapter;

  @BindView(R.id.tb_generic) Toolbar tb_feed_entry;
  @BindView(R.id.rv_entries) RecyclerView rv_entries;
  @BindView(R.id.rl_progress) RelativeLayout rl_progress;
  @BindView(R.id.rl_retry) RelativeLayout rl_retry;
  @BindView(R.id.bt_retry) Button bt_retry;

  private FeedListListener feedListListener;
  private Unbinder viewUnbinder;

  public FeedFragment() {
    setRetainInstance(true);
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof FeedListListener) {
      this.feedListListener = (FeedListListener) activity;
    }
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    FeedComponent feedComponent =
        DaggerFeedComponent.builder().applicationComponent(getApplicationComponent()).build();

    feedComponent.inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_feed, container, false);
    viewUnbinder = ButterKnife.bind(this, fragmentView);
    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.feedPresenter.setView(this);
    this.setupView();

    if (savedInstanceState == null) {
      this.loadStream();
    }
  }

  @Override public void onResume() {
    super.onResume();
    this.feedPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.feedPresenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    rv_entries.setAdapter(null);
    viewUnbinder.unbind();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.feedPresenter.destroy();
  }

  @Override public void onDetach() {
    super.onDetach();
    this.feedListListener = null;
  }

  @Override public void showLoading() {
    this.rl_progress.setVisibility(View.VISIBLE);
    this.getActivity().setProgressBarIndeterminateVisibility(true);
  }

  @Override public void hideLoading() {
    this.rl_progress.setVisibility(View.GONE);
    this.getActivity().setProgressBarIndeterminateVisibility(false);
  }

  @Override public void showRetry() {
    this.rl_retry.setVisibility(View.VISIBLE);
  }

  @Override public void hideRetry() {
    this.rl_retry.setVisibility(View.GONE);
  }

  @Override public void renderUserList(List<EntryModel> entryModelList) {
    if (entryModelList != null) {
      this.feedAdapter.setEntryList(entryModelList);
    }
  }

  @Override public void viewEntry(EntryModel entryModel) {
    if (this.feedListListener != null) {
      this.feedListListener.renderFeedEntry(entryModel);
    }
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context context() {
    return this.getActivity().getApplicationContext();
  }

  private void setupView() {
    this.setupToolbar();
    this.setupRecyclerView();
  }

  private void setupToolbar() {
    this.tb_feed_entry.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
  }

  private void setupRecyclerView() {
    this.feedAdapter.setOnItemClickListener(onItemClickListener);

    FeedLayoutManager feedLayoutManager = new FeedLayoutManager(context());
    this.rv_entries.setLayoutManager(feedLayoutManager);
    this.rv_entries.setAdapter(feedAdapter);
    FeedItemDecoration feedItemDecoration =
        new FeedItemDecoration((int) getResources().getDimension(R.dimen.activity_vertical_margin));
    this.rv_entries.addItemDecoration(feedItemDecoration);
  }

  /**
   * Loads the stream for the given subscription.
   */
  private void loadStream() {
    this.feedPresenter.initialize(currentFeedModel().getId());
  }

  /**
   * Get current feed from fragments arguments.
   */
  private FeedModel currentFeedModel() {
    final Bundle arguments = getArguments();
    Preconditions.checkNotNull(arguments, "Fragment arguments cannot be null");
    return arguments.getParcelable(PARAM_FEED_MODEL);
  }

  @OnClick(R.id.bt_retry) void onButtonRetryClick() {
    FeedFragment.this.loadStream();
  }

  private FeedAdapter.OnItemClickListener onItemClickListener = new FeedAdapter.OnItemClickListener() {
    @Override public void onEntryItemClicked(EntryModel entryModel) {
      if (FeedFragment.this.feedPresenter != null && entryModel != null) {
        FeedFragment.this.feedPresenter.onEntryClicked(entryModel);
      }
    }
  };
}

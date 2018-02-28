/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.sample.presentation.view.stream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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
import com.fernandocejas.android10.sample.presentation.view.main.drawer.FeedModel;
import com.fernandocejas.android10.sample.presentation.view.stream.di.DaggerStreamComponent;
import com.fernandocejas.android10.sample.presentation.view.stream.di.StreamComponent;
import com.fernandocejas.android10.sample.presentation.view.stream.model.EntryModel;
import com.fernandocejas.android10.sample.utils.Preconditions;
import java.util.List;
import javax.inject.Inject;

/**
 * Fragment that shows a stream.
 */
public class StreamFragment extends BaseFragment implements StreamView {

  private static final String PARAM_FEED_MODEL = "param_feed_model";

  public static StreamFragment newInstance(FeedModel feedModel) {
    final StreamFragment streamFragment = new StreamFragment();
    final Bundle arguments = new Bundle();
    arguments.putParcelable(PARAM_FEED_MODEL, feedModel);
    streamFragment.setArguments(arguments);

    return streamFragment;
  }

  /**
   * Interface for listening stream events.
   */
  public interface StreamListListener {
    void onEntryClicked(final EntryModel entryModel);
  }

  @Inject StreamPresenter streamPresenter;
  @Inject StreamAdapter streamAdapter;

  @BindView(R.id.rv_entries) RecyclerView rv_entries;
  @BindView(R.id.rl_progress) RelativeLayout rl_progress;
  @BindView(R.id.rl_retry) RelativeLayout rl_retry;
  @BindView(R.id.bt_retry) Button bt_retry;

  private StreamListListener streamListListener;
  private Unbinder viewUnbinder;

  public StreamFragment() {
    setRetainInstance(true);
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof StreamListListener) {
      this.streamListListener = (StreamListListener) activity;
    }
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    StreamComponent streamComponent =
        DaggerStreamComponent.builder().applicationComponent(getApplicationComponent()).build();

    streamComponent.inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View fragmentView = inflater.inflate(R.layout.fragment_stream, container, false);
    viewUnbinder = ButterKnife.bind(this, fragmentView);
    setupRecyclerView();
    return fragmentView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.streamPresenter.setView(this);
    if (savedInstanceState == null) {
      this.loadStream();
    }
  }

  @Override public void onResume() {
    super.onResume();
    this.streamPresenter.resume();
  }

  @Override public void onPause() {
    super.onPause();
    this.streamPresenter.pause();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    rv_entries.setAdapter(null);
    viewUnbinder.unbind();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.streamPresenter.destroy();
  }

  @Override public void onDetach() {
    super.onDetach();
    this.streamListListener = null;
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
      this.streamAdapter.setEntryList(entryModelList);
    }
  }

  @Override public void viewEntry(EntryModel entryModel) {
    if (this.streamListListener != null) {
      this.streamListListener.onEntryClicked(entryModel);
    }
  }

  @Override public void showError(String message) {
    this.showToastMessage(message);
  }

  @Override public Context context() {
    return this.getActivity().getApplicationContext();
  }

  private void setupRecyclerView() {
    this.streamAdapter.setOnItemClickListener(onItemClickListener);

    StreamLayoutManager streamLayoutManager = new StreamLayoutManager(context());
    this.rv_entries.setLayoutManager(streamLayoutManager);
    this.rv_entries.setAdapter(streamAdapter);
    StreamItemDecoration streamItemDecoration =
        new StreamItemDecoration((int) getResources().getDimension(R.dimen.activity_vertical_margin));
    this.rv_entries.addItemDecoration(streamItemDecoration);
  }

  /**
   * Loads the stream for the given subscription.
   */
  private void loadStream() {
    this.streamPresenter.initialize(currentSubscriptionModel().getId());
  }

  /**
   * Get current user id from fragments arguments.
   */
  private FeedModel currentSubscriptionModel() {
    final Bundle arguments = getArguments();
    Preconditions.checkNotNull(arguments, "Fragment arguments cannot be null");
    return arguments.getParcelable(PARAM_FEED_MODEL);
  }

  @OnClick(R.id.bt_retry) void onButtonRetryClick() {
    StreamFragment.this.loadStream();
  }

  private StreamAdapter.OnItemClickListener onItemClickListener = new StreamAdapter.OnItemClickListener() {
    @Override public void onEntryItemClicked(EntryModel entryModel) {
      if (StreamFragment.this.streamPresenter != null && entryModel != null) {
        StreamFragment.this.streamPresenter.onEntryClicked(entryModel);
      }
    }
  };
}

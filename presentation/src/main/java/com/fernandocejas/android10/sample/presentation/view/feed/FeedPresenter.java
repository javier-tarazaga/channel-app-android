/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.sample.presentation.view.feed;

import android.support.annotation.NonNull;
import com.fernandocejas.android10.sample.domain.exception.DefaultErrorBundle;
import com.fernandocejas.android10.sample.domain.exception.ErrorBundle;
import com.fernandocejas.android10.sample.domain.feeds.StreamContent;
import com.fernandocejas.android10.sample.domain.feeds.interactors.GetStreamWithContent;
import com.fernandocejas.android10.sample.domain.interactor.DefaultObserver;
import com.fernandocejas.android10.sample.presentation.exception.ErrorMessageFactory;
import com.fernandocejas.android10.sample.presentation.internal.di.PerFragment;
import com.fernandocejas.android10.sample.presentation.presenter.Presenter;
import com.fernandocejas.android10.sample.presentation.view.feed.model.EntryModel;
import com.fernandocejas.android10.sample.presentation.view.feed.model.StreamContentModel;
import com.fernandocejas.android10.sample.presentation.view.feed.model.mapper.StreamModelDataMapper;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerFragment public class FeedPresenter implements Presenter {

  private FeedView viewListView;

  private final GetStreamWithContent getStreamWithContent;
  private final StreamModelDataMapper streamModelDataMapper;

  @Inject
  public FeedPresenter(GetStreamWithContent getStreamWithContent, StreamModelDataMapper streamModelDataMapper) {
    this.getStreamWithContent = getStreamWithContent;
    this.streamModelDataMapper = streamModelDataMapper;
  }

  public void setView(@NonNull FeedView view) {
    this.viewListView = view;
  }

  @Override public void resume() {
  }

  @Override public void pause() {
  }

  @Override public void destroy() {
    this.getStreamWithContent.dispose();
    this.viewListView = null;
  }

  /**
   * Initializes the presenter by start retrieving the user list.
   */
  public void initialize(String streamId) {
    this.loadFeedEntries(streamId);
  }

  /**
   * Loads all entries for the feed.
   */
  private void loadFeedEntries(String streamId) {
    this.hideViewRetry();
    this.showViewLoading();
    this.getStream(streamId);
  }

  void onEntryClicked(EntryModel entryModel) {
    this.viewListView.viewEntry(entryModel);
  }

  private void showViewLoading() {
    this.viewListView.showLoading();
  }

  private void hideViewLoading() {
    this.viewListView.hideLoading();
  }

  private void showViewRetry() {
    this.viewListView.showRetry();
  }

  private void hideViewRetry() {
    this.viewListView.hideRetry();
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    String errorMessage = ErrorMessageFactory.create(this.viewListView.context(), errorBundle.getException());
    this.viewListView.showError(errorMessage);
  }

  private void showStreamInView(StreamContent streamContent) {
    final StreamContentModel streamContentModel = this.streamModelDataMapper.transform(streamContent);
    this.viewListView.renderUserList(streamContentModel.getEntryModelList());
  }

  private void getStream(String streamId) {
    this.getStreamWithContent.execute(new StreamContentObserver(), GetStreamWithContent.Params.forStream(streamId));
  }

  private final class StreamContentObserver extends DefaultObserver<StreamContent> {

    @Override public void onComplete() {
      FeedPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
      FeedPresenter.this.hideViewLoading();
      FeedPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      FeedPresenter.this.showViewRetry();
    }

    @Override public void onNext(StreamContent streamContent) {
      FeedPresenter.this.showStreamInView(streamContent);
    }
  }
}

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
package com.fernandocejas.android10.sample.presentation.view.feed.entry;

import android.support.annotation.NonNull;
import com.fernandocejas.android10.sample.domain.exception.ErrorBundle;
import com.fernandocejas.android10.sample.presentation.internal.di.PerFragment;
import com.fernandocejas.android10.sample.presentation.presenter.Presenter;
import com.fernandocejas.android10.sample.presentation.view.feed.model.EntryModel;
import javax.inject.Inject;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerFragment public class FeedEntryDetailsPresenter implements Presenter {

  private FeedEntryDetailsView viewListView;

  @Inject FeedEntryDetailsPresenter() {
  }

  public void setView(@NonNull FeedEntryDetailsView view) {
    this.viewListView = view;
  }

  @Override public void resume() {
  }

  @Override public void pause() {
  }

  @Override public void destroy() {
    this.viewListView = null;
  }

  /**
   * Initializes the presenter by displaying all the relevant info in the view.
   */
  public void initialize(EntryModel entryModel) {
    renderFeedEntryInView(entryModel);
  }

  private void renderFeedEntryInView(EntryModel entryModel) {
    this.viewListView.renderFeedEntryInView(entryModel);
  }

  private void showErrorMessage(ErrorBundle errorBundle) {
    //String errorMessage = ErrorMessageFactory.create(this.viewListView.context(), errorBundle.getException());
    //this.viewListView.showError(errorMessage);
  }
}

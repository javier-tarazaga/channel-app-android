/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.sample.presentation.view.feed;

import com.fernandocejas.android10.sample.presentation.view.LoadDataView;
import com.fernandocejas.android10.sample.presentation.view.feed.model.EntryModel;
import java.util.List;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link EntryModel}.
 */
public interface FeedView extends LoadDataView {

  /**
   * Render an entry list in the UI.
   *
   * @param entryModelList The collection of {@link EntryModel} that will be shown.
   */
  void renderUserList(List<EntryModel> entryModelList);

  /**
   * View a {@link EntryModel} details.
   *
   * @param entryModel The entry that will be shown.
   */
  void viewEntry(EntryModel entryModel);
}

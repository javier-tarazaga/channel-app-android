/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.sample.presentation.view.feed.entry;

import com.fernandocejas.android10.sample.presentation.view.feed.model.EntryModel;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a {@link EntryModel}.
 */
interface FeedEntryDetailsView {
  void renderFeedEntryInView(EntryModel entryModel);
}

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
package com.fernandocejas.android10.sample.presentation.view.feed.model.mapper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.fernandocejas.android10.sample.data.feeds.entity.CategoryEntity;
import com.fernandocejas.android10.sample.domain.feeds.Category;
import com.fernandocejas.android10.sample.domain.feeds.Entry;
import com.fernandocejas.android10.sample.presentation.internal.di.PerFragment;
import com.fernandocejas.android10.sample.presentation.view.feed.model.EntryModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Mapper class used to transform {@link CategoryEntity} (in the data layer) to {@link Category} in the
 * domain layer.
 */
@PerFragment public class EntryModelDataMapper {

  @Inject EntryModelDataMapper() {
  }

  /**
   * Transform a {@link Entry} into an {@link EntryModel}.
   *
   * @param entry Object to be transformed.
   * @return {@link EntryModel} if valid {@link Entry} otherwise null.
   */
  @Nullable EntryModel transform(Entry entry) {
    EntryModel entryModel = null;
    if (entry != null) {
      entryModel = new EntryModel(entry.getId());
      entryModel.setTitle(entry.getTitle());
      entryModel.setImageUrl(entry.getVisual() != null ? entry.getVisual().getUrl() : null);
      entryModel.setSummary(entry.getSummary() != null ? entry.getSummary().getContent() : null);
    }
    return entryModel;
  }

  /**
   * Transform a List of {@link Entry} into a Collection of {@link Entry}.
   *
   * @param entryList Object Collection to be transformed.
   * @return {@link Entry} if valid {@link Entry} otherwise null.
   */
  @NonNull List<EntryModel> transform(List<Entry> entryList) {
    final List<EntryModel> entryListModel = new ArrayList<>();

    if (entryList != null && !entryList.isEmpty()) {
      for (Entry entry : entryList) {
        final EntryModel entryModel = transform(entry);
        if (entryModel != null) {
          entryListModel.add(entryModel);
        }
      }
    }

    return entryListModel;
  }
}

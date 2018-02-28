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
package com.fernandocejas.android10.sample.data.feeds.entity.mapper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.fernandocejas.android10.sample.data.feeds.entity.EntryEntity;
import com.fernandocejas.android10.sample.domain.feeds.Entry;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link EntryEntity} (in the data layer) to {@link Entry} in the
 * domain layer.
 */
@Singleton public class EntryEntityDataMapper {

  @Inject EntryEntityDataMapper() {
  }

  /**
   * Transform a {@link EntryEntity} into an {@link Entry}.
   *
   * @param entryEntity Object to be transformed.
   * @return {@link Entry} if valid {@link EntryEntity} otherwise null.
   */
  @Nullable public Entry transform(EntryEntity entryEntity) {
    Entry entry = null;
    if (entryEntity != null) {
      entry = new Entry(entryEntity.getId());
      entry.setTitle(entryEntity.getTitle());
      entry.setSummary(transform(entryEntity.getSummary()));
      entry.setThumbnailList(transformThumbnailList(entryEntity.getThumbnailEntityList()));
    }
    return entry;
  }

  /**
   * Transform a List of {@link EntryEntity} into a Collection of {@link Entry}.
   *
   * @param entryEntityList Object Collection to be transformed.
   * @return {@link Entry} if valid {@link EntryEntity} otherwise null.
   */
  @NonNull public List<Entry> transform(List<EntryEntity> entryEntityList) {
    final List<Entry> entryList = new ArrayList<>();

    if (entryEntityList != null && !entryEntityList.isEmpty()) {
      for (EntryEntity categoryEntity : entryEntityList) {
        final Entry entry = transform(categoryEntity);
        if (entry != null) {
          entryList.add(entry);
        }
      }
    }

    return entryList;
  }

  @Nullable private Entry.Summary transform(EntryEntity.SummaryEntity summaryEntity) {
    Entry.Summary summary = null;
    if (summaryEntity != null) {
      summary = new Entry.Summary(summaryEntity.getContent());
    }

    return summary;
  }

  @Nullable private Entry.Thumbnail transform(EntryEntity.ThumbnailEntity thumbnailEntity) {
    Entry.Thumbnail thumbnail = null;
    if (thumbnailEntity != null) {
      thumbnail = new Entry.Thumbnail(thumbnailEntity.getUrl());
    }

    return thumbnail;
  }

  @NonNull private List<Entry.Thumbnail> transformThumbnailList(List<EntryEntity.ThumbnailEntity> thumbnailEntityList) {
    final List<Entry.Thumbnail> thumbnailList = new ArrayList<>();

    if (thumbnailEntityList != null && !thumbnailEntityList.isEmpty()) {
      for (EntryEntity.ThumbnailEntity thumbnailEntity : thumbnailEntityList) {
        final Entry.Thumbnail thumbnail = transform(thumbnailEntity);
        if (thumbnail != null) {
          thumbnailList.add(thumbnail);
        }
      }
    }

    return thumbnailList;
  }
}

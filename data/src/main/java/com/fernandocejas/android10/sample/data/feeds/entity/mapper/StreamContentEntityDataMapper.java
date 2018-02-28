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

import com.fernandocejas.android10.sample.data.feeds.entity.StreamContentEntity;
import com.fernandocejas.android10.sample.domain.feeds.StreamContent;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link StreamContentEntity} (in the data layer) to {@link StreamContent} in the
 * domain layer.
 */
@Singleton public class StreamContentEntityDataMapper {

  private final EntryEntityDataMapper entryEntityDataMapper;

  @Inject StreamContentEntityDataMapper(EntryEntityDataMapper entryEntityDataMapper) {
    this.entryEntityDataMapper = entryEntityDataMapper;
  }

  /**
   * Transform a {@link StreamContentEntity} into an {@link StreamContent}.
   *
   * @param streamContentEntity Object to be transformed.
   * @return {@link StreamContent} if valid {@link StreamContentEntity} otherwise null.
   */
  public StreamContent transform(StreamContentEntity streamContentEntity) {
    StreamContent streamContent = null;
    if (streamContentEntity != null) {
      streamContent = new StreamContent(streamContentEntity.getId());
      streamContent.setTitle(streamContentEntity.getTitle());
      streamContent.setEntryList(this.entryEntityDataMapper.transform(streamContentEntity.getEntryEntityList()));
    }
    return streamContent;
  }

  /**
   * Transform a List of {@link StreamContentEntity} into a Collection of {@link StreamContent}.
   *
   * @param streamContentEntityList Object Collection to be transformed.
   * @return {@link StreamContent} if valid {@link StreamContentEntity} otherwise null.
   */
  public List<StreamContent> transform(List<StreamContentEntity> streamContentEntityList) {
    final List<StreamContent> streamContentList = new ArrayList<>();

    if (streamContentEntityList != null && !streamContentEntityList.isEmpty()) {
      for (StreamContentEntity streamContentEntity : streamContentEntityList) {
        final StreamContent streamContent = transform(streamContentEntity);
        if (streamContent != null) {
          streamContentList.add(streamContent);
        }
      }
    }

    return streamContentList;
  }
}

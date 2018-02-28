package com.fernandocejas.android10.sample.presentation.view.stream.model.mapper;

import com.fernandocejas.android10.sample.domain.feeds.StreamContent;
import com.fernandocejas.android10.sample.presentation.internal.di.PerFragment;
import com.fernandocejas.android10.sample.presentation.view.stream.model.StreamContentModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Stream} (in the domain layer) to {@link StreamContentModel} in the
 * presentation layer.
 */
@PerFragment public class StreamModelDataMapper {

  private final EntryModelDataMapper entryModelDataMapper;

  @Inject public StreamModelDataMapper(EntryModelDataMapper entryModelDataMapper) {
    this.entryModelDataMapper = entryModelDataMapper;
  }

  /**
   * Transform a {@link StreamContent} into an {@link StreamContentModel}.
   *
   * @param streamContent Object to be transformed.
   * @return {@link StreamContentModel}.
   */
  public StreamContentModel transform(StreamContent streamContent) {
    if (streamContent == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    final StreamContentModel streamContentModel = new StreamContentModel(streamContent.getId());
    streamContentModel.setTitle(streamContent.getTitle());
    streamContentModel.setEntryModelList(this.entryModelDataMapper.transform(streamContent.getEntryList()));

    return streamContentModel;
  }

  /**
   * Transform a Collection of {@link StreamContent} into a Collection of {@link StreamContentModel}.
   *
   * @param streamsCollection Objects to be transformed.
   * @return List of {@link StreamContentModel}.
   */
  public Collection<StreamContentModel> transform(List<StreamContent> streamsCollection) {
    Collection<StreamContentModel> streamModelsCollection;

    if (streamsCollection != null && !streamsCollection.isEmpty()) {
      streamModelsCollection = new ArrayList<>();
      for (StreamContent streamContent : streamsCollection) {
        streamModelsCollection.add(transform(streamContent));
      }
    } else {
      streamModelsCollection = Collections.emptyList();
    }

    return streamModelsCollection;
  }
}

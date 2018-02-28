package com.fernandocejas.android10.sample.domain.feeds;

import io.reactivex.Observable;
import java.util.List;

/**
 * Interface that represents a Repository for getting feeds related data.
 */
public interface FeedRepository {

  /**
   * Get an {@link Observable} which will emit a List of {@link Category}.
   */
  Observable<List<Category>> categories();

  /**
   * Get an {@link Observable} which will emit a {@link Category}.
   *
   * @param categoryId The id used to retrieve category data.
   */
  Observable<Category> category(final int categoryId);

  /**
   * Get an {@link Observable} which will emit a List of {@link Subscription}.
   */
  Observable<List<Subscription>> subscriptions();

  /**
   * Get an {@link Observable} which will emit a {@link StreamContent}.
   *
   * @param streamId A feedId, a categoryId, a tagId or a system category ids can be used as stream ids.
   * @param unreadOnly Only get unread entries from the stream
   */
  Observable<StreamContent> streamWithContent(String streamId, boolean unreadOnly);
}

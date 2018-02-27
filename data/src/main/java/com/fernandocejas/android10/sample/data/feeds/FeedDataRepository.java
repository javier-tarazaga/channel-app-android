package com.fernandocejas.android10.sample.data.feeds;

import com.fernandocejas.android10.sample.domain.feeds.Category;
import com.fernandocejas.android10.sample.domain.repository.FeedRepository;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Singleton;

/**
 * {@link FeedRepository} for retrieving user data.
 */
@Singleton
public class FeedDataRepository implements FeedRepository {

  @Override public Observable<List<Category>> categories() {
    return null;
  }

  @Override public Observable<Category> category(int categoryId) {
    return null;
  }
}

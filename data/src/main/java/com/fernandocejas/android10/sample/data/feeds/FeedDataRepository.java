package com.fernandocejas.android10.sample.data.feeds;

import com.fernandocejas.android10.sample.data.feeds.datasource.FeedDataStoreFactory;
import com.fernandocejas.android10.sample.data.feeds.entity.CategoryEntity;
import com.fernandocejas.android10.sample.data.feeds.entity.SubscriptionEntity;
import com.fernandocejas.android10.sample.data.feeds.entity.mapper.CategoryEntityDataMapper;
import com.fernandocejas.android10.sample.data.feeds.entity.mapper.SubscriptionEntityDataMapper;
import com.fernandocejas.android10.sample.domain.feeds.Category;
import com.fernandocejas.android10.sample.domain.feeds.FeedRepository;
import com.fernandocejas.android10.sample.domain.feeds.Subscription;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link FeedRepository} for retrieving user data.
 */
@Singleton public class FeedDataRepository implements FeedRepository {

  private final FeedDataStoreFactory dataStoreFactory;
  private final CategoryEntityDataMapper categoryEntityDataMapper;
  private final SubscriptionEntityDataMapper subscriptionEntityDataMapper;

  @Inject
  public FeedDataRepository(FeedDataStoreFactory dataStoreFactory, CategoryEntityDataMapper categoryEntityDataMapper,
      SubscriptionEntityDataMapper subscriptionEntityDataMapper) {
    this.dataStoreFactory = dataStoreFactory;
    this.categoryEntityDataMapper = categoryEntityDataMapper;
    this.subscriptionEntityDataMapper = subscriptionEntityDataMapper;
  }

  @Override public Observable<List<Category>> categories() {
    return this.dataStoreFactory.createCloudDataStore()
        .categoriesEntityList()
        .map(new Function<List<CategoryEntity>, List<Category>>() {
          @Override public List<Category> apply(List<CategoryEntity> categoryEntities) throws Exception {
            return categoryEntityDataMapper.transform(categoryEntities);
          }
        });
  }

  @Override public Observable<Category> category(int categoryId) {
    return null;
  }

  @Override public Observable<List<Subscription>> subscriptions() {
    return this.dataStoreFactory.createCloudDataStore()
        .subscriptions()
        .map(new Function<List<SubscriptionEntity>, List<Subscription>>() {
          @Override public List<Subscription> apply(List<SubscriptionEntity> subscriptionEntities) throws Exception {
            return subscriptionEntityDataMapper.transform(subscriptionEntities);
          }
        });
  }
}

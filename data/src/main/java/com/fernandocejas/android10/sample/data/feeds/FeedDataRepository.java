package com.fernandocejas.android10.sample.data.feeds;

import com.fernandocejas.android10.sample.data.feeds.datasource.FeedDataStoreFactory;
import com.fernandocejas.android10.sample.data.feeds.entity.CategoryEntity;
import com.fernandocejas.android10.sample.data.feeds.entity.StreamContentEntity;
import com.fernandocejas.android10.sample.data.feeds.entity.SubscriptionEntity;
import com.fernandocejas.android10.sample.data.feeds.entity.mapper.CategoryEntityDataMapper;
import com.fernandocejas.android10.sample.data.feeds.entity.mapper.StreamContentEntityDataMapper;
import com.fernandocejas.android10.sample.data.feeds.entity.mapper.SubscriptionEntityDataMapper;
import com.fernandocejas.android10.sample.domain.feeds.Category;
import com.fernandocejas.android10.sample.domain.feeds.FeedRepository;
import com.fernandocejas.android10.sample.domain.feeds.StreamContent;
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
  private final StreamContentEntityDataMapper streamContentEntityDataMapper;

  @Inject
  public FeedDataRepository(FeedDataStoreFactory dataStoreFactory, CategoryEntityDataMapper categoryEntityDataMapper,
      SubscriptionEntityDataMapper subscriptionEntityDataMapper,
      StreamContentEntityDataMapper streamContentEntityDataMapper) {
    this.dataStoreFactory = dataStoreFactory;
    this.categoryEntityDataMapper = categoryEntityDataMapper;
    this.subscriptionEntityDataMapper = subscriptionEntityDataMapper;
    this.streamContentEntityDataMapper = streamContentEntityDataMapper;
  }

  @Override public Observable<List<Category>> categories() {
    return this.dataStoreFactory.createCloudDataStore()
        .categoriesEntityList()
        .map(new Function<List<CategoryEntity>, List<Category>>() {
          @Override public List<Category> apply(List<CategoryEntity> categoryEntities) throws Exception {
            return FeedDataRepository.this.categoryEntityDataMapper.transform(categoryEntities);
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
            return FeedDataRepository.this.subscriptionEntityDataMapper.transform(subscriptionEntities);
          }
        });
  }

  @Override public Observable<StreamContent> streamWithContent(String streamId, boolean unreadOnly) {
    return this.dataStoreFactory.createCloudDataStore()
        .streamWithContent(streamId, unreadOnly)
        .map(new Function<StreamContentEntity, StreamContent>() {
          @Override public StreamContent apply(StreamContentEntity streamContentEntity) throws Exception {
            return FeedDataRepository.this.streamContentEntityDataMapper.transform(streamContentEntity);
          }
        });
  }
}

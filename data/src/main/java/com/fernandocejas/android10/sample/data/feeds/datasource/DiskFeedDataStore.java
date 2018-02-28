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
package com.fernandocejas.android10.sample.data.feeds.datasource;

import com.fernandocejas.android10.sample.data.feeds.cache.CategoryCache;
import com.fernandocejas.android10.sample.data.feeds.entity.CategoryEntity;
import com.fernandocejas.android10.sample.data.feeds.entity.SubscriptionEntity;
import io.reactivex.Observable;
import java.util.List;

/**
 * {@link FeedDataStore} implementation based on file system data store.
 */
class DiskFeedDataStore implements FeedDataStore {

  private final CategoryCache categoryCache;

  /**
   * Construct a {@link FeedDataStore} based file system data store.
   *
   * @param categoryCache A {@link CategoryCache} to cache data retrieved from the api.
   */
  DiskFeedDataStore(CategoryCache categoryCache) {
    this.categoryCache = categoryCache;
  }

  @Override public Observable<List<CategoryEntity>> categoriesEntityList() {
    //TODO: implement simple cache for storing/retrieving collections of users.
    throw new UnsupportedOperationException("Operation is not available!!!");
  }

  @Override public Observable<CategoryEntity> categoryEntity(String categoryId) {
    return this.categoryCache.get(categoryId);
  }

  @Override public Observable<List<SubscriptionEntity>> subscriptions() {
    //TODO: implement simple cache for storing/retrieving collections of users.
    throw new UnsupportedOperationException("Operation is not available!!!");
  }
}

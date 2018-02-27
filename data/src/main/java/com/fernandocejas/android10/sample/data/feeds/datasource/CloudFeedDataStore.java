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
import com.fernandocejas.android10.sample.data.net.FeedlyRestApi;
import com.fernandocejas.android10.sample.data.net.RestApi;
import com.fernandocejas.android10.sample.data.user.cache.UserCache;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import java.util.List;

/**
 * {@link FeedDataStore} implementation based on connections to the api (Cloud).
 */
class CloudFeedDataStore implements FeedDataStore {

  private final FeedlyRestApi restApi;
  private final CategoryCache categoryCache;

  /**
   * Construct a {@link FeedDataStore} based on connections to the api (Cloud).
   *
   * @param restApi The {@link RestApi} implementation to use.
   * @param categoryCache A {@link UserCache} to cache data retrieved from the api.
   */
  CloudFeedDataStore(FeedlyRestApi restApi, CategoryCache categoryCache) {
    this.restApi = restApi;
    this.categoryCache = categoryCache;
  }

  @Override public Observable<List<CategoryEntity>> categoriesEntityList() {
    return this.restApi.categoryEntityList();
  }

  @Override public Observable<CategoryEntity> categoryEntity(String categoryId) {
    return this.restApi.categoryEntityById(categoryId).doOnNext(new Consumer<CategoryEntity>() {
      @Override public void accept(CategoryEntity categoryEntity) throws Exception {
        CloudFeedDataStore.this.categoryCache.put(categoryEntity);
      }
    });
  }
}

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
package com.fernandocejas.android10.sample.data.user.datasource;

import com.fernandocejas.android10.sample.data.net.FeedlyRestApi;
import com.fernandocejas.android10.sample.data.user.cache.UserCache;
import com.fernandocejas.android10.sample.data.user.entity.UserEntity;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * {@link UserDataStore} implementation based on connections to the api (Cloud).
 */
class CloudUserDataStore implements UserDataStore {

  private final FeedlyRestApi restApi;
  private final UserCache userCache;

  /**
   * Construct a {@link UserDataStore} based on connections to the api (Cloud).
   *
   * @param restApi The {@link FeedlyRestApi} implementation to use.
   * @param userCache A {@link UserCache} to cache data retrieved from the api.
   */
  CloudUserDataStore(FeedlyRestApi restApi, UserCache userCache) {
    this.restApi = restApi;
    this.userCache = userCache;
  }

  @Override public Observable<UserEntity> userEntity() {
    return this.restApi.profile().doOnNext(new Consumer<UserEntity>() {
      @Override public void accept(UserEntity userEntity) throws Exception {
        CloudUserDataStore.this.userCache.put(userEntity);
      }
    });
  }
}

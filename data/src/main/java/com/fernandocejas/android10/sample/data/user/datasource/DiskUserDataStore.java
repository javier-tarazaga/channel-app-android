package com.fernandocejas.android10.sample.data.user.datasource;

import com.fernandocejas.android10.sample.data.user.cache.UserCache;
import com.fernandocejas.android10.sample.data.user.entity.UserEntity;
import io.reactivex.Observable;

/**
 * {@link UserDataStore} implementation based on file system data store.
 */
class DiskUserDataStore implements UserDataStore {

  private final UserCache userCache;

  /**
   * Construct a {@link UserDataStore} based file system data store.
   *
   * @param userCache A {@link UserCache} to cache data retrieved from the api.
   */
  DiskUserDataStore(UserCache userCache) {
    this.userCache = userCache;
  }

  @Override public Observable<UserEntity> userEntity() {
    return this.userCache.get();
  }
}

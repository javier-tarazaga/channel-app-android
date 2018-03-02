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
package com.fernandocejas.android10.sample.data.user;

import com.fernandocejas.android10.sample.data.user.datasource.UserDataStore;
import com.fernandocejas.android10.sample.data.user.datasource.UserDataStoreFactory;
import com.fernandocejas.android10.sample.data.user.entity.UserEntity;
import com.fernandocejas.android10.sample.data.user.entity.mapper.UserEntityDataMapper;
import com.fernandocejas.android10.sample.domain.user.User;
import com.fernandocejas.android10.sample.domain.user.UserRepository;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * {@link UserRepository} for retrieving user data.
 */
@Singleton public class UserDataRepository implements UserRepository {

  private final UserDataStoreFactory userDataStoreFactory;
  private final UserEntityDataMapper userEntityDataMapper;

  /**
   * Constructs a {@link UserRepository}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   * @param userEntityDataMapper {@link UserEntityDataMapper}.
   */
  @Inject UserDataRepository(UserDataStoreFactory dataStoreFactory, UserEntityDataMapper userEntityDataMapper) {
    this.userDataStoreFactory = dataStoreFactory;
    this.userEntityDataMapper = userEntityDataMapper;
  }

  @Override public Observable<User> user() {
    final UserDataStore userDataStore = this.userDataStoreFactory.create();
    return userDataStore.userEntity().map(new Function<UserEntity, User>() {
      @Override public User apply(UserEntity userEntity) throws Exception {
        return UserDataRepository.this.userEntityDataMapper.transform(userEntity);
      }
    });
  }
}

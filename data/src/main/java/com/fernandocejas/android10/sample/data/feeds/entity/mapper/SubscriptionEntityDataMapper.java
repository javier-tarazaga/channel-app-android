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
package com.fernandocejas.android10.sample.data.feeds.entity.mapper;

import com.fernandocejas.android10.sample.data.feeds.entity.SubscriptionEntity;
import com.fernandocejas.android10.sample.domain.feeds.Category;
import com.fernandocejas.android10.sample.domain.feeds.Subscription;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link SubscriptionEntity} (in the data layer) to {@link Subscription} in the
 * domain layer.
 */
@Singleton public class SubscriptionEntityDataMapper {

  private final CategoryEntityDataMapper categoryEntityDataMapper;

  @Inject SubscriptionEntityDataMapper(CategoryEntityDataMapper categoryEntityDataMapper) {
    this.categoryEntityDataMapper = categoryEntityDataMapper;
  }

  /**
   * Transform a {@link SubscriptionEntity} into an {@link Subscription}.
   *
   * @param subscriptionEntity Object to be transformed.
   * @return {@link Subscription} if valid {@link SubscriptionEntity} otherwise null.
   */
  public Subscription transform(SubscriptionEntity subscriptionEntity) {
    Subscription subscription = null;
    if (subscriptionEntity != null) {
      subscription =
          new Subscription(subscriptionEntity.getId(), subscriptionEntity.getTitle(), subscriptionEntity.getWebsite());
      subscription.setIconUrl(subscriptionEntity.getIconUrl());
      subscription.setVisualUrl(subscriptionEntity.getVisualUrl());
      subscription.setTopics(subscriptionEntity.getTopics());
      subscription.setCategories(categoryEntityDataMapper.transform(subscriptionEntity.getCategories()));
    }
    return subscription;
  }

  /**
   * Transform a List of {@link SubscriptionEntity} into a Collection of {@link Category}.
   *
   * @param subscriptionEntityList Object Collection to be transformed.
   * @return {@link Category} if valid {@link SubscriptionEntity} otherwise null.
   */
  public List<Subscription> transform(List<SubscriptionEntity> subscriptionEntityList) {
    final List<Subscription> subscriptionList = new ArrayList<>();
    for (SubscriptionEntity subscriptionEntity : subscriptionEntityList) {
      final Subscription subscription = transform(subscriptionEntity);
      if (subscription != null) {
        subscriptionList.add(subscription);
      }
    }
    return subscriptionList;
  }
}

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
package com.fernandocejas.android10.sample.presentation.view.drawer.mapper;

import com.fernandocejas.android10.sample.data.feeds.entity.SubscriptionEntity;
import com.fernandocejas.android10.sample.domain.feeds.Category;
import com.fernandocejas.android10.sample.domain.feeds.Subscription;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.view.drawer.CategoryModel;
import com.fernandocejas.android10.sample.presentation.view.drawer.FeedModel;
import com.fernandocejas.android10.sample.utils.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Category} (in the domain layer) to {@link CategoryModel} in the
 * presentation layer.
 */
@PerActivity public class SubscriptionModelDataMapper {

  @Inject public SubscriptionModelDataMapper() {
  }

  /**
   * Transform a {@link SubscriptionEntity} into an {@link Subscription}.
   *
   * @param subscription Object to be transformed.
   * @return {@link FeedModel} if valid {@link Subscription} otherwise null.
   */
  public FeedModel transform(Subscription subscription) {
    Preconditions.checkNotNull(subscription, "Cannot transform a null value");

    FeedModel feedModel = new FeedModel(subscription.getId(), subscription.getTitle());
    feedModel.setIconUrl(subscription.getIconUrl());

    return feedModel;
  }

  /**
   * Transform a Collection of {@link Subscription} into a Collection of {@link FeedModel}.
   *
   * @param subscriptionCollection Objects to be transformed.
   * @return List of {@link FeedModel}.
   */
  public List<FeedModel> transform(List<Subscription> subscriptionCollection) {
    List<FeedModel> feedModelList;

    if (subscriptionCollection != null && !subscriptionCollection.isEmpty()) {
      feedModelList = new ArrayList<>();
      for (Subscription subscription : subscriptionCollection) {
        feedModelList.add(transform(subscription));
      }
    } else {
      feedModelList = Collections.emptyList();
    }

    return feedModelList;
  }
}

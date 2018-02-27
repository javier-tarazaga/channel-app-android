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

import com.fernandocejas.android10.sample.domain.feeds.Category;
import com.fernandocejas.android10.sample.presentation.internal.di.PerActivity;
import com.fernandocejas.android10.sample.presentation.view.drawer.CategoryModel;
import com.fernandocejas.android10.sample.utils.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Category} (in the domain layer) to {@link CategoryModel} in the
 * presentation layer.
 */
@PerActivity public class CategoryModelDataMapper {

  private final SubscriptionModelDataMapper subscriptionModelDataMapper;

  @Inject public CategoryModelDataMapper(SubscriptionModelDataMapper subscriptionModelDataMapper) {
    this.subscriptionModelDataMapper = subscriptionModelDataMapper;
  }

  /**
   * Transform a {@link Category} into an {@link CategoryModel}.
   *
   * @param category Object to be transformed.
   * @return {@link CategoryModel} if valid {@link Category} otherwise null.
   */
  public CategoryModel transform(Category category) {
    Preconditions.checkNotNull(category, "Cannot transform a null value");
    return new CategoryModel(category.getId(),
        this.subscriptionModelDataMapper.transform(category.getSubscriptionList()));
  }

  /**
   * Transform a List of {@link Category} into a Collection of {@link CategoryModel}.
   *
   * @param categoryList Object Collection to be transformed.
   * @return {@link CategoryModel} if valid {@link Category} otherwise null.
   */
  public List<CategoryModel> transform(List<Category> categoryList) {
    List<CategoryModel> categoryModelsCollection;

    if (categoryList != null && !categoryList.isEmpty()) {
      categoryModelsCollection = new ArrayList<>();
      for (Category category : categoryList) {
        categoryModelsCollection.add(transform(category));
      }
    } else {
      categoryModelsCollection = Collections.emptyList();
    }

    return categoryModelsCollection;
  }
}

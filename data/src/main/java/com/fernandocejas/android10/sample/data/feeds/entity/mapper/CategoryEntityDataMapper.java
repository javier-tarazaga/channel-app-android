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

import com.fernandocejas.android10.sample.data.feeds.entity.CategoryEntity;
import com.fernandocejas.android10.sample.domain.feeds.Category;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link CategoryEntity} (in the data layer) to {@link Category} in the
 * domain layer.
 */
@Singleton public class CategoryEntityDataMapper {

  @Inject CategoryEntityDataMapper() {
  }

  /**
   * Transform a {@link CategoryEntity} into an {@link Category}.
   *
   * @param categoryEntity Object to be transformed.
   * @return {@link Category} if valid {@link CategoryEntity} otherwise null.
   */
  public Category transform(CategoryEntity categoryEntity) {
    Category category = null;
    if (categoryEntity != null) {
      category = new Category(categoryEntity.getId(), categoryEntity.getLabel());
    }
    return category;
  }

  /**
   * Transform a List of {@link CategoryEntity} into a Collection of {@link Category}.
   *
   * @param categoryEntityList Object Collection to be transformed.
   * @return {@link Category} if valid {@link CategoryEntity} otherwise null.
   */
  public List<Category> transform(List<CategoryEntity> categoryEntityList) {
    final List<Category> categoryList = new ArrayList<>();

    if (categoryEntityList != null && !categoryEntityList.isEmpty()) {
      for (CategoryEntity categoryEntity : categoryEntityList) {
        final Category category = transform(categoryEntity);
        if (category != null) {
          categoryList.add(category);
        }
      }
    }

    return categoryList;
  }
}

/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.sample.data.feeds.entity.mapper;

import com.fernandocejas.android10.sample.data.feeds.entity.CategoryEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Inject;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class SubscriptionEntityJsonMapper {

  private final Gson gson;

  @Inject
  public SubscriptionEntityJsonMapper() {
    this.gson = new Gson();
  }

  /**
   * Transform from valid json string to {@link CategoryEntity}.
   *
   * @param categoryJsonResponse A json representing a user profile.
   * @return {@link CategoryEntity}.
   * @throws JsonSyntaxException if the json string is not a valid json structure.
   */
  public CategoryEntity transformUserEntity(String categoryJsonResponse) throws JsonSyntaxException {
    final Type userEntityType = new TypeToken<CategoryEntity>() {}.getType();
    return this.gson.fromJson(categoryJsonResponse, userEntityType);
  }

  /**
   * Transform from valid json string to List of {@link CategoryEntity}.
   *
   * @param categoryListJsonResponse A json representing a collection of categories.
   * @return List of {@link CategoryEntity}.
   * @throws JsonSyntaxException if the json string is not a valid json structure.
   */
  public List<CategoryEntity> transformUserEntityCollection(String categoryListJsonResponse)
      throws JsonSyntaxException {
    final Type listOfUserEntityType = new TypeToken<List<CategoryEntity>>() {}.getType();
    return this.gson.fromJson(categoryListJsonResponse, listOfUserEntityType);
  }
}

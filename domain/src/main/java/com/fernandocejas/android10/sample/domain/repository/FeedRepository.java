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
package com.fernandocejas.android10.sample.domain.repository;

import com.fernandocejas.android10.sample.domain.feeds.Category;
import io.reactivex.Observable;
import java.util.List;

/**
 * Interface that represents a Repository for getting feeds related data.
 */
public interface FeedRepository {

  /**
   * Get an {@link Observable} which will emit a List of {@link Category}.
   */
  Observable<List<Category>> categories();

  /**
   * Get an {@link Observable} which will emit a {@link Category}.
   *
   * @param categoryId The id used to retrieve category data.
   */
  Observable<Category> category(final int categoryId);
}
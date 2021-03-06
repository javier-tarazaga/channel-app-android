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

import android.content.Context;
import android.support.annotation.NonNull;
import com.fernandocejas.android10.sample.data.net.FeedlyRestApi;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link FeedDataStore}.
 */
@Singleton public class FeedDataStoreFactory {

  private final Context context;
  private final FeedlyRestApi feedlyRestApi;

  @Inject FeedDataStoreFactory(@NonNull Context context, @NonNull FeedlyRestApi feedlyRestApi) {
    this.context = context.getApplicationContext();
    this.feedlyRestApi = feedlyRestApi;
  }

  /**
   * Create {@link FeedDataStore} from a category id.
   */
  public FeedDataStore create(String categoryId) {
    FeedDataStore feedDataStore;

    //if (!this.categoryCache.isExpired() && this.categoryCache.isCached(categoryId)) {
    //  feedDataStore = new DiskFeedDataStore(this.categoryCache);
    //} else {
    //  feedDataStore = createCloudDataStore();
    //}

    feedDataStore = createCloudDataStore();

    return feedDataStore;
  }

  /**
   * Create {@link FeedDataStore} to retrieve data from the Cloud.
   */
  public FeedDataStore createCloudDataStore() {
    return new CloudFeedDataStore(this.feedlyRestApi, null);
  }
}

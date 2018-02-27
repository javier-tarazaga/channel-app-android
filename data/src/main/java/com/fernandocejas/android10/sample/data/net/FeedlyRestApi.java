package com.fernandocejas.android10.sample.data.net;

import com.fernandocejas.android10.sample.data.feeds.entity.CategoryEntity;
import io.reactivex.Observable;
import java.util.List;

public interface FeedlyRestApi {
  Observable<List<CategoryEntity>> categoryEntityList();

  Observable<CategoryEntity> categoryEntityById(String categoryId);
}

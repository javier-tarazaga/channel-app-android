package com.fernandocejas.android10.sample.domain.feeds.interactors;

import com.fernandocejas.android10.sample.domain.executor.PostExecutionThread;
import com.fernandocejas.android10.sample.domain.executor.ThreadExecutor;
import com.fernandocejas.android10.sample.domain.feeds.Category;
import com.fernandocejas.android10.sample.domain.feeds.FeedRepository;
import com.fernandocejas.android10.sample.domain.feeds.Subscription;
import com.fernandocejas.android10.sample.domain.interactor.UseCase;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import java.util.List;
import javax.inject.Inject;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link Category}.
 */
public class GetCategoryList extends UseCase<List<Category>, Void> {

  private final FeedRepository feedRepository;

  @Inject GetCategoryList(FeedRepository feedRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.feedRepository = feedRepository;
  }

  @Override protected Observable<List<Category>> buildUseCaseObservable(Void unused) {
    return Observable.zip(this.feedRepository.subscriptions(), this.feedRepository.categories(),
        new BiFunction<List<Subscription>, List<Category>, List<Category>>() {
          @Override public List<Category> apply(List<Subscription> subscriptions, List<Category> categoryList)
              throws Exception {

            if (categoryList != null) {
              for(Category category : categoryList) {
                category.setSubscriptionList(subscriptions);
              }
            }

            return categoryList;
          }
        });
  }
}

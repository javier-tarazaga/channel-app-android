package com.fernandocejas.android10.sample.domain.feeds.interactors;

import com.fernandocejas.android10.sample.domain.executor.PostExecutionThread;
import com.fernandocejas.android10.sample.domain.executor.ThreadExecutor;
import com.fernandocejas.android10.sample.domain.feeds.FeedRepository;
import com.fernandocejas.android10.sample.domain.feeds.StreamContent;
import com.fernandocejas.android10.sample.domain.interactor.UseCase;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a {@link StreamContent}.
 */
public class GetStreamWithContent extends UseCase<StreamContent, GetStreamWithContent.Params> {

  private final FeedRepository feedRepository;

  @Inject GetStreamWithContent(FeedRepository feedRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.feedRepository = feedRepository;
  }

  @Override protected Observable<StreamContent> buildUseCaseObservable(GetStreamWithContent.Params params) {
    return this.feedRepository.streamWithContent(params.streamId, params.unreadOnly);
  }

  public static final class Params {
    private final String streamId;
    private boolean unreadOnly;

    private Params(String streamId) {
      this.streamId = streamId;
    }

    public void setUnreadOnly(boolean unreadOnly) {
      this.unreadOnly = unreadOnly;
    }

    public static GetStreamWithContent.Params forStream(String streamId) {
      return new GetStreamWithContent.Params(streamId);
    }
  }
}

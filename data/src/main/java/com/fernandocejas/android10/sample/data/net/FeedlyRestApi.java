package com.fernandocejas.android10.sample.data.net;

import com.fernandocejas.android10.sample.data.feeds.entity.CategoryEntity;
import com.fernandocejas.android10.sample.data.feeds.entity.StreamContentEntity;
import com.fernandocejas.android10.sample.data.feeds.entity.SubscriptionEntity;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface FeedlyRestApi {

  // CATEGORIES
  @Headers({
      "Authorization: OAuth A28N1sRWKUhHlWpwbU-P-zP0aWgyGwVk8zlvFjYOvTakssXP_t9hcg5FzRj_N3jkb6H5EMDv4Dnm4XqsiXkZOJQQbw2KH3bRtymVUOJnD0GA-6a0_mG52Vg4cfVAiTFVAxwarPhHvXY5W3qAr4n22NWk47Ce9hf6VFsUqLBQQnoGKN1A-RESr16Q4C6FQtyWrMVDf7ng9EksJcINlsSrQ-HKqaRDuL0Zyl_E9niZtY_-P7gbDJ7YIQpYak9R:feedlydev"
  }) @GET("categories?sort=feedly") Observable<List<CategoryEntity>> categoryEntityList();

  Observable<CategoryEntity> categoryEntityById(String categoryId);

  // SUBSCRIPTIONS
  @Headers({
      "Authorization: OAuth A28N1sRWKUhHlWpwbU-P-zP0aWgyGwVk8zlvFjYOvTakssXP_t9hcg5FzRj_N3jkb6H5EMDv4Dnm4XqsiXkZOJQQbw2KH3bRtymVUOJnD0GA-6a0_mG52Vg4cfVAiTFVAxwarPhHvXY5W3qAr4n22NWk47Ce9hf6VFsUqLBQQnoGKN1A-RESr16Q4C6FQtyWrMVDf7ng9EksJcINlsSrQ-HKqaRDuL0Zyl_E9niZtY_-P7gbDJ7YIQpYak9R:feedlydev"
  }) @GET("subscriptions") Observable<List<SubscriptionEntity>> subscriptionEntityList();

  // STREAMS
  @Headers({
      "Authorization: OAuth A28N1sRWKUhHlWpwbU-P-zP0aWgyGwVk8zlvFjYOvTakssXP_t9hcg5FzRj_N3jkb6H5EMDv4Dnm4XqsiXkZOJQQbw2KH3bRtymVUOJnD0GA-6a0_mG52Vg4cfVAiTFVAxwarPhHvXY5W3qAr4n22NWk47Ce9hf6VFsUqLBQQnoGKN1A-RESr16Q4C6FQtyWrMVDf7ng9EksJcINlsSrQ-HKqaRDuL0Zyl_E9niZtY_-P7gbDJ7YIQpYak9R:feedlydev"
  }) @GET("streams/contents?streamId={streamId}&unreadOnly={unreadOnly}")
  Observable<StreamContentEntity> streamContentById(@Path("streamId") String streamId,
      @Path("unreadOnly") boolean unreadOnly);
}

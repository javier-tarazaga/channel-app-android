package com.fernandocejas.android10.sample.data.net;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger module that provides user related collaborators.
 */
@Module public class ApiModule {

  @Provides @Singleton Retrofit providesRetrofit() {
    return new Retrofit.Builder()
        .baseUrl("http://cloud.feedly.com/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
  }

  @Provides @Singleton FeedlyRestApi providesFeedlyRestApi(Retrofit retrofit) {
    return retrofit.create(FeedlyRestApi.class);
  }
}

package com.iliakplv.trademepreview.api;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.iliakplv.trademepreview.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {


    @Provides
    @NonNull
    @Singleton
    public TradeMeApi provideTradeMeApi(@NonNull OkHttpClient okHttpClient, @NonNull Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .validateEagerly(BuildConfig.DEBUG)  // check Retrofit configuration at creation time in Debug build.
                .build()
                .create(TradeMeApi.class);
    }
}

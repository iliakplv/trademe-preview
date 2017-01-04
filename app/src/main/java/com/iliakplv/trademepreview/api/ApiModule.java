package com.iliakplv.trademepreview.api;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.iliakplv.trademepreview.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @NonNull
    private final ChangeableBaseUrl changeableBaseUrl;

    public ApiModule(@NonNull String baseUrl) {
        changeableBaseUrl = new ChangeableBaseUrl(baseUrl);
    }

    @Provides
    @NonNull
    @Singleton
    public ChangeableBaseUrl provideChangeableBaseUrl() {
        return changeableBaseUrl;
    }

    @Provides
    @NonNull
    @Singleton
    public TradeMeApi provideTradeMeApi(@NonNull OkHttpClient okHttpClient, @NonNull Gson gson, @NonNull ChangeableBaseUrl changeableBaseUrl) {
        return new Retrofit.Builder()
                .baseUrl(changeableBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .validateEagerly(BuildConfig.DEBUG)  // check Retrofit configuration at creation time in Debug build.
                .build()
                .create(TradeMeApi.class);
    }
}

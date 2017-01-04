package com.iliakplv.trademepreview;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.iliakplv.trademepreview.api.ApiModule;
import com.iliakplv.trademepreview.api.ChangeableBaseUrl;
import com.iliakplv.trademepreview.api.TradeMeApi;
import com.iliakplv.trademepreview.network.NetworkModule;
import com.iliakplv.trademepreview.network.OkHttpInterceptorsModule;
import com.iliakplv.trademepreview.ui.CategoryListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        OkHttpInterceptorsModule.class,
        ApiModule.class,
})
public interface ApplicationComponent {

    @NonNull
    Gson gson();

    @NonNull
    TradeMeApi tradeMeApi();

    @NonNull
    ChangeableBaseUrl changeableBaseUrl();


    void inject(@NonNull CategoryListActivity mainActivity);
}

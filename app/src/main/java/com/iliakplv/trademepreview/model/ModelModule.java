package com.iliakplv.trademepreview.model;

import android.support.annotation.NonNull;

import com.iliakplv.trademepreview.api.TradeMeApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {

    @Provides
    @NonNull
    @Singleton
    public CategoriesModel provideCategoriesModel(@NonNull TradeMeApi tradeMeApi) {
        return new CategoriesModelImpl(tradeMeApi);
    }

}

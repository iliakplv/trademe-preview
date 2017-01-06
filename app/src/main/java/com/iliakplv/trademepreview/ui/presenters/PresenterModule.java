package com.iliakplv.trademepreview.ui.presenters;

import android.support.annotation.NonNull;

import com.iliakplv.trademepreview.model.CategoriesModel;
import com.iliakplv.trademepreview.model.ListingsModel;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @NonNull
    public CategoriesListPresenter provideCategoriesListPresenter(@NonNull CategoriesModel categoriesModel) {
        return new CategoriesListPresenter(categoriesModel);
    }

    @Provides
    @NonNull
    public ListingsPresenter provideListingsPresenter(@NonNull ListingsModel listingsModel) {
        return new ListingsPresenter(listingsModel);
    }

}

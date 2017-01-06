package com.iliakplv.trademepreview;

import android.support.annotation.NonNull;

import com.iliakplv.trademepreview.api.ApiModule;
import com.iliakplv.trademepreview.api.ChangeableBaseUrl;
import com.iliakplv.trademepreview.api.TradeMeApi;
import com.iliakplv.trademepreview.model.CategoriesModel;
import com.iliakplv.trademepreview.model.ModelModule;
import com.iliakplv.trademepreview.network.NetworkModule;
import com.iliakplv.trademepreview.network.OkHttpInterceptorsModule;
import com.iliakplv.trademepreview.ui.activities.CategoryListActivity;
import com.iliakplv.trademepreview.ui.adapters.ListingsAdapter;
import com.iliakplv.trademepreview.ui.fragments.ListingsFragment;
import com.iliakplv.trademepreview.ui.presenters.PresenterModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        OkHttpInterceptorsModule.class,
        ApiModule.class,
        ModelModule.class,
        PresenterModule.class,
})
public interface ApplicationComponent {

    // Access to URL, API, Model, etc. for testing purposes

    @NonNull
    ChangeableBaseUrl changeableBaseUrl();

    @NonNull
    TradeMeApi tradeMeApi();

    @NonNull
    CategoriesModel categoriesModel();


    // Injections

    void inject(@NonNull CategoryListActivity activity);

    void inject(@NonNull ListingsFragment activity);

    void inject(@NonNull ListingsAdapter activity);
}

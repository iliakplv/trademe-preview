package com.iliakplv.trademepreview.model;

import android.support.annotation.NonNull;

import com.iliakplv.trademepreview.api.TradeMeApi;
import com.iliakplv.trademepreview.api.entities.Category;

import rx.Single;

import static com.iliakplv.trademepreview.common.StringUtils.getLastDigitGroup;

/**
 * This implementation should hide all the logic for data fetching/updating/caching/etc.
 * In this example it transfers all the calls directly to the API
 */
/*package*/ class CategoriesModelImpl implements CategoriesModel {

    private static final int CATEGORY_DEPTH = 1;

    @NonNull
    private final TradeMeApi tradeMeApi;


    /*package*/ CategoriesModelImpl(@NonNull TradeMeApi tradeMeApi) {
        this.tradeMeApi = tradeMeApi;
    }

    @NonNull
    public Single<Category> getCategory(@NonNull String number) {
        return tradeMeApi.getCategory(
                getLastDigitGroup(number, Category.NUMBER_SEPARATOR),
                CATEGORY_DEPTH);
    }

}

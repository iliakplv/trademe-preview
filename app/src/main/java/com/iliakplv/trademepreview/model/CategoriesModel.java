package com.iliakplv.trademepreview.model;

import android.support.annotation.NonNull;

import com.iliakplv.trademepreview.api.entities.Category;

import rx.Single;


public interface CategoriesModel {

    @NonNull
    Single<Category> getCategory(String number);

}

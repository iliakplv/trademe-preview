package com.iliakplv.trademepreview.model;


import android.support.annotation.NonNull;

import com.iliakplv.trademepreview.api.entities.SearchResult;

import rx.Single;

public interface ListingsModel {

    @NonNull
    Single<SearchResult> getListings(@NonNull String categoryNumber);

    @NonNull
    Single<SearchResult> searchListings(@NonNull String categoryNumber, @NonNull String searchString);

}

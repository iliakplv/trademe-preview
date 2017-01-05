package com.iliakplv.trademepreview.model;

import android.support.annotation.NonNull;

import com.iliakplv.trademepreview.api.TradeMeApi;
import com.iliakplv.trademepreview.api.entities.SearchResult;

import rx.Single;


public class ListingsModelImpl implements ListingsModel {

    private static final int ROWS_LIMIT = 20;

    @NonNull
    private TradeMeApi tradeMeApi;


    public ListingsModelImpl(@NonNull TradeMeApi tradeMeApi) {
        this.tradeMeApi = tradeMeApi;
    }


    @NonNull
    @Override
    public Single<SearchResult> getListings(String categoryNumber) {
        return tradeMeApi.getListings(categoryNumber, ROWS_LIMIT);
    }

    @NonNull
    @Override
    public Single<SearchResult> searchListings(String categoryNumber, String searchString) {
        return tradeMeApi.searchListings(categoryNumber, searchString, ROWS_LIMIT);
    }
}

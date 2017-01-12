package com.iliakplv.trademepreview.model;


import android.support.annotation.NonNull;

import com.iliakplv.trademepreview.api.entities.SearchResult;

import rx.Single;

public interface ListingsModel {

    String SORT_ORDER_DEFAULT = "Default";
    String SORT_ORDER_FEATURED = "FeaturedFirst";
    String SORT_ORDER_TITLE_ACS = "TitleAsc";
    String SORT_ORDER_PRICE_ASC = "PriceAsc";

    @NonNull
    Single<SearchResult> searchListings(@NonNull String categoryNumber,
                                        @NonNull String searchString,
                                        @NonNull String sortOrder);

}

package com.iliakplv.trademepreview.ui.views;


import com.iliakplv.trademepreview.api.entities.SearchResult;

public interface ListingsView {

    void onLoadingStarted();

    void onListingsLoaded(SearchResult searchResult);

    void onLoadingError();

    void onListingClicked(int listingId);

}

package com.iliakplv.trademepreview.api.entities;


import com.google.gson.annotations.SerializedName;

public class SearchResult {

    @SerializedName("TotalCount")
    private int totalCount;

    @SerializedName("Page")
    private int page;

    @SerializedName("PageSize")
    private int pageSize;

    @SerializedName("List")
    private Listing[] listings;


    public int getTotalCount() {
        return totalCount;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public boolean hasListings() {
        return listingsCount() > 0;
    }

    public int listingsCount() {
        return listings != null ? listings.length : 0;
    }

    public Listing getListing(int position) {
        return listings[position];
    }
}

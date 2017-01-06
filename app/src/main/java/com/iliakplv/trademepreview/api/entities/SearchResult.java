package com.iliakplv.trademepreview.api.entities;


import com.google.gson.annotations.SerializedName;

public class SearchResult {

    @SerializedName("TotalCount")
    public int totalCount;

    @SerializedName("Page")
    public int page;

    @SerializedName("PageSize")
    public int pageSize;

    @SerializedName("List")
    public Listing[] listings;


    public boolean hasListings() {
        return listings != null && listings.length > 0;
    }

}

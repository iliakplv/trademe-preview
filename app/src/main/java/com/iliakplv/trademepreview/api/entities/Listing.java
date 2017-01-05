package com.iliakplv.trademepreview.api.entities;


import com.google.gson.annotations.SerializedName;

public class Listing {

    @SerializedName("ListngId")
    private int listingId;

    @SerializedName("Title")
    private String title;

    @SerializedName("PictureHref")
    private String pictureHref;

}

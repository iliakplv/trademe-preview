package com.iliakplv.trademepreview.api.entities;


import com.google.gson.annotations.SerializedName;

public class Listing {

    @SerializedName("ListngId")
    public int listingId;

    @SerializedName("Title")
    public String title;

    @SerializedName("PictureHref")
    public String pictureHref;

}

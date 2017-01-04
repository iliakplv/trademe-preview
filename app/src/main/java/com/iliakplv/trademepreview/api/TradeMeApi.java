package com.iliakplv.trademepreview.api;

import android.support.annotation.NonNull;

import com.iliakplv.trademepreview.model.Category;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface TradeMeApi {

    @GET("Categories/{number}.json")
    @NonNull
    Category getCategory(@Path("number") String categoryNumber, @Query("depth") int depth);

}

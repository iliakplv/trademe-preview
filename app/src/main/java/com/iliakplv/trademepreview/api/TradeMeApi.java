package com.iliakplv.trademepreview.api;

import android.support.annotation.NonNull;

import com.iliakplv.trademepreview.BuildConfig;
import com.iliakplv.trademepreview.api.entities.Category;
import com.iliakplv.trademepreview.api.entities.SearchResult;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;


public interface TradeMeApi {

    String AUTH_HEADER = "Authorization: OAuth " +
            "oauth_consumer_key=\"" + BuildConfig.CONSUMER_KEY + "\", " +
            "oauth_signature_method=\"PLAINTEXT\", " +
            "oauth_signature=\"" + BuildConfig.CONSUMER_SECRET + "&\"";


    @GET("Categories/{number}.json")
    @NonNull
    Single<Category> getCategory(@Path("number") String categoryNumber, @Query("depth") int depth);


    @GET("Search/General.json")
    @NonNull
    @Headers({AUTH_HEADER})
    Single<SearchResult> getListings(@Query("category") String categoryNumber, @Query("rows") int rows);


    @GET("Search/General.json")
    @NonNull
    @Headers({AUTH_HEADER})
    Single<SearchResult> searchListings(@Query("category") String categoryNumber,
                                        @Query("search_string") String searchString,
                                        @Query("rows") int rows);

}

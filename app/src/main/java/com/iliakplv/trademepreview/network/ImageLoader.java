package com.iliakplv.trademepreview.network;


import android.support.annotation.NonNull;
import android.widget.ImageView;

public interface ImageLoader {

    void loadListingThumbnail(@NonNull ImageView imageView, @NonNull String imageUrl);

}

package com.iliakplv.trademepreview.network;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.iliakplv.trademepreview.R;
import com.squareup.picasso.Picasso;

public class PicassoImageLoader implements ImageLoader {

    @NonNull
    private final Picasso picasso;

    public PicassoImageLoader(@NonNull final Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public void loadListingThumbnail(@NonNull ImageView imageView, @NonNull String imageUrl) {
        picasso.load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(imageView);
    }
}


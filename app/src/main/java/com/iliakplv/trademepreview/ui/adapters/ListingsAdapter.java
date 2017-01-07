package com.iliakplv.trademepreview.ui.adapters;


import android.content.Context;
import android.os.Handler;
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iliakplv.trademepreview.ApplicationModule;
import com.iliakplv.trademepreview.R;
import com.iliakplv.trademepreview.TradeMePreviewApp;
import com.iliakplv.trademepreview.api.entities.Listing;
import com.iliakplv.trademepreview.api.entities.SearchResult;
import com.iliakplv.trademepreview.network.ImageLoader;
import com.iliakplv.trademepreview.ui.views.ListingsView;

import javax.inject.Inject;
import javax.inject.Named;

public class ListingsAdapter extends RecyclerView.Adapter<ListingsAdapter.ViewHolder> {

    @Inject
    ImageLoader imageLoader;

    @Inject
    @Named(ApplicationModule.MAIN_THREAD_HANDLER)
    Handler mainThreadHandler;

    private ListingsView view;
    private SearchResult searchResult;


    public ListingsAdapter(@NonNull Context context, @NonNull ListingsView view) {
        TradeMePreviewApp.get(context).applicationComponent().inject(this);
        this.view = view;
    }


    @AnyThread
    public void setSearchResult(SearchResult searchResult) {
        this.searchResult = searchResult;
        mainThreadHandler.post(this::notifyDataSetChanged);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listing_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Listing listing = searchResult.listings[position];
        imageLoader.loadListingThumbnail(holder.thumbnail, listing.pictureHref);
        holder.title.setText(listing.title);
        holder.listingId.setText(String.valueOf(listing.listingId));
    }

    @Override
    public int getItemCount() {
        if (searchResult != null && searchResult.hasListings()) {
            return searchResult.listings.length;
        }
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView title;
        TextView listingId;

        ViewHolder(View itemView) {
            super(itemView);

            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.listing_title);
            listingId = (TextView) itemView.findViewById(R.id.listing_id);

            itemView.setOnClickListener(v -> {
                final int position = getAdapterPosition();
                view.onListingClicked(searchResult.listings[position].listingId);
            });
        }

    }
}

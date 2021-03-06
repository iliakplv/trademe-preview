package com.iliakplv.trademepreview.ui.presenters;


import android.support.annotation.NonNull;

import com.iliakplv.trademepreview.model.ListingsModel;
import com.iliakplv.trademepreview.ui.views.ListingsView;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ListingsPresenter extends Presenter<ListingsView> {

    @NonNull
    private ListingsModel listingsModel;

    public ListingsPresenter(@NonNull ListingsModel listingsModel) {
        this.listingsModel = listingsModel;
    }


    /**
     * Load listings for given search string in given category
     */
    public void loadListingsForSearch(@NonNull String categoryNumber,
                                      @NonNull String searchString,
                                      @NonNull String sortOrder) {
        showLoading();
        final Subscription subscription = listingsModel.searchListings(categoryNumber, searchString, sortOrder)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                            final ListingsView view = getView();
                            if (view != null) {
                                view.onListingsLoaded(result);
                            }
                        },
                        error -> {
                            final ListingsView view = getView();
                            if (view != null) {
                                view.onLoadingError();
                            }
                        });
        addSubscriptions(subscription);
    }

    private void showLoading() {
        final ListingsView view = getView();
        if (view != null) {
            view.onLoadingStarted();
        }
    }

}

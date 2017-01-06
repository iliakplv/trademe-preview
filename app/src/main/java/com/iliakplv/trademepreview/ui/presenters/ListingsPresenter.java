package com.iliakplv.trademepreview.ui.presenters;


import android.support.annotation.NonNull;

import com.iliakplv.trademepreview.api.entities.SearchResult;
import com.iliakplv.trademepreview.model.ListingsModel;
import com.iliakplv.trademepreview.ui.views.ListingsView;

import rx.SingleSubscriber;
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
     * Load listings for given category
     *
     * @param categoryNumber
     */
    public void loadListingsForCategory(String categoryNumber) {
        final Subscription subscription = listingsModel.getListings(categoryNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<SearchResult>() {
                    @Override
                    public void onSuccess(SearchResult result) {
                        final ListingsView view = getView();
                        if (view != null) {
                            view.onListingsLoaded(result);
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        final ListingsView view = getView();
                        if (view != null) {
                            view.onLoadingError();
                        }
                    }
                });
        addSubscriptions(subscription);
    }

    /**
     * Load listings for given search string in given category
     *
     * @param categoryNumber
     * @param searchString
     */
    public void loadListingsForSearch(String categoryNumber, String searchString) {
        final Subscription subscription = listingsModel.searchListings(categoryNumber, searchString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<SearchResult>() {
                    @Override
                    public void onSuccess(SearchResult result) {
                        final ListingsView view = getView();
                        if (view != null) {
                            view.onListingsLoaded(result);
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        final ListingsView view = getView();
                        if (view != null) {
                            view.onLoadingError();
                        }
                    }
                });
        addSubscriptions(subscription);
    }

}

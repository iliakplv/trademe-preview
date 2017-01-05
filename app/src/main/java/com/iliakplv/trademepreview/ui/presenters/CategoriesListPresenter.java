package com.iliakplv.trademepreview.ui.presenters;


import android.support.annotation.NonNull;

import com.iliakplv.trademepreview.api.entities.Category;
import com.iliakplv.trademepreview.model.CategoriesModel;
import com.iliakplv.trademepreview.ui.views.CategoriesListView;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CategoriesListPresenter extends Presenter<CategoriesListView> {

    private CategoriesModel categoriesModel;


    public CategoriesListPresenter(@NonNull CategoriesModel categoriesModel) {
        this.categoriesModel = categoriesModel;
    }

    public void loadCategory(String number) {
        final Subscription subscription = categoriesModel.getCategory(number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<Category>() {
                    @Override
                    public void onSuccess(Category category) {
                        final CategoriesListView view = getView();
                        if (view != null) {
                            view.onCategoryLoaded(category);
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        final CategoriesListView view = getView();
                        if (view != null) {
                            view.onLoadingError();
                        }
                    }
                });
        addSubscriptions(subscription);
    }

}

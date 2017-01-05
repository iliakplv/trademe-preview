package com.iliakplv.trademepreview.ui.views;


import com.iliakplv.trademepreview.api.entities.Category;

public interface CategoriesListView {

    void onLoadingStarted();

    void onCategoryLoaded(Category category);

    void onLoadingError();

    void onCategorySelected(Category category);

    /**
     * Button clicked to go up in categories hierarchy (i.e. up arrow or device's home button)
     *
     * @return {@code true} if click consumed by this view and shouldn't be handled by Android
     */
    boolean onUpClicked();

}

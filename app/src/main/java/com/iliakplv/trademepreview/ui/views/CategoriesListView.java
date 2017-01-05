package com.iliakplv.trademepreview.ui.views;


import com.iliakplv.trademepreview.api.entities.Category;

public interface CategoriesListView {

    void onLoadingStarted();

    void onCategoryLoaded(Category category);

    void onLoadingError();

    void onCategorySelected(Category category);

}

package com.iliakplv.trademepreview.ui.presenters;


import android.support.annotation.NonNull;

import com.iliakplv.trademepreview.api.entities.Category;
import com.iliakplv.trademepreview.model.CategoriesModel;
import com.iliakplv.trademepreview.ui.views.CategoriesListView;

import java.util.Stack;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.iliakplv.trademepreview.api.entities.Category.ROOT_CATEGORY_NUMBER;

public class CategoriesListPresenter extends Presenter<CategoriesListView> {

    @NonNull
    private CategoriesModel categoriesModel;
    private Stack<String> categoryNumberStack = new Stack<>();


    public CategoriesListPresenter(@NonNull CategoriesModel categoriesModel) {
        this.categoriesModel = categoriesModel;
    }


    public void loadCategory(@NonNull String categoryNumber) {
        loadCategory(categoryNumber, true);
    }

    public Stack<String> getCategoryNumberStack() {
        return (Stack<String>) categoryNumberStack.clone();
    }

    public void restoreCategoryNumberStack(@NonNull Stack<String> stack) {
        categoryNumberStack = stack;
        loadCategory(categoryNumberStack.peek(), false);
    }

    private void loadCategory(@NonNull String number, final boolean pushToStack) {
        final CategoriesListView view = getView();
        if (view != null) {
            view.onLoadingStarted();
        }
        final Subscription subscription = categoriesModel.getCategory(number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<Category>() {
                    @Override
                    public void onSuccess(Category category) {
                        if (pushToStack) {
                            categoryNumberStack.push(number);
                        }
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

    /**
     * Go up from current position in categories hierarchy
     *
     * @return {@code true} if went up, {@code false} if current position is root
     */
    public boolean goUpInHierarchy() {
        if (categoryNumberStack.isEmpty()) {
            return false;
        }

        final String currentCategory = categoryNumberStack.pop();
        if (ROOT_CATEGORY_NUMBER.equals(currentCategory)) {
            return false;
        }

        if (!categoryNumberStack.isEmpty()) {
            loadCategory(categoryNumberStack.peek(), false);
            return true;
        }

        return false;
    }

}

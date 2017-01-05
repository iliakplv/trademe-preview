package com.iliakplv.trademepreview.ui.presenters;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class Presenter<V> {

    @NonNull
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Nullable
    private volatile V view;


    @CallSuper
    public void bindView(@NonNull V view) {
        final V previousView = this.view;
        if (previousView != null) {
            throw new IllegalStateException(
                    "Previous getView is not unbounded! previousView = " + previousView);
        }
        this.view = view;
    }

    @CallSuper
    public void unbindView(@NonNull V view) {
        final V previousView = this.view;
        if (previousView == view) {
            this.view = null;
        } else {
            throw new IllegalStateException(
                    "Unexpected view! previousView = " + previousView + ", view to unbind = " + view);
        }
        compositeSubscription.clear();
    }


    @Nullable
    protected V getView() {
        return view;
    }

    protected final void addSubscriptions(@NonNull Subscription subscription,
                                          @NonNull Subscription... subscriptions) {
        compositeSubscription.add(subscription);
        for (Subscription s : subscriptions) {
            compositeSubscription.add(s);
        }
    }

    protected final void removeSubscription(@NonNull Subscription subscription) {
        compositeSubscription.remove(subscription);
    }

}

package com.iliakplv.trademepreview;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.iliakplv.trademepreview.api.ApiModule;

import timber.log.Timber;


public class TradeMePreviewApp extends Application {

    private ApplicationComponent applicationComponent;

    @NonNull
    public static TradeMePreviewApp get(@NonNull Context context) {
        return (TradeMePreviewApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = prepareApplicationComponent().build();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @NonNull
    protected DaggerApplicationComponent.Builder prepareApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule(BuildConfig.BASE_URL));
    }

    @NonNull
    public ApplicationComponent applicationComponent() {
        return applicationComponent;
    }

}

package com.iliakplv.trademepreview.ui.activities;

import android.support.v7.app.AppCompatActivity;

import com.iliakplv.trademepreview.ApplicationComponent;
import com.iliakplv.trademepreview.TradeMePreviewApp;

/**
 * Base class for all app's activities.
 * Provides easy access to ApplicationComponent.
 * All activity tracking/engagement/analytics could be initialized here.
 */
public class BaseActivity extends AppCompatActivity {

    protected ApplicationComponent getApplicationComponent() {
        return TradeMePreviewApp.get(this).applicationComponent();
    }

}

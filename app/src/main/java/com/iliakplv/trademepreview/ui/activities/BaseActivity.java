package com.iliakplv.trademepreview.ui.activities;

import android.support.v7.app.AppCompatActivity;

import com.iliakplv.trademepreview.ApplicationComponent;
import com.iliakplv.trademepreview.TradeMePreviewApp;

/**
 * Created by ilya on 4/01/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected ApplicationComponent getApplicationComponent() {
        return TradeMePreviewApp.get(this).applicationComponent();
    }

}

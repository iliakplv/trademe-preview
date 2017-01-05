package com.iliakplv.trademepreview.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.iliakplv.trademepreview.R;
import com.iliakplv.trademepreview.ui.fragments.ListingsFragment;

public class ListingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        setupToolbar();

        if (savedInstanceState == null) {
            final Bundle arguments = new Bundle();
            arguments.putString(ListingsFragment.ARG_CATEGORY_NUMBER,
                    getIntent().getStringExtra(ListingsFragment.ARG_CATEGORY_NUMBER));
            arguments.putString(ListingsFragment.ARG_TITLE,
                    getIntent().getStringExtra(ListingsFragment.ARG_TITLE));
            final ListingsFragment fragment = new ListingsFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.category_detail_container, fragment)
                    .commit();
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static void startForCategory(Activity activity, String categoryNumber, String title) {
        Intent intent = new Intent(activity, ListingsActivity.class);
        intent.putExtra(ListingsFragment.ARG_CATEGORY_NUMBER, categoryNumber);
        intent.putExtra(ListingsFragment.ARG_TITLE, title);
        activity.startActivity(intent);
    }
}

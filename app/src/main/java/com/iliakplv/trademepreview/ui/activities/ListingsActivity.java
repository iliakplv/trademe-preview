package com.iliakplv.trademepreview.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.iliakplv.trademepreview.R;
import com.iliakplv.trademepreview.ui.fragments.ListingsFragment;

import static com.iliakplv.trademepreview.ui.fragments.ListingsFragment.ARG_CATEGORY_NUMBER;
import static com.iliakplv.trademepreview.ui.fragments.ListingsFragment.ARG_SEARCH_STRING;

public class ListingsActivity extends BaseActivity {

    private static final String ARG_ACTIVITY_TITLE = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        setupToolbar();

        if (savedInstanceState == null) {
            final Intent intent = getIntent();
            final Bundle args = new Bundle();

            args.putString(ARG_CATEGORY_NUMBER, intent.getStringExtra(ARG_CATEGORY_NUMBER));
            if (intent.getExtras().containsKey(ARG_SEARCH_STRING)) {
                args.putString(ARG_SEARCH_STRING, intent.getStringExtra(ARG_SEARCH_STRING));
            }

            final ListingsFragment fragment = new ListingsFragment();
            fragment.setArguments(args);
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
            actionBar.setTitle(getIntent().getStringExtra(ARG_ACTIVITY_TITLE));
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


    public static void startForCategory(Activity activity, String categoryNumber, String categoryName) {
        Intent intent = new Intent(activity, ListingsActivity.class);
        intent.putExtra(ARG_CATEGORY_NUMBER, categoryNumber);
        intent.putExtra(ARG_ACTIVITY_TITLE, categoryName);
        activity.startActivity(intent);
    }

    public static void startForSearch(Activity activity, String categoryNumber, String searchString) {
        Intent intent = new Intent(activity, ListingsActivity.class);
        intent.putExtra(ARG_CATEGORY_NUMBER, categoryNumber);
        intent.putExtra(ARG_SEARCH_STRING, searchString);
        intent.putExtra(ARG_ACTIVITY_TITLE, "\"" + searchString + "\"");
        activity.startActivity(intent);
    }
}

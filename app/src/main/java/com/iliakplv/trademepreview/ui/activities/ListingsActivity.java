package com.iliakplv.trademepreview.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.iliakplv.trademepreview.R;
import com.iliakplv.trademepreview.model.ListingsModel;
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
                    .add(R.id.category_detail_container, fragment, ListingsFragment.TAG)
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.listings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.sort_default:
                getListingsFragment().setSortOrder(ListingsModel.SORT_ORDER_DEFAULT);
                return true;
            case R.id.sort_featured:
                getListingsFragment().setSortOrder(ListingsModel.SORT_ORDER_FEATURED);
                return true;
            case R.id.sort_title:
                getListingsFragment().setSortOrder(ListingsModel.SORT_ORDER_TITLE_ACS);
                return true;
            case R.id.sort_price:
                getListingsFragment().setSortOrder(ListingsModel.SORT_ORDER_PRICE_ASC);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public ListingsFragment getListingsFragment() {
        return (ListingsFragment) getSupportFragmentManager().findFragmentByTag(ListingsFragment.TAG);
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

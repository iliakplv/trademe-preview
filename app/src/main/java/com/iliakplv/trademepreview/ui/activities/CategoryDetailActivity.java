package com.iliakplv.trademepreview.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.iliakplv.trademepreview.R;
import com.iliakplv.trademepreview.ui.fragments.CategoryDetailFragment;

/**
 * An activity representing a single Category detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link CategoryListActivity}.
 */
public class CategoryDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show());

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(CategoryDetailFragment.ARG_CATEGORY_NUMBER,
                    getIntent().getStringExtra(CategoryDetailFragment.ARG_CATEGORY_NUMBER));
            CategoryDetailFragment fragment = new CategoryDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.category_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, CategoryListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static void startForCategory(Activity activity, String categoryNumber) {
        Intent intent = new Intent(activity, CategoryDetailActivity.class);
        intent.putExtra(CategoryDetailFragment.ARG_CATEGORY_NUMBER, categoryNumber);
        activity.startActivity(intent);
    }
}

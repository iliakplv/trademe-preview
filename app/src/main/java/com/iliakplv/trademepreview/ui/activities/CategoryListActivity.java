package com.iliakplv.trademepreview.ui.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.iliakplv.trademepreview.R;
import com.iliakplv.trademepreview.api.entities.Category;
import com.iliakplv.trademepreview.common.UiUtils;
import com.iliakplv.trademepreview.ui.adapters.CategoryListAdapter;
import com.iliakplv.trademepreview.ui.fragments.ListingsFragment;
import com.iliakplv.trademepreview.ui.presenters.CategoriesListPresenter;
import com.iliakplv.trademepreview.ui.views.CategoriesListView;

import java.util.Stack;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.iliakplv.trademepreview.ui.fragments.ListingsFragment.ARG_CATEGORY_NUMBER;
import static com.iliakplv.trademepreview.ui.fragments.ListingsFragment.ARG_SEARCH_STRING;

public class CategoryListActivity extends BaseActivity implements CategoriesListView {

    private static final String ARG_CURRENT_CATEGORY_STACK = "current_category_stack";

    private boolean twoPane;

    @Inject
    CategoriesListPresenter presenter;

    private CategoryListAdapter adapter;

    @BindView(R.id.progress_bar)
    View progressBar;
    @BindView(R.id.category_path)
    TextView categoryPath;
    @BindView(R.id.empty_placeholder)
    View emptyPlaceholder;
    @BindView(R.id.category_list)
    RecyclerView recyclerView;


    @OnClick(R.id.show_listings)
    public void onShowListingsClicked() {
        onCategorySelected(adapter.getCategory());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
        setContentView(R.layout.activity_category_list);
        ButterKnife.bind(this);
        presenter.bindView(this);

        twoPane = findViewById(R.id.category_detail_container) != null;
        setupToolbar();
        setupRecyclerView();

        if (savedInstanceState != null) {
            final Stack<String> currentCategoryStack =
                    (Stack<String>) savedInstanceState.getSerializable(ARG_CURRENT_CATEGORY_STACK);
            if (currentCategoryStack != null) {
                presenter.restoreCategoryNumberStack(currentCategoryStack);
            }
        } else {
            presenter.loadRootCategory();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_CURRENT_CATEGORY_STACK, presenter.getCategoryNumberStack());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbindView(this);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
    }

    private void setupRecyclerView() {
        adapter = new CategoryListAdapter(this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoadingStarted() {
        progressBar.setVisibility(View.VISIBLE);
        categoryPath.setVisibility(View.INVISIBLE);
        emptyPlaceholder.setVisibility(View.GONE);
    }

    @Override
    public void onCategoryLoaded(Category category) {
        adapter.setCategory(category);
        recyclerView.scrollToPosition(0);
        recyclerView.setVisibility(category.hasSubcategories() ? View.VISIBLE : View.GONE);
        progressBar.setVisibility(View.INVISIBLE);
        categoryPath.setText(getString(R.string.all_categories, category.getPath()));
        categoryPath.setVisibility(View.VISIBLE);
        emptyPlaceholder.setVisibility(category.hasSubcategories() ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onLoadingError() {
        UiUtils.showSnackbar(recyclerView, R.string.cant_load_categories);
        progressBar.setVisibility(View.INVISIBLE);
        categoryPath.setVisibility(View.VISIBLE);
        emptyPlaceholder.setVisibility(View.GONE);
    }

    @Override
    public void onCategoryClicked(Category category) {
        presenter.loadCategory(category.getNumber());
    }

    @Override
    public void onCategorySelected(Category category) {
        showListings(category, null);
    }

    @Override
    public void onSearchRequested(Category category, String searchString) {
        showListings(category, searchString);
    }

    private void showListings(@NonNull Category category, @Nullable String searchString) {
        final boolean searchMode = searchString != null;
        if (twoPane) {
            final Bundle args = new Bundle();
            args.putString(ARG_CATEGORY_NUMBER, category.getNumber());
            if (searchMode) {
                args.putString(ARG_SEARCH_STRING, searchString);
            }
            ListingsFragment fragment = new ListingsFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.category_detail_container, fragment, ListingsFragment.TAG)
                    .commit();
        } else {
            if (searchMode) {
                ListingsActivity.startForSearch(this, category.getNumber(), searchString);
            } else {
                ListingsActivity.startForCategory(this, category.getNumber(), category.getName());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleSearchIntent(intent);
    }

    private void handleSearchIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            final String searchString = intent.getStringExtra(SearchManager.QUERY);
            onSearchRequested(adapter.getCategory(), searchString);
        }
    }

    @Override
    public boolean onUpClicked() {
        return presenter.goUpInHierarchy();
    }

    @Override
    public void onBackPressed() {
        final boolean upClickConsumed = onUpClicked();
        if (!upClickConsumed) {
            super.onBackPressed();
        }
    }

}

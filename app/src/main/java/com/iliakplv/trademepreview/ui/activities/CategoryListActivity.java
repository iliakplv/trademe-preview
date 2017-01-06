package com.iliakplv.trademepreview.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
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


    @OnClick(R.id.go_back)
    public void onEmptyPlaceholderClicked() {
        onUpClicked();
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

        onLoadingStarted();
        if (savedInstanceState != null) {
            final Stack<String> currentCategoryStack =
                    (Stack<String>) savedInstanceState.getSerializable(ARG_CURRENT_CATEGORY_STACK);
            presenter.restoreCategoryNumberStack(currentCategoryStack);
        } else {
            presenter.loadCategory(Category.ROOT_CATEGORY_NUMBER);
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
        adapter = new CategoryListAdapter(this);
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
        progressBar.setVisibility(View.INVISIBLE);
        categoryPath.setText(getString(R.string.all_categories, category.getPath()));
        categoryPath.setVisibility(View.VISIBLE);
        emptyPlaceholder.setVisibility(category.hasSubcategories() ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onLoadingError() {
        UiUtils.showSnackbar(recyclerView, R.string.cant_load_categories);
        progressBar.setVisibility(View.INVISIBLE);
        categoryPath.setVisibility(View.INVISIBLE);
        emptyPlaceholder.setVisibility(View.GONE);
    }

    @Override
    public void onCategoryClicked(Category category) {
        onLoadingStarted();
        presenter.loadCategory(category.getNumber());
    }

    @Override
    public void onCategorySelected(Category category) {
        // todo pass search string here
        if (twoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(ListingsFragment.ARG_CATEGORY_NUMBER, category.getNumber());
            ListingsFragment fragment = new ListingsFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.category_detail_container, fragment, ListingsFragment.TAG)
                    .commit();
        } else {
            ListingsActivity.startForCategory(this, category.getNumber(), category.getName());
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

package com.iliakplv.trademepreview.ui.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.iliakplv.trademepreview.R;
import com.iliakplv.trademepreview.api.entities.Category;
import com.iliakplv.trademepreview.ui.adapters.CategoryListAdapter;
import com.iliakplv.trademepreview.ui.presenters.CategoriesListPresenter;
import com.iliakplv.trademepreview.ui.views.CategoriesListView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryListActivity extends BaseActivity implements CategoriesListView {

    private boolean twoPane;

    @Inject
    CategoriesListPresenter presenter;

    private CategoryListAdapter adapter;

    @BindView(R.id.category_list)
    RecyclerView recyclerView;
    @BindView(R.id.category_path)
    TextView categoryPath;
    @BindView(R.id.progress_bar)
    View progressBar;


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
        presenter.loadCategory(Category.ROOT_CATEGORY_NUMBER);
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
    }

    @Override
    public void onCategoryLoaded(Category category) {
        adapter.setCategory(category);
        categoryPath.setText(getString(R.string.all_categories, category.getPath()));
        progressBar.setVisibility(View.INVISIBLE);
        categoryPath.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadingError() {
        Snackbar.make(recyclerView, R.string.cant_load_data, Snackbar.LENGTH_LONG).show();
        progressBar.setVisibility(View.INVISIBLE);
        categoryPath.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onCategoryClicked(Category category) {
        onLoadingStarted();
        presenter.loadCategory(category.getNumber());
    }

    @Override
    public void onCategorySelected(Category category) {
        if (twoPane) {
//            Bundle arguments = new Bundle();
//            arguments.putString(CategoryDetailFragment.ARG_CATEGORY_NUMBER, holder.mItem.id);
//            CategoryDetailFragment fragment = new CategoryDetailFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.category_detail_container, fragment)
//                    .commit();
        } else {
            // todo pass search string here
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

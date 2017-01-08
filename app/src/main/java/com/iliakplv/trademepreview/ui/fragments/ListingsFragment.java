package com.iliakplv.trademepreview.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iliakplv.trademepreview.R;
import com.iliakplv.trademepreview.TradeMePreviewApp;
import com.iliakplv.trademepreview.api.entities.SearchResult;
import com.iliakplv.trademepreview.common.UiUtils;
import com.iliakplv.trademepreview.ui.adapters.ListingsAdapter;
import com.iliakplv.trademepreview.ui.presenters.ListingsPresenter;
import com.iliakplv.trademepreview.ui.views.ListingsView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListingsFragment extends Fragment implements ListingsView {

    public static final String TAG = "ListingsFragment";

    public static final String ARG_CATEGORY_NUMBER = "category_number";
    public static final String ARG_SEARCH_STRING = "search_string";


    @Inject
    ListingsPresenter presenter;

    @BindView(R.id.progress_bar)
    View progressBar;
    @BindView(R.id.empty_placeholder)
    TextView emptyPlaceholder;
    @BindView(R.id.listings_list)
    RecyclerView recyclerView;

    private ListingsAdapter adapter;

    private Mode mode;
    private String categoryNumber;
    private String searchString;


    public ListingsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TradeMePreviewApp.get(getContext()).applicationComponent().inject(this);
        final View rootView = inflater.inflate(R.layout.fragment_listings, container, false);
        ButterKnife.bind(this, rootView);
        presenter.bindView(this);

        readArguments();
        setupRecyclerView();

        if (mode == Mode.Preview) {
            presenter.loadListingsForCategory(categoryNumber);
        } else {
            presenter.loadListingsForSearch(categoryNumber, searchString);
        }

        return rootView;
    }

    private void readArguments() {
        final Bundle args = getArguments();
        if (args.containsKey(ARG_CATEGORY_NUMBER)) {
            categoryNumber = args.getString(ARG_CATEGORY_NUMBER);
        }
        if (args.containsKey(ARG_SEARCH_STRING)) {
            mode = Mode.Search;
            searchString = args.getString(ARG_SEARCH_STRING);
        } else {
            mode = Mode.Preview;
        }
    }

    private void setupRecyclerView() {
        adapter = new ListingsAdapter(getContext(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unbindView(this);
    }

    @Override
    public void onLoadingStarted() {
        progressBar.setVisibility(View.VISIBLE);
        emptyPlaceholder.setVisibility(View.GONE);
    }

    @Override
    public void onListingsLoaded(SearchResult searchResult) {
        adapter.setSearchResult(searchResult);
        final boolean emptyList = !searchResult.hasListings();
        recyclerView.scrollToPosition(0);
        recyclerView.setVisibility(emptyList ? View.GONE : View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        emptyPlaceholder.setVisibility(emptyList ? View.VISIBLE : View.GONE);
        if (emptyList) {
            emptyPlaceholder.setText(mode == Mode.Preview ?
                    R.string.listings_empty : R.string.search_empty);
        }
    }

    @Override
    public void onLoadingError() {
        UiUtils.showSnackbar(recyclerView, R.string.cant_load_listings);
        progressBar.setVisibility(View.GONE);
        emptyPlaceholder.setVisibility(View.GONE);
    }

    @Override
    public void onListingClicked(int listingId) {
        // listing details are optional
    }

    private enum Mode {
        Preview,
        Search
    }
}

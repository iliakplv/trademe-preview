package com.iliakplv.trademepreview.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iliakplv.trademepreview.R;
import com.iliakplv.trademepreview.TradeMePreviewApp;
import com.iliakplv.trademepreview.api.entities.SearchResult;
import com.iliakplv.trademepreview.ui.adapters.ListingsAdapter;
import com.iliakplv.trademepreview.ui.presenters.ListingsPresenter;
import com.iliakplv.trademepreview.ui.views.ListingsView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListingsFragment extends Fragment implements ListingsView {

    public static final String TAG = "ListingsFragment";

    public static final String ARG_CATEGORY_NUMBER = "category_number";


    @Inject
    ListingsPresenter presenter;

    @BindView(R.id.listings_list)
    RecyclerView recyclerView;

    private ListingsAdapter adapter;

    private String categoryNumber;


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

        onLoadingStarted();
        presenter.loadListingsForCategory(categoryNumber);

        return rootView;
    }

    private void readArguments() {
        final Bundle args = getArguments();
        if (args.containsKey(ARG_CATEGORY_NUMBER)) {
            categoryNumber = args.getString(ARG_CATEGORY_NUMBER);
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
        // todo show progress
    }

    @Override
    public void onListingsLoaded(SearchResult searchResult) {
        adapter.setSearchResult(searchResult);
        // todo check empty, display placeholder
    }

    @Override
    public void onLoadingError() {
        // todo show error
    }

    @Override
    public void onListingClicked(int listingId) {
        // TODO
    }
}

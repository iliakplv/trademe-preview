package com.iliakplv.trademepreview.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iliakplv.trademepreview.R;
import com.iliakplv.trademepreview.ui.activities.CategoryListActivity;
import com.iliakplv.trademepreview.ui.activities.ListingsActivity;

/**
 * A fragment representing a single Category detail screen.
 * This fragment is either contained in a {@link CategoryListActivity}
 * in two-pane mode (on tablets) or a {@link ListingsActivity}
 * on handsets.
 */
public class ListingsFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_CATEGORY_NUMBER = "category_number";

    /**
     * The dummy content this fragment is presenting.
     */
//    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_CATEGORY_NUMBER)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
//            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_CATEGORY_NUMBER));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
//                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_listings, container, false);

        // Show the dummy content as text in a TextView.
//        if (mItem != null) {
//            ((TextView) rootView.findViewById(R.id.category_detail)).setText(mItem.details);
//        }

        return rootView;
    }
}

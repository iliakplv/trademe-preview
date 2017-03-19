package com.iliakplv.trademepreview.ui.presenters;

import com.iliakplv.trademepreview.api.entities.SearchResult;
import com.iliakplv.trademepreview.model.ListingsModel;
import com.iliakplv.trademepreview.ui.views.ListingsView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Scheduler;
import rx.Single;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

import static com.iliakplv.trademepreview.model.ListingsModel.SORT_ORDER_DEFAULT;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ListingsPresenterTest {

    @Mock
    private ListingsModel listingsModel;

    @Mock
    private ListingsView listingsView;

    private ListingsPresenter listingsPresenter;


    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        listingsPresenter = new ListingsPresenter(listingsModel);
        listingsPresenter.bindView(listingsView);

        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @Test
    public void test_loadListings() {
        when(listingsModel.searchListings(any(), any(), any())).thenReturn(Single.just(new SearchResult()));

        listingsPresenter.loadListingsForSearch("0001", "", SORT_ORDER_DEFAULT);
        verify(listingsView).onLoadingStarted();
        verify(listingsModel).searchListings("0001", "", SORT_ORDER_DEFAULT);
    }

    @Test
    public void test_searchListings() {
        when(listingsModel.searchListings(any(), any(), any())).thenReturn(Single.just(new SearchResult()));

        listingsPresenter.loadListingsForSearch("0002", "test", SORT_ORDER_DEFAULT);
        verify(listingsView).onLoadingStarted();
        verify(listingsModel).searchListings("0002", "test", SORT_ORDER_DEFAULT);
    }

    @After
    public void tearDown() {
        listingsPresenter.unbindView(listingsView);
        RxAndroidPlugins.getInstance().reset();
    }

}

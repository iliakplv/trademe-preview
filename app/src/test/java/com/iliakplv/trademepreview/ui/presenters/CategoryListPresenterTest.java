package com.iliakplv.trademepreview.ui.presenters;

import com.iliakplv.trademepreview.api.entities.Category;
import com.iliakplv.trademepreview.model.CategoriesModel;
import com.iliakplv.trademepreview.ui.views.CategoriesListView;

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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryListPresenterTest {

    @Mock
    private CategoriesModel categoriesModel;

    @Mock
    private CategoriesListView categoriesListView;

    private CategoriesListPresenter categoriesListPresenter;


    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        categoriesListPresenter = new CategoriesListPresenter(categoriesModel);
        categoriesListPresenter.bindView(categoriesListView);

        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @Test
    public void test_loadSubcategories() {
        when(categoriesModel.getCategory(any())).thenReturn(Single.just(new Category()));

        categoriesListPresenter.loadCategory("0001");
        verify(categoriesListView).onLoadingStarted();
        verify(categoriesModel).getCategory("0001");
    }

    @After
    public void tearDown() {
        categoriesListPresenter.unbindView(categoriesListView);
        RxAndroidPlugins.getInstance().reset();
    }

}

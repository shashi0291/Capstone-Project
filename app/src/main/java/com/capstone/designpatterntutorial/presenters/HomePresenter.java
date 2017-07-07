package com.capstone.designpatterntutorial.presenters;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.Loader;
import android.os.Bundle;

import com.capstone.designpatterntutorial.di.MyApplication;
import com.capstone.designpatterntutorial.model.events.MainScreenEvent;
import com.capstone.designpatterntutorial.model.mainScreen.MainScreenData;
import com.capstone.designpatterntutorial.services.MainScreenLoaderTask;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created by venugopalraog on 4/23/17.
 */

public class HomePresenter implements LoaderManager.LoaderCallbacks<MainScreenData> {

    public static final int MAIN_SCREEN_DATA_LOADER_ID = 100;
    MyApplication mMyApplication;

    @Inject
    ContentResolver mContentResolver;

    @Inject
    EventBus eventBus;

    @Inject
    public HomePresenter(MyApplication application) {
        mMyApplication = application;
        mMyApplication.getAppComponent().inject(this);
    }

    public void getPattern(LoaderManager loaderManager) {
        loaderManager.initLoader(MAIN_SCREEN_DATA_LOADER_ID, null, this).forceLoad();
    }

    @Override
    public Loader<MainScreenData> onCreateLoader(int id, Bundle args) {
        return new MainScreenLoaderTask(mMyApplication, mContentResolver);
    }

    @Override
    public void onLoadFinished(Loader<MainScreenData> loader, MainScreenData data) {
        eventBus.post(new MainScreenEvent(data));
    }

    @Override
    public void onLoaderReset(Loader<MainScreenData> loader) {

    }

    public void getFavoritePattern(LoaderManager loaderManager) {

    }

    public void getRecentsPattern(LoaderManager loaderManager) {

    }
}

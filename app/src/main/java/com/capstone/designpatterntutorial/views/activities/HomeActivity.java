package com.capstone.designpatterntutorial.views.activities;

import android.content.ContentResolver;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;

import com.capstone.designpatterntutorial.R;
import com.capstone.designpatterntutorial.di.MyApplication;
import com.capstone.designpatterntutorial.model.events.MainScreenEvent;
import com.capstone.designpatterntutorial.model.mainScreen.MainScreenData;
import com.capstone.designpatterntutorial.model.mainScreen.ScreenData;
import com.capstone.designpatterntutorial.presenters.HomePresenter;
import com.capstone.designpatterntutorial.views.fragments.detailfragment.DetailFragment;
import com.capstone.designpatterntutorial.views.fragments.mainfragment.MainFragment;
import com.capstone.designpatterntutorial.views.fragments.mainfragment.MainListFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity implements MainListFragment.onListItemClicked, DrawerLayout.DrawerListener {

    private String TAG = HomeActivity.class.getSimpleName();

    @Inject
    ContentResolver mContentResolver;

    @Inject
    HomePresenter mHomePresenter;

    @Inject
    EventBus mEventBus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getAppComponent(getApplicationContext()).inject(this);
        mEventBus.register(this);
        setContentView(R.layout.activity_main);
        mHomePresenter.getMainScreenData(getLoaderManager());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mEventBus.isRegistered(this))
            mEventBus.unregister(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainScreenEvent(MainScreenEvent event) {
        Log.d(TAG, "MainScreenEvent Received from HomePresenter");
        MainScreenData mainScreenData = event.getMainScreenData();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, MainFragment.newInstance(mainScreenData), MainFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void onItemClicked(ScreenData screenData) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, DetailFragment.newInstance(screenData), DetailFragment.class.getSimpleName())
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
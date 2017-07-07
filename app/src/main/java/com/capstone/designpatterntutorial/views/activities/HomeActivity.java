package com.capstone.designpatterntutorial.views.activities;

import android.content.ContentResolver;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;

import com.capstone.designpatterntutorial.R;
import com.capstone.designpatterntutorial.di.MyApplication;
import com.capstone.designpatterntutorial.model.events.MainScreenEvent;
import com.capstone.designpatterntutorial.model.mainScreen.MainScreenData;
import com.capstone.designpatterntutorial.model.mainScreen.Pattern;
import com.capstone.designpatterntutorial.presenters.HomePresenter;
import com.capstone.designpatterntutorial.views.fragments.categoryfragment.CategoryFragment;
import com.capstone.designpatterntutorial.views.fragments.categoryfragment.CategoryListFragment;
import com.capstone.designpatterntutorial.views.fragments.patternfragment.PatternFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements CategoryListFragment.onListItemClicked, DrawerLayoutEvent {

    private String TAG = HomeActivity.class.getSimpleName();

    @Inject
    ContentResolver mContentResolver;

    @Inject
    HomePresenter mHomePresenter;

    @Inject
    EventBus mEventBus;

    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getAppComponent(getApplicationContext()).inject(this);
        mEventBus.register(this);
        setContentView(R.layout.activity_main);
        mHomePresenter.getPattern(getLoaderManager());
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mEventBus.isRegistered(this))
            mEventBus.unregister(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainScreenEvent(MainScreenEvent event) {
        Log.d(TAG, "Received Data from Database");
        MainScreenData mainScreenData = event.getMainScreenData();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, CategoryFragment.newInstance(mainScreenData), CategoryFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void onItemClicked(Pattern pattern) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, PatternFragment.newInstance(pattern), PatternFragment.class.getSimpleName())
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void closeDrawerLayout() {
        drawer.closeDrawers();
    }

    @Override
    public void openDrawerLayout() {
        drawer.openDrawer(Gravity.LEFT);
    }
}
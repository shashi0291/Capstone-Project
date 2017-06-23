package com.capstone.designpatterntutorial.views.fragments.mainfragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.capstone.designpatterntutorial.R;
import com.capstone.designpatterntutorial.commons.Constants;
import com.capstone.designpatterntutorial.di.MyApplication;
import com.capstone.designpatterntutorial.model.mainScreen.MainScreenData;
import com.capstone.designpatterntutorial.model.mainScreen.MainScreenTab;
import com.capstone.designpatterntutorial.views.fragments.BaseFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by gubbave on 5/2/2017.
 */

public class MainFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    private static final String TAG = MainFragment.class.getSimpleName();
    private static final String MAIN_SCREEN_DATA = "MainScreenData";

    private MainScreenData mainScreenData;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    private MainScreenAdapter mMainScreenAdapter;

    public static Fragment newInstance(MainScreenData mainScreenData) {
        if (mainScreenData == null) {
            throw new IllegalArgumentException("MainScreenData cannot be NULL");
        }

        Bundle args = new Bundle();
        args.putParcelable(MAIN_SCREEN_DATA, mainScreenData);

        Fragment fragment = new MainFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected void loadFragmentArguments() {
        if (getArguments() != null) {
            mainScreenData = getArguments().getParcelable(MAIN_SCREEN_DATA);
        }
    }

    @Override
    protected void injectFragment() {
        MyApplication.getAppComponent(MyApplication.getAppContext()).inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment;
    }

    @Override
    protected void initFragment(View rootView) {

        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

        mMainScreenAdapter = new MainScreenAdapter(getChildFragmentManager(),   mainScreenData.getMainScreenTabList());
        mViewPager.setAdapter(mMainScreenAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void setupTabs() {
        for (MainScreenTab tabDetails : mainScreenData.getMainScreenTabList()) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabDetails.getCategoryName()));
        }
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private class MainScreenAdapter  extends FragmentStatePagerAdapter {

        List<MainScreenTab> mMainScreenTabList;

        public MainScreenAdapter(FragmentManager fm, List<MainScreenTab> mainScreenTabList) {
            super(fm);
            this.mMainScreenTabList = mainScreenTabList;
        }

        @Override
        public int getCount() {
            return mMainScreenTabList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getItemAtPosition(position).getCategoryName();
        }

        public MainScreenTab getItemAtPosition(int position) {
            return mMainScreenTabList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            MainScreenTab mainScreenTab = mMainScreenTabList.get(position);

            switch (mainScreenTab.getCategoryName()) {
                case Constants.CREATIONAL_CATEGORY_NAME:
                case Constants.STRUCTURAL_CATEGORY_NAME:
                case Constants.BEHAVIORAL_CATEGORY_NAME:
                    fragment = MainListFragment.newInstance(mainScreenTab.getScreenDataList());
                    break;
                default:
                    Log.d(TAG, " Invalid Category Name Received...");
            }

            return fragment;
        }
    }
}

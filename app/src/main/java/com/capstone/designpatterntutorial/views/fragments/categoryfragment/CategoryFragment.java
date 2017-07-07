package com.capstone.designpatterntutorial.views.fragments.categoryfragment;

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
import com.capstone.designpatterntutorial.model.mainScreen.Category;
import com.capstone.designpatterntutorial.model.mainScreen.MainScreenData;
import com.capstone.designpatterntutorial.views.activities.HomeActivity;
import com.capstone.designpatterntutorial.views.fragments.BaseFragment;

import java.util.List;

import butterknife.BindView;

/**
 * Created by gubbave on 5/2/2017.
 */

public class CategoryFragment extends BaseFragment implements TabLayout.OnTabSelectedListener, View.OnClickListener {

    private static final String TAG = CategoryFragment.class.getSimpleName();
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

        Fragment fragment = new CategoryFragment();
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

        mMainScreenAdapter = new MainScreenAdapter(getChildFragmentManager(), mainScreenData.getCategoryList());
        mViewPager.setAdapter(mMainScreenAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        mToolbar.setNavigationIcon(R.drawable.ic_menu_black);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).openDrawerLayout();
            }
        });
    }

    public void setupTabs() {
        for (Category tabDetails : mainScreenData.getCategoryList()) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabDetails.getName()));
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

    @Override
    public void onClick(View v) {

    }

    private class MainScreenAdapter extends FragmentStatePagerAdapter {

        List<Category> mCategoryList;

        public MainScreenAdapter(FragmentManager fm, List<Category> categoryList) {
            super(fm);
            this.mCategoryList = categoryList;
        }

        @Override
        public int getCount() {
            return mCategoryList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getItemAtPosition(position).getName();
        }

        public Category getItemAtPosition(int position) {
            return mCategoryList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            Category category = mCategoryList.get(position);

            switch (category.getName()) {
                case Constants.CREATIONAL_CATEGORY_NAME:
                case Constants.STRUCTURAL_CATEGORY_NAME:
                case Constants.BEHAVIORAL_CATEGORY_NAME:
                    fragment = CategoryListFragment.newInstance(category.getPatternList());
                    break;
                default:
                    Log.d(TAG, " Invalid Category Name Received... " + category.getName());
            }

            return fragment;
        }
    }
}

package com.capstone.designpatterntutorial.views.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.capstone.designpatterntutorial.R;
import com.capstone.designpatterntutorial.di.MyApplication;
import com.capstone.designpatterntutorial.presenters.HomePresenter;
import com.capstone.designpatterntutorial.views.activities.HomeActivity;
import com.capstone.designpatterntutorial.views.adapters.NavigationMenuAdapter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by gubbave on 5/19/2017.
 */

public class NavigationMenuFragment extends BaseFragment implements NavigationMenuAdapter.OnItemClickListener {

    public static final int DEFAULT_SELECTED_INDEX = 0;
    @BindView(R.id.navigation_menu_list)
    RecyclerView mRecyclerView;

    @Inject
    HomePresenter homePresenter;


    private NavigationMenuAdapter mAdapter;
    private String[] menuList;

    public static NavigationMenuFragment newInstance() {
        Bundle args = new Bundle();

        NavigationMenuFragment fragment = new NavigationMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectFragment() {
        MyApplication.getAppComponent(MyApplication.getAppContext()).inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.navigation_menu_fragment;
    }

    @Override
    protected void initFragment(View rootView) {
        super.initFragment(rootView);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        menuList = getResources().getStringArray(R.array.navigation_menu_list);

        mAdapter = new NavigationMenuAdapter(menuList);
        mAdapter.setSelectedItem(DEFAULT_SELECTED_INDEX);
        mAdapter.setOnItemClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {

        int prevSelectedIndex = mAdapter.getSelectedItem();

        if (prevSelectedIndex != position) {
            mAdapter.setSelectedItem(position);
            mAdapter.notifyItemChanged(prevSelectedIndex);
            mAdapter.notifyItemChanged(position);
        }

        ((HomeActivity) getActivity()).closeDrawerLayout();

        if (getResources().getString(R.string.design_pattern).equalsIgnoreCase(menuList[position])) {
            homePresenter.getPattern(getActivity().getLoaderManager());
        } else if (getResources().getString(R.string.favorites).equalsIgnoreCase(menuList[position])) {
            homePresenter.getFavoritePattern(getActivity().getLoaderManager());
        } else if (getResources().getString(R.string.recents).equalsIgnoreCase(menuList[position])) {
            homePresenter.getRecentsPattern(getActivity().getLoaderManager());
        }
    }
}
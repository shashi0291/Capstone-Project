package com.capstone.designpatterntutorial.views.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.capstone.designpatterntutorial.R;

import butterknife.BindView;

/**
 * Created by gubbave on 5/19/2017.
 */

public class NavigationMenuFragment extends BaseFragment {

    @BindView(R.id.navigation_menu_list)
    RecyclerView mRecyclerView;

    public static NavigationMenuFragment newInstance() {
        Bundle args = new Bundle();

        NavigationMenuFragment fragment = new NavigationMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.navigation_menu_fragment;
    }

    @Override
    protected void initFragment(View rootView) {
        super.initFragment(rootView);
    }
}

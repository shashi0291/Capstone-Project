package com.capstone.designpatterntutorial.views.fragments.mainfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.capstone.designpatterntutorial.R;
import com.capstone.designpatterntutorial.model.mainScreen.ScreenData;
import com.capstone.designpatterntutorial.views.activities.HomeActivity;
import com.capstone.designpatterntutorial.views.adapters.MainListAdapter;
import com.capstone.designpatterntutorial.views.fragments.BaseFragment;

import java.util.ArrayList;

/**
 * Created by gubbave on 5/8/2017.
 */

public class MainListFragment extends BaseFragment implements MainListAdapter.OnItemClickListener {

    private static final String TAG = MainListFragment.class.getSimpleName();
    private static final String MAIN_SCREEN_DETAILS_FRAGMENT_DATA = "MainScreenDetailsFragmentData";

    private ArrayList<ScreenData> mScreenDataList;
    private RecyclerView mRecyclerView;
    private MainListAdapter mAdapter;

    public static Fragment newInstance(ArrayList<ScreenData> screenDataList) {
        if (screenDataList == null) {
            throw new IllegalArgumentException("ScreenData List cannot be NULL");
        }

        Bundle args = new Bundle();
        args.putParcelableArrayList(MAIN_SCREEN_DETAILS_FRAGMENT_DATA, screenDataList);

        Fragment fragment = new MainListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void loadFragmentArguments() {
        if (getArguments() != null) {
            mScreenDataList = getArguments().getParcelableArrayList(MAIN_SCREEN_DETAILS_FRAGMENT_DATA);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_screen_details;
    }

    @Override
    protected void initFragment(View rootView) {
        mRecyclerView = (RecyclerView) rootView;

        if (mScreenDataList == null)
            return;

        mAdapter = new MainListAdapter(mScreenDataList);
        mAdapter.setOnItemClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        ScreenData screenData = mScreenDataList.get(position);
        ((HomeActivity)getActivity()).onItemClicked(screenData);
    }

    public interface onListItemClicked {
        void onItemClicked(ScreenData screenData);
    }
}

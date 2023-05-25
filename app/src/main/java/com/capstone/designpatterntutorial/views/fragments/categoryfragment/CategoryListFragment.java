package com.capstone.designpatterntutorial.views.fragments.categoryfragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.capstone.designpatterntutorial.R;
import com.capstone.designpatterntutorial.databinding.MainScreenDetailsBinding;
import com.capstone.designpatterntutorial.model.mainscreen.Pattern;
import com.capstone.designpatterntutorial.views.activities.HomeActivity;
import com.capstone.designpatterntutorial.views.adapters.MainListAdapter;
import com.capstone.designpatterntutorial.views.fragments.BaseFragment;

import java.util.ArrayList;

/**
 * Created by gubbave on 5/8/2017.
 */

public class CategoryListFragment extends BaseFragment<MainScreenDetailsBinding> implements MainListAdapter.OnItemClickListener {

    private static final String TAG = CategoryListFragment.class.getSimpleName();
    private static final String MAIN_SCREEN_DETAILS_FRAGMENT_DATA = "MainScreenDetailsFragmentData";

    private ArrayList<Pattern> mPatternList;
    private MainListAdapter mAdapter;

    public static Fragment newInstance(ArrayList<Pattern> patternList) {
        if (patternList == null) {
            throw new IllegalArgumentException("Pattern List cannot be NULL");
        }

        Bundle args = new Bundle();
        args.putParcelableArrayList(MAIN_SCREEN_DETAILS_FRAGMENT_DATA, patternList);

        Fragment fragment = new CategoryListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void loadFragmentArguments() {
        if (getArguments() != null) {
            mPatternList = getArguments().getParcelableArrayList(MAIN_SCREEN_DETAILS_FRAGMENT_DATA);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_screen_details;
    }

    @Override
    protected void initFragment(View rootView) {
        if (mPatternList == null)
            return;

        mAdapter = new MainListAdapter(mPatternList);
        mAdapter.setOnItemClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Pattern pattern = mPatternList.get(position);
        ((HomeActivity) getActivity()).onItemClicked(pattern);
    }

    public interface onListItemClicked {
        void onItemClicked(Pattern pattern);
    }
}

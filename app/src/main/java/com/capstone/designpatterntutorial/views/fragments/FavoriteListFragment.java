package com.capstone.designpatterntutorial.views.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.capstone.designpatterntutorial.R;
import com.capstone.designpatterntutorial.databinding.FavoriteFragmentBinding;
import com.capstone.designpatterntutorial.di.MyApplication;
import com.capstone.designpatterntutorial.model.events.FavoriteScreenEvent;
import com.capstone.designpatterntutorial.model.favorite.FavoriteScreenData;
import com.capstone.designpatterntutorial.model.mainscreen.Pattern;
import com.capstone.designpatterntutorial.presenters.FavoritePresenter;
import com.capstone.designpatterntutorial.views.activities.HomeActivity;
import com.capstone.designpatterntutorial.views.adapters.MainListAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by gubbave on 7/12/2017.
 */

public class FavoriteListFragment extends BaseFragment<FavoriteFragmentBinding> implements MainListAdapter.OnItemClickListener {

    private static final String TAG = FavoriteListFragment.class.getSimpleName();
    private static final String FAVORITE_FRAGMENT_DATA = "FavoriteFragmentData";

    private FavoriteScreenData favoriteScreenData;
    private MainListAdapter mAdapter;

    @Inject
    FavoritePresenter favoritePresenter;

    @Inject
    EventBus mEventBus;


    public static Fragment newInstance() {
        Fragment fragment = new FavoriteListFragment();
        return fragment;
    }

    public static Fragment newInstance(FavoriteScreenData favoriteScreenData) {
        if (favoriteScreenData == null) {
            throw new IllegalArgumentException("Pattern List cannot be NULL");
        }

        Bundle args = new Bundle();
        args.putParcelable(FAVORITE_FRAGMENT_DATA, favoriteScreenData);

        Fragment fragment = new FavoriteListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mEventBus.isRegistered(this))
            mEventBus.unregister(this);
    }

    @Override
    protected void injectFragment() {
        MyApplication.getAppComponent(MyApplication.getAppContext()).inject(this);
    }

    @Override
    protected void loadFragmentArguments() {
        if (getArguments() != null) {
            favoriteScreenData = getArguments().getParcelable(FAVORITE_FRAGMENT_DATA);
        } else {
            favoritePresenter.getFavoriteScreenData(getActivity().getLoaderManager());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.favorite_fragment;
    }

    @Override
    protected void initFragment(View rootView) {

        if (favoriteScreenData == null)
            return;

        loadScreenData();
    }

    @Override
    protected void initFragmentCreation(Bundle savedInstanceState) {
        mEventBus.register(this);
    }

    private void loadScreenData() {
        setToolbarTitle(R.string.favorite_title);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setNavigationIcon(R.drawable.icon_navigation_menu);
        mAdapter = new MainListAdapter(favoriteScreenData.getPatternList());
        mAdapter.setOnItemClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.favoriteList.setLayoutManager(linearLayoutManager);
        binding.favoriteList.setAdapter(mAdapter);

        if (favoriteScreenData.getPatternList().size() == 0) {
            binding.favoriteList.setVisibility(View.GONE);
            binding.message.setVisibility(View.VISIBLE);
            binding.message.setText(R.string.no_favorite_msg);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (favoriteScreenData != null)
            favoritePresenter.getFavoriteScreenData(getActivity().getLoaderManager());
    }

    @Override
    public void onItemClick(View view, int position) {
        Pattern pattern = favoriteScreenData.getPatternList().get(position);
        pattern.setFavorite(true);
        ((HomeActivity) getActivity()).onItemClicked(pattern);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFavoriteScreenEvent(FavoriteScreenEvent event) {
        favoriteScreenData = event.getFavoriteScreenData();
        loadScreenData();
    }
}
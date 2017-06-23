package com.capstone.designpatterntutorial.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by gubbave on 5/3/2017.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder mUnBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.injectFragment();
        this.loadFragmentArguments();
        this.initFragmentCreation(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        mUnBinder = ButterKnife.bind(this, rootView);
        initFragment(rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    //Inject the Fragment to dagger
    protected void injectFragment() {   }

    //Method to get the layout id
    protected abstract int getLayoutId();

    //Method to init Fragment Creation
    protected void initFragmentCreation(Bundle savedInstanceState) {    }

    //Method to load Fragment Arguments
    protected void loadFragmentArguments() {    }

    //Method to init View
    protected void initFragment(View rootView) {    }
}

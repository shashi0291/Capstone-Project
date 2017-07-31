package com.capstone.designpatterntutorial.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.capstone.designpatterntutorial.R;
import com.capstone.designpatterntutorial.views.activities.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by gubbave on 5/3/2017.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder mUnBinder;

    @Nullable
    @BindView(R.id.title)
    protected TextView toolbarTitle;

    @Nullable
    @BindView(R.id.tool_bar)
    protected Toolbar toolbar;


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

        initToolbar();
        initFragment(rootView);
        return rootView;
    }

    private void initToolbar() {
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleNavigationClick();
                }
            });
        }
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

    protected void handleNavigationClick() {
        ((HomeActivity) getActivity()).openDrawerLayout();
    }

    protected void setToolbarTitle(int resId) {
        toolbarTitle.setText(resId);
    }

    protected void setToolbarTitle(String title) {
        toolbarTitle.setText(title);
    }
}

package com.capstone.designpatterntutorial.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.capstone.designpatterntutorial.views.activities.HomeActivity;

import butterknife.Unbinder;

/**
 * Created by gubbave on 5/3/2017.
 */

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    private Unbinder mUnBinder;
    /*

        @Nullable
        @BindView(R.id.title)
        protected TextView toolbarTitle;

        @Nullable
        @BindView(R.id.tool_bar)
        protected Toolbar toolbar;
    */
    protected Toolbar toolbar;

    protected T binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.injectFragment();
        this.initFragmentCreation(savedInstanceState);
        this.loadFragmentArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        initToolbar();
        Thread.dumpStack();
        initFragment(binding.getRoot());
        return binding.getRoot();
    }

    private void initToolbar() {
/*        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

            toolbar.setNavigationOnClickListener(v -> handleNavigationClick());
        }*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    //Inject the Fragment to dagger
    protected void injectFragment() {
    }

    //Method to get the layout id
    protected abstract int getLayoutId();

    //Method to init Fragment Creation
    protected void initFragmentCreation(Bundle savedInstanceState) {
    }

    //Method to load Fragment Arguments
    protected void loadFragmentArguments() {
    }

    //Method to init View
    protected void initFragment(View rootView) {
    }

    protected void handleNavigationClick() {
        ((HomeActivity) getActivity()).openDrawerLayout();
    }

    protected void setToolbarTitle(int resId) {
//        toolbarTitle.setText(resId);
    }

    protected void setToolbarTitle(String title) {
//        toolbarTitle.setText(title);
    }
}

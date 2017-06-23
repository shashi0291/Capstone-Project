package com.capstone.designpatterntutorial.views.fragments.detailfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.capstone.designpatterntutorial.R;
import com.capstone.designpatterntutorial.model.mainScreen.ScreenData;
import com.capstone.designpatterntutorial.views.fragments.BaseFragment;

/**
 * Created by gubbave on 5/19/2017.
 */

public class DetailFragment extends BaseFragment {

    private static final String DETAIL_SCREEN_DATA = "DetailScreenData";

    @Override
    protected int getLayoutId() {
        return R.layout.detail_fragment;
    }

    public static Fragment newInstance(ScreenData screenData) {
        if (screenData == null) {
            throw new IllegalArgumentException("ScreenData cannot be NULL");
        }

        Bundle args = new Bundle();
        args.putParcelable(DETAIL_SCREEN_DATA, screenData);
        Fragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

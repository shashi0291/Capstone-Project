package com.capstone.designpatterntutorial.views.fragments.patternfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.webkit.WebView;

import com.capstone.designpatterntutorial.R;
import com.capstone.designpatterntutorial.model.mainScreen.Pattern;
import com.capstone.designpatterntutorial.views.fragments.BaseFragment;

import butterknife.BindView;

/**
 * Created by gubbave on 5/19/2017.
 */

public class PatternFragment extends BaseFragment {

    private static final String PATTERN_DETAILS = "PatternDetails";

    @BindView(R.id.pattern_description)
    WebView description;

    private Pattern patternData;

    @Override
    protected int getLayoutId() {
        return R.layout.detail_fragment;
    }

    public static Fragment newInstance(Pattern pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be NULL");
        }

        Bundle args = new Bundle();
        args.putParcelable(PATTERN_DETAILS, pattern);
        Fragment fragment = new PatternFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void loadFragmentArguments() {
        if (getArguments() != null) {
            patternData = getArguments().getParcelable(PATTERN_DETAILS);
        }

    }

    @Override
    protected void initFragment(View rootView) {
        super.initFragment(rootView);
        description.loadData(patternData.getDescription(), "text/html", null);
//        description.setText(Html.fromHtml(patternData.getDescription()));
    }
}

package com.capstone.designpatterntutorial.views.fragments.patternfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.capstone.designpatterntutorial.R;
import com.capstone.designpatterntutorial.databinding.DetailFragmentBinding;
import com.capstone.designpatterntutorial.model.mainscreen.Pattern;
import com.capstone.designpatterntutorial.services.FavoriteDbService;
import com.capstone.designpatterntutorial.views.activities.HomeActivity;
import com.capstone.designpatterntutorial.views.fragments.BaseFragment;

/**
 * Created by gubbave on 5/19/2017.
 */

public class PatternFragment extends BaseFragment<DetailFragmentBinding> {

    private static final String PATTERN_DETAILS = "PatternDetails";

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
/*        ((HomeActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getActivity().getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
*/

        binding.collapsingToolbar.setTitle(patternData.getName());
        binding.collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        binding.patternDescription.loadData(patternData.getDescription(), "text/html", null);
        binding.favoriteFab.setImageResource(patternData.isFavorite() ? R.drawable.icon_favorite_selected : R.drawable.icon_favorite_unselected);

        binding.favoriteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FavoriteDbService.class);

                if (patternData.isFavorite()) {
                    binding.favoriteFab.setImageResource(R.drawable.icon_favorite_unselected);
                    patternData.setFavorite(false);
                    intent.putExtra(FavoriteDbService.OPERATION_FAVORITE, FavoriteDbService.OPERATION_REMOVE);
                } else {
                    binding.favoriteFab.setImageResource(R.drawable.icon_favorite_selected);
                    patternData.setFavorite(true);
                    intent.putExtra(FavoriteDbService.OPERATION_FAVORITE, FavoriteDbService.OPERATION_ADD);
                }
                intent.putExtra(FavoriteDbService.PATTERN, patternData);
                getActivity().startService(intent);
            }
        });
    }

    @Override
    protected void handleNavigationClick() {
        getActivity().onBackPressed();
    }
}
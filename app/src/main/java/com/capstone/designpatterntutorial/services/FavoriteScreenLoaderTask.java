package com.capstone.designpatterntutorial.services;

import android.content.AsyncTaskLoader;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

import com.capstone.designpatterntutorial.database.DesignPatternContract;
import com.capstone.designpatterntutorial.model.converter.MainScreenConverter;
import com.capstone.designpatterntutorial.model.favorite.FavoriteScreenData;

/**
 * Created by gubbave on 7/12/2017.
 */

public class FavoriteScreenLoaderTask extends AsyncTaskLoader<FavoriteScreenData> {

    private final ContentResolver mContentResolver;

    public FavoriteScreenLoaderTask(Context context, ContentResolver contentResolver) {
        super(context);
        mContentResolver = contentResolver;
    }

    @Override
    public FavoriteScreenData loadInBackground() {
        Cursor favoriteCursor = mContentResolver.query(DesignPatternContract.FavoritePatternEntry.CONTENT_URI,
                null,
                null,
                null,
                null);

        FavoriteScreenData favoriteScreenData = MainScreenConverter.convertFavoriteScreenData(favoriteCursor);

        return favoriteScreenData;
    }
}

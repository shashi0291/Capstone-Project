package com.capstone.designpatterntutorial.services;

import android.content.AsyncTaskLoader;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

import com.capstone.designpatterntutorial.database.DesignPatternContract.CategoryEntry;
import com.capstone.designpatterntutorial.database.DesignPatternContract.PatternEntry;
import com.capstone.designpatterntutorial.model.converter.MainScreenConverter;
import com.capstone.designpatterntutorial.model.mainscreen.Category;
import com.capstone.designpatterntutorial.model.mainscreen.MainScreenData;

import java.util.List;

/**
 * Created by gubbave on 4/27/2017.
 */

public class MainScreenLoaderTask extends AsyncTaskLoader<MainScreenData> {

    private final ContentResolver mContentResolver;

    public MainScreenLoaderTask(Context context, ContentResolver contentResolver) {
        super(context);
        this.mContentResolver = contentResolver;
    }

    @Override
    public MainScreenData loadInBackground() {

        Cursor categoryListCursor = mContentResolver.query(CategoryEntry.CONTENT_URI,
                                            null,
                                            null,
                                            null,
                                            null);

        MainScreenData mainScreenData = MainScreenConverter.convertCategoryListEntry(categoryListCursor);

        if (mainScreenData != null) {
            List<Category> categoryList = mainScreenData.getCategoryList();
            for (Category category : categoryList) {
                String selection = String.format("%s=?", PatternEntry.COLUMN_CATEGORY_ID);
                String[] selectionArgs = {String.valueOf(category.getId())};
                Cursor pattern = mContentResolver.query(PatternEntry.CONTENT_URI,
                        PatternEntry.PATTERN_COLUMNS,
                        selection,
                        selectionArgs,
                        null);
                MainScreenConverter.convertCategoryDetailsEntry(mContentResolver, pattern, category.getPatternList());
            }
        }

        return mainScreenData;
    }
}

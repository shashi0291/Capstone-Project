package com.capstone.designpatterntutorial.services;

import android.content.AsyncTaskLoader;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

import com.capstone.designpatterntutorial.converter.MainScreenConverter;
import com.capstone.designpatterntutorial.database.DesignPatternContract.CategoryDetailsEntry;
import com.capstone.designpatterntutorial.database.DesignPatternContract.CategoryListEntry;
import com.capstone.designpatterntutorial.model.mainScreen.MainScreenData;
import com.capstone.designpatterntutorial.model.mainScreen.ScreenData;

import java.util.ArrayList;
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

        MainScreenData mainScreenData = new MainScreenData();
        List<ScreenData> screenDataList = new ArrayList();

        mainScreenData.setScreenDataList(screenDataList);

        //Query CategoryList Table from Database
        Cursor categoryListCursor =  mContentResolver.query(CategoryListEntry.CONTENT_URI,
                                            null,
                                            null,
                                            null,
                                            null);

        MainScreenConverter.convertCategoryListEntry(categoryListCursor, mainScreenData);

        //Query CategoryDetails Table from Database for CategoryId retreived from CategoryList Table
        for (int i = 0; i < categoryListCursor.getCount(); i++) {
            int categoryId = categoryListCursor.getInt(categoryListCursor.getColumnIndex(CategoryListEntry.COLUMN_CATEGORY_ID));
            String selection = String.format("%s = %d", CategoryDetailsEntry.COLUMN_CATEGORY_ID, categoryId);
            Cursor categoryDetailsCursor = mContentResolver.query(CategoryDetailsEntry.CONTENT_URI,
                                                    null,
                                                    selection,
                                                    null,
                                                    null);
            screenDataList.add(MainScreenConverter.convertCategoryDetailsEntry(categoryDetailsCursor));
        }

        return mainScreenData;
    }
}

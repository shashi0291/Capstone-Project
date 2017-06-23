package com.capstone.designpatterntutorial.services;

import android.content.AsyncTaskLoader;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

import com.capstone.designpatterntutorial.converter.MainScreenConverter;
import com.capstone.designpatterntutorial.database.DesignPatternContract.CategoryDetailsEntry;
import com.capstone.designpatterntutorial.database.DesignPatternContract.CategoryListEntry;
import com.capstone.designpatterntutorial.model.mainScreen.MainScreenData;
import com.capstone.designpatterntutorial.model.mainScreen.MainScreenTab;

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

        //Query CategoryList Table from Database
        Cursor categoryListCursor =  mContentResolver.query(CategoryListEntry.CONTENT_URI,
                                            null,
                                            null,
                                            null,
                                            null);

        MainScreenData mainScreenData = MainScreenConverter.convertCategoryListEntry(categoryListCursor);

        if (mainScreenData != null) {
            List<MainScreenTab> mainScreenTabList = mainScreenData.getMainScreenTabList();
            //Query CategoryDetails Table from Database for CategoryId retreived from CategoryList Table
            for (MainScreenTab mainScreenTab : mainScreenTabList) {
                String selection = String.format("%s=?", CategoryDetailsEntry.COLUMN_CATEGORY_ID);
                String[] selectionArgs = {String.valueOf(mainScreenTab.getCategoryId())};
                Cursor categoryDetailsCursor = mContentResolver.query(CategoryDetailsEntry.CONTENT_URI,
                        CategoryDetailsEntry.CATEGORY_DETAILS_COLUMNS,
                        selection,
                        selectionArgs,
                        null);
                MainScreenConverter.convertCategoryDetailsEntry(categoryDetailsCursor, mainScreenTab.getScreenDataList());
            }
        }

        return mainScreenData;
    }
}

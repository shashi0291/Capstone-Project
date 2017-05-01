package com.capstone.designpatterntutorial.converter;


import android.database.Cursor;

import com.capstone.designpatterntutorial.database.DesignPatternContract.CategoryDetailsEntry;
import com.capstone.designpatterntutorial.database.DesignPatternContract.CategoryListEntry;
import com.capstone.designpatterntutorial.model.mainScreen.MainScreenData;
import com.capstone.designpatterntutorial.model.mainScreen.ScreenData;


/**
 * Created by gubbave on 4/27/2017.
 */

public class MainScreenConverter{

    //Convert data from CategoryList Table to model
    public static MainScreenData convertCategoryListEntry(Cursor cursor, MainScreenData mainScreenData) {

        if (cursor != null && cursor.moveToFirst()) {
            mainScreenData.setCategoryId(cursor.getInt(cursor.getColumnIndex(CategoryListEntry.COLUMN_CATEGORY_ID)));
            mainScreenData.setTitle(cursor.getString(cursor.getColumnIndex(CategoryListEntry.COLUMN_DISPLAY_TITLE)));
            mainScreenData.setCategoryName(cursor.getString(cursor.getColumnIndex(CategoryListEntry.COLUMN_CATEGORY_NAME)));
        }

        return mainScreenData;
    }

    //Convert data from CategoryDetails Table to model
    public static ScreenData convertCategoryDetailsEntry(Cursor cursor) {
        ScreenData screenData = new ScreenData();

        if (cursor != null && cursor.moveToFirst()) {
            screenData.setCategoryId(cursor.getInt(cursor.getColumnIndex(CategoryDetailsEntry.COLUMN_CATEGORY_ID)));
            screenData.setDisplayTitle(cursor.getString(cursor.getColumnIndex(CategoryDetailsEntry.COLUMN_DISPLAY_TITLE)));
            screenData.setCategoryName(cursor.getString(cursor.getColumnIndex(CategoryDetailsEntry.COLUMN_CATEGORY_NAME)));
            screenData.setDescription(cursor.getString(cursor.getColumnIndex(CategoryDetailsEntry.COLUMN_DESCRIPTION)));
            screenData.setImplementation(cursor.getString(cursor.getColumnIndex(CategoryDetailsEntry.COLUMN_IMPLEMENTATION)));
            screenData.setImageName(cursor.getString(cursor.getColumnIndex(CategoryDetailsEntry.COLUMN_IMAGE_NAME)));
        }

        return screenData;
    }
}

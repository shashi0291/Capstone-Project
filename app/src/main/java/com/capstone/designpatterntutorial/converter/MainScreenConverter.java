package com.capstone.designpatterntutorial.converter;


import android.database.Cursor;

import com.capstone.designpatterntutorial.database.DesignPatternContract.CategoryDetailsEntry;
import com.capstone.designpatterntutorial.database.DesignPatternContract.CategoryListEntry;
import com.capstone.designpatterntutorial.model.mainScreen.MainScreenData;
import com.capstone.designpatterntutorial.model.mainScreen.MainScreenTab;
import com.capstone.designpatterntutorial.model.mainScreen.ScreenData;

import java.util.ArrayList;


/**
 * Created by gubbave on 4/27/2017.
 */

public class MainScreenConverter{

    //Convert data from CategoryList Table to model
    public static MainScreenData convertCategoryListEntry(Cursor cursor) {

        if (cursor == null || cursor.getCount() <= 0) {
            return null;
        }

        MainScreenData mainScreenData = new MainScreenData();
        ArrayList<MainScreenTab> tabList = new ArrayList<>();

        cursor.moveToFirst();
        mainScreenData.setTitle(cursor.getString(cursor.getColumnIndex(CategoryListEntry.COLUMN_DISPLAY_TITLE)));
        mainScreenData.setDescription(cursor.getString(cursor.getColumnIndex(CategoryListEntry.COLUMN_CATEGORY_NAME)));


        for (int pos = 0; pos < cursor.getCount(); pos++) {
            MainScreenTab tabDetails = new MainScreenTab();
            ArrayList<ScreenData> screenDataList = new ArrayList<>();

            tabDetails.setCategoryId(cursor.getInt(cursor.getColumnIndex(CategoryListEntry.COLUMN_CATEGORY_ID)));
            tabDetails.setCategoryName(cursor.getString(cursor.getColumnIndex(CategoryListEntry.COLUMN_CATEGORY_NAME)));
            tabDetails.setContentDescription(cursor.getString(cursor.getColumnIndex(CategoryListEntry.COLUMN_CATEGORY_NAME)));
            tabDetails.setScreenDataList(screenDataList);

            tabList.add(tabDetails);
            cursor.moveToNext();
        }

        mainScreenData.setMainScreenTabList(tabList);
        return mainScreenData;
    }

    //Convert data from CategoryDetails Table to model
    public static void convertCategoryDetailsEntry(Cursor cursor, ArrayList<ScreenData> screenDataList) {

        if (cursor == null || cursor.getCount() <= 0) {
            return ;
        }

        cursor.moveToFirst();
        for (int pos = 0; pos < cursor.getCount(); pos++) {
            ScreenData screenData = new ScreenData();

            screenData.setCategoryId(cursor.getInt(cursor.getColumnIndex(CategoryDetailsEntry.COLUMN_CATEGORY_ID)));
            screenData.setDisplayTitle(cursor.getString(cursor.getColumnIndex(CategoryDetailsEntry.COLUMN_DISPLAY_TITLE)));
            screenData.setCategoryName(cursor.getString(cursor.getColumnIndex(CategoryDetailsEntry.COLUMN_CATEGORY_NAME)));
            screenData.setDescription(cursor.getString(cursor.getColumnIndex(CategoryDetailsEntry.COLUMN_DESCRIPTION)));
            screenData.setImplementation(cursor.getString(cursor.getColumnIndex(CategoryDetailsEntry.COLUMN_IMPLEMENTATION)));
            screenData.setImageName(cursor.getString(cursor.getColumnIndex(CategoryDetailsEntry.COLUMN_IMAGE_NAME)));

            screenDataList.add(screenData);
            cursor.moveToNext();
        }
    }
}

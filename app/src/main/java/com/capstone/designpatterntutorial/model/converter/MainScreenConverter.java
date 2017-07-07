package com.capstone.designpatterntutorial.model.converter;


import android.database.Cursor;

import com.capstone.designpatterntutorial.database.DesignPatternContract.CategoryEntry;
import com.capstone.designpatterntutorial.database.DesignPatternContract.PatternEntry;
import com.capstone.designpatterntutorial.model.mainScreen.Category;
import com.capstone.designpatterntutorial.model.mainScreen.MainScreenData;
import com.capstone.designpatterntutorial.model.mainScreen.Pattern;

import java.util.ArrayList;


/**
 * Created by gubbave on 4/27/2017.
 */

public class MainScreenConverter {

    //Convert data from CategoryList Table to model
    public static MainScreenData convertCategoryListEntry(Cursor cursor) {

        if (cursor == null || cursor.getCount() <= 0) {
            return null;
        }

        MainScreenData mainScreenData = new MainScreenData();
        ArrayList<Category> tabList = new ArrayList<>();

        cursor.moveToFirst();

        for (int pos = 0; pos < cursor.getCount(); pos++) {
            Category category = new Category();
            ArrayList<Pattern> patternList = new ArrayList<>();

            category.setId(cursor.getInt(cursor.getColumnIndex(CategoryEntry.COLUMN_ID)));
            category.setName(cursor.getString(cursor.getColumnIndex(CategoryEntry.COLUMN_NAME)));
            category.setDescription(cursor.getString(cursor.getColumnIndex(CategoryEntry.COLUMN_DESCRIPTION)));
            category.setPatternList(patternList);

            tabList.add(category);
            cursor.moveToNext();
        }

        mainScreenData.setCategoryList(tabList);
        return mainScreenData;
    }

    //Convert data from CategoryDetails Table to model
    public static void convertCategoryDetailsEntry(Cursor cursor, ArrayList<Pattern> patternList) {

        if (cursor == null || cursor.getCount() <= 0) {
            return;
        }

        cursor.moveToFirst();
        for (int pos = 0; pos < cursor.getCount(); pos++) {
            Pattern pattern = new Pattern();

            pattern.setId(cursor.getInt(cursor.getColumnIndex(PatternEntry.COLUMN_ID)));
            pattern.setCategoryId(cursor.getInt(cursor.getColumnIndex(PatternEntry.COLUMN_CATEGORY_ID)));
            pattern.setName(cursor.getString(cursor.getColumnIndex(PatternEntry.COLUMN_NAME)));
            pattern.setDescription(cursor.getString(cursor.getColumnIndex(PatternEntry.COLUMN_DESCRIPTION)));
            pattern.setIntent(cursor.getString(cursor.getColumnIndex(PatternEntry.COLUMN_INTENT)));
            pattern.setImageName(cursor.getString(cursor.getColumnIndex(PatternEntry.COLUMN_IMAGE_NAME)));

            patternList.add(pattern);
            cursor.moveToNext();
        }
    }
}

package com.capstone.designpatterntutorial.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by gubbave on 4/17/2017.
 */

public class DesignPatternContract {

    public static final String CONTENT_AUTHORITY = "com.capstone.designpatterntutorial";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_CATEGORYLIST = "categoryList";
    public static final String PATH_CATEGORYDETAILS = "categoryDetails";


    /* Inner class that defines the table contents of the category_list table */
    public static final class CategoryListEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_CATEGORYLIST)
                .build();

        public static final String TABLE_NAME = "category_list";

        public static final String COLUMN_CATEGORY_ID = "CategoryId";
        public static final String COLUMN_DISPLAY_TITLE = "DisplayTitle";
        public static final String COLUMN_CATEGORY_NAME = "CategoryName";
        public static final String COLUMN_DESCRIPTION = "Description";

        public static Uri buildCategoryListUri(long date) {
            return CONTENT_URI;
        }
    }

    /* Inner class that defines the table contents of the category_list table */
    public static final class CategoryDetailsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_CATEGORYDETAILS)
                .build();

        public static final String TABLE_NAME = "category_details";

        public static final String COLUMN_CATEGORY_ID = "CategoryId";
        public static final String COLUMN_DISPLAY_TITLE = "DisplayTitle";
        public static final String COLUMN_CATEGORY_NAME = "CategoryName";
        public static final String COLUMN_DESCRIPTION = "Description";
        public static final String COLUMN_IMPLEMENTATION = "Implementation";
        public static final String COLUMN_IMAGE_NAME = "ImageName";

        public static Uri buildCategoryListUri(long date) {
            return CONTENT_URI;
        }
    }
}
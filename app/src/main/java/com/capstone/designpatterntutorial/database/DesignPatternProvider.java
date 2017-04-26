package com.capstone.designpatterntutorial.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by gubbave on 4/17/2017.
 */

public class DesignPatternProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static final int CATEGORY_LIST = 100;
    public static final int CATEGORY_DETAILS = 101;

    private DesignPatternDbHelper mOpenHelper;


    @Override
    public boolean onCreate() {
        mOpenHelper = new DesignPatternDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor;

        switch (sUriMatcher.match(uri)) {
            case CATEGORY_LIST:
                cursor = mOpenHelper.getReadableDatabase().query(
                        DesignPatternContract.CategoryListEntry.TABLE_NAME,
                        projection,
                        null,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case CATEGORY_DETAILS:
                cursor = mOpenHelper.getReadableDatabase().query(
                        DesignPatternContract.CategoryDetailsEntry.TABLE_NAME,
                        projection,
                        null,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new RuntimeException("We are not implementing getType in Design Pattern Tutorial.");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        throw new RuntimeException("We are not implementing insert in Design Pattern Tutorial.");
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new RuntimeException("We are not implementing delete in Design Pattern Tutorial.");
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DesignPatternContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, DesignPatternContract.PATH_CATEGORYLIST, CATEGORY_LIST);
        matcher.addURI(authority, DesignPatternContract.PATH_CATEGORYDETAILS, CATEGORY_DETAILS);

        return matcher;
    }
}

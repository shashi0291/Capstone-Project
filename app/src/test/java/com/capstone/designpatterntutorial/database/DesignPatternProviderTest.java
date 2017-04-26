package com.capstone.designpatterntutorial.database;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.RemoteException;

import com.capstone.designpatterntutorial.BuildConfig;
import com.capstone.designpatterntutorial.RobolectricGradleTestRunner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowContentResolver;

/**
 * Created by gubbave on 4/17/2017.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class DesignPatternProviderTest {

    private Context mContext;
    private ContentResolver mContentResolver;
    private DesignPatternProvider mProvider;

    private ShadowContentResolver mShadowContentResolver;

    @Before
    public void setup() {
        mProvider = new DesignPatternProvider();
        mContext = ShadowApplication.getInstance().getApplicationContext();
        mContentResolver = mContext.getContentResolver();
        mShadowContentResolver = Shadows.shadowOf(mContentResolver);
        mProvider.onCreate();
        ShadowContentResolver.registerProviderInternal(DesignPatternContract.CONTENT_AUTHORITY,  mProvider);
    }

    @Test
    public void check_database_query_from_customer_list_table() {
        ContentProviderClient client = mShadowContentResolver.acquireContentProviderClient(DesignPatternContract.CONTENT_AUTHORITY);

        Cursor cursor = null;
        try {
            cursor = client.query(DesignPatternContract.CategoryListEntry.CONTENT_URI,
                    null,
                    null,
                    null,
                    null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Assert.assertNotEquals(cursor, null);
    }
}

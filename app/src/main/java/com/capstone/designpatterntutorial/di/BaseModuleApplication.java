package com.capstone.designpatterntutorial.di;

import android.app.Application;
import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by venugopalraog on 4/27/17.
 */
@Module
public class BaseModuleApplication {

    Application mApplication;

    public BaseModuleApplication(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
        // Application reference must come from AppModule.class
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    ContentResolver providesContentResolver(Application application) {
        return application.getContentResolver();
    }
}
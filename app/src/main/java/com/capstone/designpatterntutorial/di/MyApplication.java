package com.capstone.designpatterntutorial.di;

import android.app.Application;

/**
 * Created by venugopalraog on 4/27/17.
 */

public class MyApplication extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                        .baseModuleApplication(new BaseModuleApplication(this))
                        .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

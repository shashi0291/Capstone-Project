package com.capstone.designpatterntutorial.di;

import com.capstone.designpatterntutorial.views.activity.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by venugopalraog on 4/27/17.
 */

@Singleton
@Component(modules={BaseModuleApplication.class})
public interface AppComponent {

    void inject(HomeActivity activity);

}

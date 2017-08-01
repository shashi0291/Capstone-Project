package com.capstone.designpatterntutorial.model.events;

import com.capstone.designpatterntutorial.model.mainscreen.MainScreenData;

/**
 * Created by gubbave on 4/27/2017.
 */

public class MainScreenEvent {

    private MainScreenData mainScreenData;

    public MainScreenEvent(MainScreenData mainScreenData) {
        this.mainScreenData = mainScreenData;
    }

    public MainScreenData getMainScreenData() {
        return mainScreenData;
    }

    public void setMainScreenData(MainScreenData mainScreenData) {
        this.mainScreenData = mainScreenData;
    }
}

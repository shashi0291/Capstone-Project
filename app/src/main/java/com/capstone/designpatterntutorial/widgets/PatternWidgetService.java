package com.capstone.designpatterntutorial.widgets;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by gubbave on 12/29/2016.
 */

public class PatternWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new PatternWidgetViewsFactory(this.getApplicationContext(), intent);
    }

}

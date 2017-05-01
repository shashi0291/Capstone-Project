package com.capstone.designpatterntutorial.converter;

import android.database.Cursor;
import android.os.Parcelable;

/**
 * Created by gubbave on 4/27/2017.
 */

public interface Converter {
   <R extends Parcelable> R convert(Cursor cursor);
}

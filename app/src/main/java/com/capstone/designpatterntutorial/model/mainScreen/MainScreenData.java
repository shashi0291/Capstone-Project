package com.capstone.designpatterntutorial.model.mainScreen;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;

/**
 * Created by venugopalraog on 4/24/17.
 */

public class MainScreenData implements Parcelable {

    private ArrayList<Category> categoryList;

    public MainScreenData() {   }

    protected MainScreenData(Parcel in) {
        categoryList = in.createTypedArrayList(Category.CREATOR);
    }

    public static final Creator<MainScreenData> CREATOR = new Creator<MainScreenData>() {
        @Override
        public MainScreenData createFromParcel(Parcel in) {
            return new MainScreenData(in);
        }

        @Override
        public MainScreenData[] newArray(int size) {
            return new MainScreenData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(categoryList);
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MainScreenData that = (MainScreenData) o;

        return new EqualsBuilder()
                .append(categoryList, that.categoryList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(categoryList)
                .toHashCode();
    }
}

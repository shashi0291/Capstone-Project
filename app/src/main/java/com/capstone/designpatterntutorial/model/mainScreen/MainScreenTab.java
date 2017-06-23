package com.capstone.designpatterntutorial.model.mainScreen;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;

/**
 * Created by venugopalraog on 4/24/17.
 */

public class MainScreenTab implements Parcelable {

    private String categoryName;
    private int categoryId;
    private String contentDescription;
    private ArrayList<ScreenData> screenDataList;


    public MainScreenTab()  {   }


    protected MainScreenTab(Parcel in) {
        categoryId = in.readInt();
        categoryName = in.readString();
        contentDescription = in.readString();
        screenDataList = in.createTypedArrayList(ScreenData.CREATOR);
    }

    public static final Creator<MainScreenTab> CREATOR = new Creator<MainScreenTab>() {
        @Override
        public MainScreenTab createFromParcel(Parcel in) {
            return new MainScreenTab(in);
        }

        @Override
        public MainScreenTab[] newArray(int size) {
            return new MainScreenTab[size];
        }
    };

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public ArrayList<ScreenData> getScreenDataList() {
        return screenDataList;
    }

    public void setScreenDataList(ArrayList<ScreenData> screenDataList) {
        this.screenDataList = screenDataList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(categoryId);
        dest.writeString(categoryName);
        dest.writeString(contentDescription);
        dest.writeTypedList(screenDataList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MainScreenTab that = (MainScreenTab) o;

        return new EqualsBuilder()
                .append(categoryId, that.categoryId)
                .append(categoryName, that.categoryName)
                .append(contentDescription, that.contentDescription)
                .append(screenDataList, that.screenDataList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(categoryId)
                .append(categoryName)
                .append(contentDescription)
                .append(screenDataList)
                .toHashCode();
    }
}

package com.capstone.designpatterntutorial.model.mainScreen;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * Created by venugopalraog on 4/24/17.
 */

public class MainScreenData implements Parcelable {

    private int categoryId;
    private String title;
    private String categoryName;
    private List<ScreenData> screenDataList;


    public MainScreenData() {   }

    protected MainScreenData(Parcel in) {
        categoryId = in.readInt();
        title = in.readString();
        categoryName = in.readString();
        screenDataList = in.createTypedArrayList(ScreenData.CREATOR);
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ScreenData> getScreenDataList() {
        return screenDataList;
    }

    public void setScreenDataList(List<ScreenData> screenDataList) {
        this.screenDataList = screenDataList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(categoryId);
        dest.writeString(title);
        dest.writeString(categoryName);
        dest.writeTypedList(screenDataList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MainScreenData that = (MainScreenData) o;

        return new EqualsBuilder()
                .append(categoryId, that.categoryId)
                .append(title, that.title)
                .append(categoryName, that.categoryName)
                .append(screenDataList, that.screenDataList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(categoryId)
                .append(title)
                .append(categoryName)
                .append(screenDataList)
                .toHashCode();
    }
}

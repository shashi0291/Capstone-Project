package com.capstone.designpatterntutorial.model.mainScreen;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Objects;

/**
 * Created by venugopalraog on 4/24/17.
 */

public class MainScreenTabDetail implements Parcelable {

    private String title;
    private List<ScreenData> screenDataList;

    protected MainScreenTabDetail(Parcel in) {
        title = in.readString();
        screenDataList = in.createTypedArrayList(ScreenData.CREATOR);
    }

    public static final Creator<MainScreenTabDetail> CREATOR = new Creator<MainScreenTabDetail>() {
        @Override
        public MainScreenTabDetail createFromParcel(Parcel in) {
            return new MainScreenTabDetail(in);
        }

        @Override
        public MainScreenTabDetail[] newArray(int size) {
            return new MainScreenTabDetail[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        dest.writeString(title);
        dest.writeTypedList(screenDataList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainScreenTabDetail that = (MainScreenTabDetail) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(screenDataList, that.screenDataList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, screenDataList);
    }
}

package com.capstone.designpatterntutorial.model.mainScreen;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Objects;

/**
 * Created by venugopalraog on 4/24/17.
 */

class MainScreenData implements Parcelable {

    private String title;
    private List<MainScreenTabDetail> mainScreenDetails;


    protected MainScreenData(Parcel in) {
        title = in.readString();
        mainScreenDetails = in.createTypedArrayList(MainScreenTabDetail.CREATOR);
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MainScreenTabDetail> getMainScreenDetails() {
        return mainScreenDetails;
    }

    public void setMainScreenDetails(List<MainScreenTabDetail> mainScreenDetails) {
        this.mainScreenDetails = mainScreenDetails;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeTypedList(mainScreenDetails);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainScreenData that = (MainScreenData) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(mainScreenDetails, that.mainScreenDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, mainScreenDetails);
    }
}

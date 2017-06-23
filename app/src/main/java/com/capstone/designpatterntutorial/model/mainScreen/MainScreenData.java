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

    private String title;
    private String Description;
    private ArrayList<MainScreenTab> mainScreenTabList;


    public MainScreenData() {   }

    protected MainScreenData(Parcel in) {
        title = in.readString();
        Description = in.readString();
        mainScreenTabList = in.createTypedArrayList(MainScreenTab.CREATOR);
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public ArrayList<MainScreenTab> getMainScreenTabList() {
        return mainScreenTabList;
    }

    public void setMainScreenTabList(ArrayList<MainScreenTab> mainScreenTabList) {
        this.mainScreenTabList = mainScreenTabList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(Description);
        dest.writeTypedList(mainScreenTabList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MainScreenData that = (MainScreenData) o;

        return new EqualsBuilder()
                .append(title, that.title)
                .append(Description, that.Description)
                .append(mainScreenTabList, that.mainScreenTabList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(title)
                .append(Description)
                .append(mainScreenTabList)
                .toHashCode();
    }
}

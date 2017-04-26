package com.capstone.designpatterntutorial.model.mainScreen;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * Created by venugopalraog on 4/24/17.
 */

class ScreenData implements Parcelable {

    private String title;
    private String message;

    protected ScreenData(Parcel in) {
        title = in.readString();
        message = in.readString();
    }

    public static final Creator<ScreenData> CREATOR = new Creator<ScreenData>() {
        @Override
        public ScreenData createFromParcel(Parcel in) {
            return new ScreenData(in);
        }

        @Override
        public ScreenData[] newArray(int size) {
            return new ScreenData[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreenData that = (ScreenData) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, message);
    }
}

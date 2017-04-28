package com.capstone.designpatterntutorial.model.mainScreen;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by venugopalraog on 4/24/17.
 */

public class ScreenData implements Parcelable {

    private int categoryId;
    private String categoryName;
    private String displayTitle;
    private String description;
    private String implementation;
    private String imageName;

    public ScreenData() {   }

    protected ScreenData(Parcel in) {
        categoryId = in.readInt();
        categoryName = in.readString();
        displayTitle = in.readString();
        description = in.readString();
        implementation = in.readString();
        imageName = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(categoryId);
        dest.writeString(categoryName);
        dest.writeString(displayTitle);
        dest.writeString(description);
        dest.writeString(implementation);
        dest.writeString(imageName);
    }

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

    public String getDisplayTitle() {
        return displayTitle;
    }

    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImplementation() {
        return implementation;
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ScreenData that = (ScreenData) o;

        return new EqualsBuilder()
                .append(categoryId, that.categoryId)
                .append(categoryName, that.categoryName)
                .append(displayTitle, that.displayTitle)
                .append(description, that.description)
                .append(implementation, that.implementation)
                .append(imageName, that.imageName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(categoryId)
                .append(categoryName)
                .append(displayTitle)
                .append(description)
                .append(implementation)
                .append(imageName)
                .toHashCode();
    }
}

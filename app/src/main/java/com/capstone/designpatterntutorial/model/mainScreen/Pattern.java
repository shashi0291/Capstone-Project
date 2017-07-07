package com.capstone.designpatterntutorial.model.mainScreen;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by venugopalraog on 4/24/17.
 */

public class Pattern implements Parcelable {

    private int id;
    private int categoryId;
    private String name;
    private String intent;
    private String description;
    private String imageName;

    public Pattern() {
    }

    protected Pattern(Parcel in) {
        id = in.readInt();
        categoryId = in.readInt();
        name = in.readString();
        intent = in.readString();
        description = in.readString();
        imageName = in.readString();
    }

    public static final Creator<Pattern> CREATOR = new Creator<Pattern>() {
        @Override
        public Pattern createFromParcel(Parcel in) {
            return new Pattern(in);
        }

        @Override
        public Pattern[] newArray(int size) {
            return new Pattern[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(categoryId);
        dest.writeString(name);
        dest.writeString(intent);
        dest.writeString(description);
        dest.writeString(imageName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Pattern pattern = (Pattern) o;

        return new EqualsBuilder()
                .append(id, pattern.id)
                .append(categoryId, pattern.categoryId)
                .append(name, pattern.name)
                .append(intent, pattern.intent)
                .append(description, pattern.description)
                .append(imageName, pattern.imageName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(categoryId)
                .append(name)
                .append(intent)
                .append(description)
                .append(imageName)
                .toHashCode();
    }
}

package net.dragora.mttweather.pojo.search_city;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;


public class Region implements Parcelable {

    public static final Parcelable.Creator<Region> CREATOR = new Parcelable.Creator<Region>() {
        public Region createFromParcel(Parcel in) {
            return new Region(in);
        }

        public Region[] newArray(int size) {
            return new Region[size];
        }
    };
    private static final String FIELD_VALUE = "value";
    @SerializedName(FIELD_VALUE)
    private String mValue;

    public Region() {

    }

    public Region(Parcel in) {
        mValue = in.readString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        return mValue != null ? mValue.equals(region.mValue) : region.mValue == null;

    }

    @Override
    public int hashCode() {
        return mValue != null ? mValue.hashCode() : 0;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mValue);
    }

    @Override
    public String toString() {
        return "value = " + mValue;
    }


}
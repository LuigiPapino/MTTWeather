package net.dragora.mttweather.pojo.search_city;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;


public class AreaName implements Parcelable {

    public static final Parcelable.Creator<AreaName> CREATOR = new Parcelable.Creator<AreaName>() {
        public AreaName createFromParcel(Parcel in) {
            return new AreaName(in);
        }

        public AreaName[] newArray(int size) {
            return new AreaName[size];
        }
    };
    private static final String FIELD_VALUE = "value";
    @SerializedName(FIELD_VALUE)
    private String mValue;

    public AreaName() {

    }

    public AreaName(Parcel in) {
        mValue = in.readString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AreaName areaName = (AreaName) o;

        return mValue != null ? mValue.equals(areaName.mValue) : areaName.mValue == null;

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
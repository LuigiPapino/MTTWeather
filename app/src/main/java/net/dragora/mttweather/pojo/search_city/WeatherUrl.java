package net.dragora.mttweather.pojo.search_city;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;


public class WeatherUrl implements Parcelable {

    public static final Parcelable.Creator<WeatherUrl> CREATOR = new Parcelable.Creator<WeatherUrl>() {
        public WeatherUrl createFromParcel(Parcel in) {
            return new WeatherUrl(in);
        }

        public WeatherUrl[] newArray(int size) {
            return new WeatherUrl[size];
        }
    };
    private static final String FIELD_VALUE = "value";
    @SerializedName(FIELD_VALUE)
    private String mValue;

    public WeatherUrl() {

    }

    public WeatherUrl(Parcel in) {
        mValue = in.readString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherUrl that = (WeatherUrl) o;

        return mValue != null ? mValue.equals(that.mValue) : that.mValue == null;

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
package net.dragora.mttweather.pojo.weather;

import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import android.os.Parcel;


public class WeatherIconUrl implements Parcelable {

    public static final Creator<WeatherIconUrl> CREATOR = new Creator<WeatherIconUrl>() {
        public WeatherIconUrl createFromParcel(Parcel in) {
            return new WeatherIconUrl(in);
        }

        public WeatherIconUrl[] newArray(int size) {
            return new WeatherIconUrl[size];
        }
    };
    private static final String FIELD_VALUE = "value";
    @SerializedName(FIELD_VALUE)
    private String mValue;

    public WeatherIconUrl() {

    }

    public WeatherIconUrl(Parcel in) {
        mValue = in.readString();
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
package net.dragora.mttweather.pojo.weather;

import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import android.os.Parcel;


public class WeatherDesc implements Parcelable {

    public static final Creator<WeatherDesc> CREATOR = new Creator<WeatherDesc>() {
        public WeatherDesc createFromParcel(Parcel in) {
            return new WeatherDesc(in);
        }

        public WeatherDesc[] newArray(int size) {
            return new WeatherDesc[size];
        }
    };
    private static final String FIELD_VALUE = "value";
    @SerializedName(FIELD_VALUE)
    private String mValue;

    public WeatherDesc() {

    }

    public WeatherDesc(Parcel in) {
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
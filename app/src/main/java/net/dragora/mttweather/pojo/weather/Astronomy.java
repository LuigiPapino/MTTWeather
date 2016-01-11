package net.dragora.mttweather.pojo.weather;

import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import android.os.Parcel;


public class Astronomy implements Parcelable {

    public static final Creator<Astronomy> CREATOR = new Creator<Astronomy>() {
        public Astronomy createFromParcel(Parcel in) {
            return new Astronomy(in);
        }

        public Astronomy[] newArray(int size) {
            return new Astronomy[size];
        }
    };
    private static final String FIELD_SUNSET = "sunset";
    private static final String FIELD_MOONSET = "moonset";
    private static final String FIELD_MOONRISE = "moonrise";
    private static final String FIELD_SUNRISE = "sunrise";
    @SerializedName(FIELD_SUNSET)
    private String mSunset;
    @SerializedName(FIELD_MOONSET)
    private String mMoonset;
    @SerializedName(FIELD_MOONRISE)
    private String mMoonrise;
    @SerializedName(FIELD_SUNRISE)
    private String mSunrise;

    public Astronomy() {

    }

    public Astronomy(Parcel in) {
        mSunset = in.readString();
        mMoonset = in.readString();
        mMoonrise = in.readString();
        mSunrise = in.readString();
    }

    public String getSunset() {
        return mSunset;
    }

    public void setSunset(String sunset) {
        mSunset = sunset;
    }

    public String getMoonset() {
        return mMoonset;
    }

    public void setMoonset(String moonset) {
        mMoonset = moonset;
    }

    public String getMoonrise() {
        return mMoonrise;
    }

    public void setMoonrise(String moonrise) {
        mMoonrise = moonrise;
    }

    public String getSunrise() {
        return mSunrise;
    }

    public void setSunrise(String sunrise) {
        mSunrise = sunrise;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mSunset);
        dest.writeString(mMoonset);
        dest.writeString(mMoonrise);
        dest.writeString(mSunrise);
    }

    @Override
    public String toString() {
        return "sunset = " + mSunset + ", moonset = " + mMoonset + ", moonrise = " + mMoonrise + ", sunrise = " + mSunrise;
    }


}
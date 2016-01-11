package net.dragora.mttweather.pojo.weather;

import java.util.ArrayList;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import android.os.Parcel;


public class Weather implements Parcelable {

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };
    private static final String FIELD_MAXTEMP_C = "maxtempC";
    private static final String FIELD_HOURLY = "hourly";
    private static final String FIELD_MAXTEMP_F = "maxtempF";
    private static final String FIELD_MINTEMP_C = "mintempC";
    private static final String FIELD_DATE = "date";
    private static final String FIELD_ASTRONOMY = "astronomy";
    private static final String FIELD_MINTEMP_F = "mintempF";
    private static final String FIELD_UV_INDEX = "uvIndex";
    @SerializedName(FIELD_MAXTEMP_C)
    private int mMaxtempC;
    @SerializedName(FIELD_HOURLY)
    private List<Hourly> mHourlies;
    @SerializedName(FIELD_MAXTEMP_F)
    private int mMaxtempF;
    @SerializedName(FIELD_MINTEMP_C)
    private int mMintempC;
    @SerializedName(FIELD_DATE)
    private String mDate;
    @SerializedName(FIELD_ASTRONOMY)
    private List<Astronomy> mAstronomies;
    @SerializedName(FIELD_MINTEMP_F)
    private int mMintempF;
    @SerializedName(FIELD_UV_INDEX)
    private int mUvIndex;

    public Weather() {

    }

    public Weather(Parcel in) {
        mMaxtempC = in.readInt();
        mHourlies = new ArrayList<Hourly>();
        in.readTypedList(mHourlies, Hourly.CREATOR);
        mMaxtempF = in.readInt();
        mMintempC = in.readInt();
        mDate = in.readString();
        mAstronomies = new ArrayList<Astronomy>();
        in.readTypedList(mAstronomies, Astronomy.CREATOR);
        mMintempF = in.readInt();
        mUvIndex = in.readInt();
    }

    public int getMaxtempC() {
        return mMaxtempC;
    }

    public void setMaxtempC(int maxtempC) {
        mMaxtempC = maxtempC;
    }

    public List<Hourly> getHourlies() {
        return mHourlies;
    }

    public void setHourlies(List<Hourly> hourlies) {
        mHourlies = hourlies;
    }

    public int getMaxtempF() {
        return mMaxtempF;
    }

    public void setMaxtempF(int maxtempF) {
        mMaxtempF = maxtempF;
    }

    public int getMintempC() {
        return mMintempC;
    }

    public void setMintempC(int mintempC) {
        mMintempC = mintempC;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public List<Astronomy> getAstronomies() {
        return mAstronomies;
    }

    public void setAstronomies(List<Astronomy> astronomies) {
        mAstronomies = astronomies;
    }

    public int getMintempF() {
        return mMintempF;
    }

    public void setMintempF(int mintempF) {
        mMintempF = mintempF;
    }

    public int getUvIndex() {
        return mUvIndex;
    }

    public void setUvIndex(int uvIndex) {
        mUvIndex = uvIndex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mMaxtempC);
        dest.writeTypedList(mHourlies);
        dest.writeInt(mMaxtempF);
        dest.writeInt(mMintempC);
        dest.writeString(mDate);
        dest.writeTypedList(mAstronomies);
        dest.writeInt(mMintempF);
        dest.writeInt(mUvIndex);
    }

    @Override
    public String toString() {
        return "maxtempC = " + mMaxtempC + ", hourlies = " + mHourlies + ", maxtempF = " + mMaxtempF + ", mintempC = " + mMintempC + ", date = " + mDate + ", astronomies = " + mAstronomies + ", mintempF = " + mMintempF + ", uvIndex = " + mUvIndex;
    }


}
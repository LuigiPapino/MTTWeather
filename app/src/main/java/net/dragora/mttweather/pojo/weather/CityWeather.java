package net.dragora.mttweather.pojo.weather;

import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import android.os.Parcel;


public class CityWeather implements Parcelable {

    public static final Creator<CityWeather> CREATOR = new Creator<CityWeather>() {
        public CityWeather createFromParcel(Parcel in) {
            return new CityWeather(in);
        }

        public CityWeather[] newArray(int size) {
            return new CityWeather[size];
        }
    };
    private static final String FIELD_DATA = "data";
    @SerializedName(FIELD_DATA)
    private Datum mDatum;

    public CityWeather() {

    }

    public CityWeather(Parcel in) {
        mDatum = in.readParcelable(Datum.class.getClassLoader());
    }

    public Datum getDatum() {
        return mDatum;
    }

    public void setDatum(Datum datum) {
        mDatum = datum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mDatum, flags);
    }

    @Override
    public String toString() {
        return "datum = " + mDatum;
    }


    public String getId() {
        return String.format("%s,%s", getDatum().getNearestAreas().get(0).getLatitude(), getDatum().getNearestAreas().get(0).getLongitude());
    }
}
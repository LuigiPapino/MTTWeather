package net.dragora.mttweather.pojo.weather;

import java.util.ArrayList;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import android.os.Parcel;


public class NearestArea implements Parcelable {

    public static final Creator<NearestArea> CREATOR = new Creator<NearestArea>() {
        public NearestArea createFromParcel(Parcel in) {
            return new NearestArea(in);
        }

        public NearestArea[] newArray(int size) {
            return new NearestArea[size];
        }
    };
    private static final String FIELD_AREA_NAME = "areaName";
    private static final String FIELD_COUNTRY = "country";
    private static final String FIELD_POPULATION = "population";
    private static final String FIELD_REGION = "region";
    private static final String FIELD_LONGITUDE = "longitude";
    private static final String FIELD_LATITUDE = "latitude";
    private static final String FIELD_WEATHER_URL = "weatherUrl";
    @SerializedName(FIELD_AREA_NAME)
    private List<AreaName> mAreaNames;
    @SerializedName(FIELD_COUNTRY)
    private List<Country> mCountries;
    @SerializedName(FIELD_POPULATION)
    private int mPopulation;
    @SerializedName(FIELD_REGION)
    private List<Region> mRegions;
    @SerializedName(FIELD_LONGITUDE)
    private String mLongitude;
    @SerializedName(FIELD_LATITUDE)
    private String mLatitude;
    @SerializedName(FIELD_WEATHER_URL)
    private List<WeatherUrl> mWeatherUrls;

    public NearestArea() {

    }

    public NearestArea(Parcel in) {
        mAreaNames = new ArrayList<AreaName>();
        in.readTypedList(mAreaNames, AreaName.CREATOR);
        mCountries = new ArrayList<Country>();
        in.readTypedList(mCountries, Country.CREATOR);
        mPopulation = in.readInt();
        mRegions = new ArrayList<Region>();
        in.readTypedList(mRegions, Region.CREATOR);
        mLongitude = in.readString();
        mLatitude = in.readString();
        mWeatherUrls = new ArrayList<WeatherUrl>();
        in.readTypedList(mWeatherUrls, WeatherUrl.CREATOR);
    }

    public List<AreaName> getAreaNames() {
        return mAreaNames;
    }

    public void setAreaNames(List<AreaName> areaNames) {
        mAreaNames = areaNames;
    }

    public List<Country> getCountries() {
        return mCountries;
    }

    public void setCountries(List<Country> countries) {
        mCountries = countries;
    }

    public int getPopulation() {
        return mPopulation;
    }

    public void setPopulation(int population) {
        mPopulation = population;
    }

    public List<Region> getRegions() {
        return mRegions;
    }

    public void setRegions(List<Region> regions) {
        mRegions = regions;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String longitude) {
        mLongitude = longitude;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String latitude) {
        mLatitude = latitude;
    }

    public List<WeatherUrl> getWeatherUrls() {
        return mWeatherUrls;
    }

    public void setWeatherUrls(List<WeatherUrl> weatherUrls) {
        mWeatherUrls = weatherUrls;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mAreaNames);
        dest.writeTypedList(mCountries);
        dest.writeInt(mPopulation);
        dest.writeTypedList(mRegions);
        dest.writeString(mLongitude);
        dest.writeString(mLatitude);
        dest.writeTypedList(mWeatherUrls);
    }

    @Override
    public String toString() {
        return "areaNames = " + mAreaNames + ", countries = " + mCountries + ", population = " + mPopulation + ", regions = " + mRegions + ", longitude = " + mLongitude + ", latitude = " + mLatitude + ", weatherUrls = " + mWeatherUrls;
    }


}
package net.dragora.mttweather.pojo.search_city;

import java.util.ArrayList;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import android.os.Parcel;
import android.text.TextUtils;


public class Result implements Parcelable {

    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        public Result[] newArray(int size) {
            return new Result[size];
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
    private double mLatitude;
    @SerializedName(FIELD_WEATHER_URL)
    private List<WeatherUrl> mWeatherUrls;

    public Result() {

    }

    public Result(Parcel in) {
        mAreaNames = new ArrayList<AreaName>();
        in.readTypedList(mAreaNames, AreaName.CREATOR);
        mCountries = new ArrayList<Country>();
        in.readTypedList(mCountries, Country.CREATOR);
        mPopulation = in.readInt();
        mRegions = new ArrayList<Region>();
        in.readTypedList(mRegions, Region.CREATOR);
        mLongitude = in.readString();
        mLatitude = in.readDouble();
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

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
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
        dest.writeDouble(mLatitude);
        dest.writeTypedList(mWeatherUrls);
    }

    @Override
    public String toString() {
        return "areaNames = " + mAreaNames + ", countries = " + mCountries + ", population = " + mPopulation + ", regions = " + mRegions + ", longitude = " + mLongitude + ", latitude = " + mLatitude + ", weatherUrls = " + mWeatherUrls;
    }


    public String getFirstAreaName() {
        String result = "";
        if (getAreaNames() != null && !getAreaNames().isEmpty())
            result = getAreaNames().get(0).getValue();

        return TextUtils.isEmpty(result) ? "" : result;
    }

    public String getFirstCountry() {
        String result = "";
        if (getCountries() != null && !getCountries().isEmpty())
            result = getCountries().get(0).getValue();

        return TextUtils.isEmpty(result) ? "" : result;
    }

    public String getFirstRegion() {
        String result = "";
        if (getRegions() != null && !getRegions().isEmpty())
            result = getRegions().get(0).getValue();

        return TextUtils.isEmpty(result) ? "" : result;
    }

}
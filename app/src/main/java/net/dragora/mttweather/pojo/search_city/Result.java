package net.dragora.mttweather.pojo.search_city;

import java.util.ArrayList;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.text.TextUtils;


public class Result implements Parcelable, Comparable<Result> {

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
    private String mLatitude;
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
        mLatitude = in.readString();
        mWeatherUrls = new ArrayList<WeatherUrl>();
        in.readTypedList(mWeatherUrls, WeatherUrl.CREATOR);
    }

    public String getWeatherId() {
        return String.format("%s,%s", getLatitude(), getLongitude());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        if (mPopulation != result.mPopulation) return false;
        if (mLatitude != null ? !mLatitude.equals(result.mLatitude) : result.mLatitude != null)
            return false;
        if (mAreaNames != null ? !mAreaNames.equals(result.mAreaNames) : result.mAreaNames != null)
            return false;
        if (mCountries != null ? !mCountries.equals(result.mCountries) : result.mCountries != null)
            return false;
        if (mRegions != null ? !mRegions.equals(result.mRegions) : result.mRegions != null)
            return false;
        if (mLongitude != null ? !mLongitude.equals(result.mLongitude) : result.mLongitude != null)
            return false;
        return mWeatherUrls != null ? mWeatherUrls.equals(result.mWeatherUrls) : result.mWeatherUrls == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = mAreaNames != null ? mAreaNames.hashCode() : 0;
        result = 31 * result + (mCountries != null ? mCountries.hashCode() : 0);
        result = 31 * result + mPopulation;
        result = 31 * result + (mRegions != null ? mRegions.hashCode() : 0);
        result = 31 * result + (mLongitude != null ? mLongitude.hashCode() : 0);
        result = 31 * result + (mLatitude != null ? mLatitude.hashCode() : 0);
        result = 31 * result + (mWeatherUrls != null ? mWeatherUrls.hashCode() : 0);
        return result;
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


    @NonNull
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

    @Override
    public int compareTo(Result another) {

        return getFirstAreaName().compareToIgnoreCase(another.getFirstAreaName());
    }
}
package net.dragora.mttweather.pojo.weather;

import java.util.ArrayList;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import android.os.Parcel;


public class CurrentCondition implements Parcelable {

    public static final Creator<CurrentCondition> CREATOR = new Creator<CurrentCondition>() {
        public CurrentCondition createFromParcel(Parcel in) {
            return new CurrentCondition(in);
        }

        public CurrentCondition[] newArray(int size) {
            return new CurrentCondition[size];
        }
    };
    private static final String FIELD_WINDSPEED_KMPH = "windspeedKmph";
    private static final String FIELD_TEMP_F = "temp_F";
    private static final String FIELD_FEELS_LIKE_F = "FeelsLikeF";
    private static final String FIELD_TEMP_C = "temp_C";
    private static final String FIELD_PRECIP_MM = "precipMM";
    private static final String FIELD_FEELS_LIKE_C = "FeelsLikeC";
    private static final String FIELD_WINDSPEED_MILES = "windspeedMiles";
    private static final String FIELD_WEATHER_DESC = "weatherDesc";
    private static final String FIELD_WINDDIR16_POINT = "winddir16Point";
    private static final String FIELD_WEATHER_ICON_URL = "weatherIconUrl";
    private static final String FIELD_WINDDIR_DEGREE = "winddirDegree";
    private static final String FIELD_PRESSURE = "pressure";
    private static final String FIELD_VISIBILITY = "visibility";
    private static final String FIELD_HUMIDITY = "humidity";
    private static final String FIELD_CLOUDCOVER = "cloudcover";
    private static final String FIELD_OBSERVATION_TIME = "observation_time";
    private static final String FIELD_WEATHER_CODE = "weatherCode";
    @SerializedName(FIELD_WINDSPEED_KMPH)
    private int mWindspeedKmph;
    @SerializedName(FIELD_TEMP_F)
    private int mTempF;
    @SerializedName(FIELD_FEELS_LIKE_F)
    private int mFeelsLikeF;
    @SerializedName(FIELD_TEMP_C)
    private int mTempC;
    @SerializedName(FIELD_PRECIP_MM)
    private double mPrecipMM;
    @SerializedName(FIELD_FEELS_LIKE_C)
    private int mFeelsLikeC;
    @SerializedName(FIELD_WINDSPEED_MILES)
    private int mWindspeedMile;
    @SerializedName(FIELD_WEATHER_DESC)
    private List<WeatherDesc> mWeatherDescs;
    @SerializedName(FIELD_WINDDIR16_POINT)
    private String mWinddir16Point;
    @SerializedName(FIELD_WEATHER_ICON_URL)
    private List<WeatherIconUrl> mWeatherIconUrls;
    @SerializedName(FIELD_WINDDIR_DEGREE)
    private int mWinddirDegree;
    @SerializedName(FIELD_PRESSURE)
    private int mPressure;
    @SerializedName(FIELD_VISIBILITY)
    private int mVisibility;
    @SerializedName(FIELD_HUMIDITY)
    private int mHumidity;
    @SerializedName(FIELD_CLOUDCOVER)
    private int mCloudcover;
    @SerializedName(FIELD_OBSERVATION_TIME)
    private String mObservationTime;
    @SerializedName(FIELD_WEATHER_CODE)
    private int mWeatherCode;

    public CurrentCondition() {

    }

    public CurrentCondition(Parcel in) {
        mWindspeedKmph = in.readInt();
        mTempF = in.readInt();
        mFeelsLikeF = in.readInt();
        mTempC = in.readInt();
        mPrecipMM = in.readDouble();
        mFeelsLikeC = in.readInt();
        mWindspeedMile = in.readInt();
        mWeatherDescs = new ArrayList<WeatherDesc>();
        in.readTypedList(mWeatherDescs, WeatherDesc.CREATOR);
        mWinddir16Point = in.readString();
        mWeatherIconUrls = new ArrayList<WeatherIconUrl>();
        in.readTypedList(mWeatherIconUrls, WeatherIconUrl.CREATOR);
        mWinddirDegree = in.readInt();
        mPressure = in.readInt();
        mVisibility = in.readInt();
        mHumidity = in.readInt();
        mCloudcover = in.readInt();
        mObservationTime = in.readString();
        mWeatherCode = in.readInt();
    }

    public int getWindspeedKmph() {
        return mWindspeedKmph;
    }

    public void setWindspeedKmph(int windspeedKmph) {
        mWindspeedKmph = windspeedKmph;
    }

    public int getTempF() {
        return mTempF;
    }

    public void setTempF(int tempF) {
        mTempF = tempF;
    }

    public int getFeelsLikeF() {
        return mFeelsLikeF;
    }

    public void setFeelsLikeF(int feelsLikeF) {
        mFeelsLikeF = feelsLikeF;
    }

    public int getTempC() {
        return mTempC;
    }

    public void setTempC(int tempC) {
        mTempC = tempC;
    }

    public double getPrecipMM() {
        return mPrecipMM;
    }

    public void setPrecipMM(double precipMM) {
        mPrecipMM = precipMM;
    }

    public int getFeelsLikeC() {
        return mFeelsLikeC;
    }

    public void setFeelsLikeC(int feelsLikeC) {
        mFeelsLikeC = feelsLikeC;
    }

    public int getWindspeedMile() {
        return mWindspeedMile;
    }

    public void setWindspeedMile(int windspeedMile) {
        mWindspeedMile = windspeedMile;
    }

    public List<WeatherDesc> getWeatherDescs() {
        return mWeatherDescs;
    }

    public void setWeatherDescs(List<WeatherDesc> weatherDescs) {
        mWeatherDescs = weatherDescs;
    }

    public String getWinddir16Point() {
        return mWinddir16Point;
    }

    public void setWinddir16Point(String winddir16Point) {
        mWinddir16Point = winddir16Point;
    }

    public List<WeatherIconUrl> getWeatherIconUrls() {
        return mWeatherIconUrls;
    }

    public void setWeatherIconUrls(List<WeatherIconUrl> weatherIconUrls) {
        mWeatherIconUrls = weatherIconUrls;
    }

    public int getWinddirDegree() {
        return mWinddirDegree;
    }

    public void setWinddirDegree(int winddirDegree) {
        mWinddirDegree = winddirDegree;
    }

    public int getPressure() {
        return mPressure;
    }

    public void setPressure(int pressure) {
        mPressure = pressure;
    }

    public int getVisibility() {
        return mVisibility;
    }

    public void setVisibility(int visibility) {
        mVisibility = visibility;
    }

    public int getHumidity() {
        return mHumidity;
    }

    public void setHumidity(int humidity) {
        mHumidity = humidity;
    }

    public int getCloudcover() {
        return mCloudcover;
    }

    public void setCloudcover(int cloudcover) {
        mCloudcover = cloudcover;
    }

    public String getObservationTime() {
        return mObservationTime;
    }

    public void setObservationTime(String observationTime) {
        mObservationTime = observationTime;
    }

    public int getWeatherCode() {
        return mWeatherCode;
    }

    public void setWeatherCode(int weatherCode) {
        mWeatherCode = weatherCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mWindspeedKmph);
        dest.writeInt(mTempF);
        dest.writeInt(mFeelsLikeF);
        dest.writeInt(mTempC);
        dest.writeDouble(mPrecipMM);
        dest.writeInt(mFeelsLikeC);
        dest.writeInt(mWindspeedMile);
        dest.writeTypedList(mWeatherDescs);
        dest.writeString(mWinddir16Point);
        dest.writeTypedList(mWeatherIconUrls);
        dest.writeInt(mWinddirDegree);
        dest.writeInt(mPressure);
        dest.writeInt(mVisibility);
        dest.writeInt(mHumidity);
        dest.writeInt(mCloudcover);
        dest.writeString(mObservationTime);
        dest.writeInt(mWeatherCode);
    }

    @Override
    public String toString() {
        return "windspeedKmph = " + mWindspeedKmph + ", tempF = " + mTempF + ", feelsLikeF = " + mFeelsLikeF + ", tempC = " + mTempC + ", precipMM = " + mPrecipMM + ", feelsLikeC = " + mFeelsLikeC + ", windspeedMile = " + mWindspeedMile + ", weatherDescs = " + mWeatherDescs + ", winddir16Point = " + mWinddir16Point + ", weatherIconUrls = " + mWeatherIconUrls + ", winddirDegree = " + mWinddirDegree + ", pressure = " + mPressure + ", visibility = " + mVisibility + ", humidity = " + mHumidity + ", cloudcover = " + mCloudcover + ", observationTime = " + mObservationTime + ", weatherCode = " + mWeatherCode;
    }


}
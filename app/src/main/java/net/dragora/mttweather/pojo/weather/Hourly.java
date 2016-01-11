package net.dragora.mttweather.pojo.weather;

import java.util.ArrayList;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import android.os.Parcel;


public class Hourly implements Parcelable {

    public static final Creator<Hourly> CREATOR = new Creator<Hourly>() {
        public Hourly createFromParcel(Parcel in) {
            return new Hourly(in);
        }

        public Hourly[] newArray(int size) {
            return new Hourly[size];
        }
    };
    private static final String FIELD_WINDSPEED_KMPH = "windspeedKmph";
    private static final String FIELD_TEMP_F = "tempF";
    private static final String FIELD_TEMP_C = "tempC";
    private static final String FIELD_FEELS_LIKE_F = "FeelsLikeF";
    private static final String FIELD_PRECIP_MM = "precipMM";
    private static final String FIELD_CHANCEOFSNOW = "chanceofsnow";
    private static final String FIELD_FEELS_LIKE_C = "FeelsLikeC";
    private static final String FIELD_HEAT_INDEX_F = "HeatIndexF";
    private static final String FIELD_WINDSPEED_MILES = "windspeedMiles";
    private static final String FIELD_WIND_CHILL_C = "WindChillC";
    private static final String FIELD_WEATHER_DESC = "weatherDesc";
    private static final String FIELD_WINDDIR16_POINT = "winddir16Point";
    private static final String FIELD_WEATHER_ICON_URL = "weatherIconUrl";
    private static final String FIELD_WIND_CHILL_F = "WindChillF";
    private static final String FIELD_HEAT_INDEX_C = "HeatIndexC";
    private static final String FIELD_CHANCEOFTHUNDER = "chanceofthunder";
    private static final String FIELD_CHANCEOFREMDRY = "chanceofremdry";
    private static final String FIELD_CLOUDCOVER = "cloudcover";
    private static final String FIELD_CHANCEOFSUNSHINE = "chanceofsunshine";
    private static final String FIELD_DEW_POINT_C = "DewPointC";
    private static final String FIELD_CHANCEOFWINDY = "chanceofwindy";
    private static final String FIELD_DEW_POINT_F = "DewPointF";
    private static final String FIELD_WIND_GUST_MILES = "WindGustMiles";
    private static final String FIELD_WIND_GUST_KMPH = "WindGustKmph";
    private static final String FIELD_WINDDIR_DEGREE = "winddirDegree";
    private static final String FIELD_PRESSURE = "pressure";
    private static final String FIELD_VISIBILITY = "visibility";
    private static final String FIELD_HUMIDITY = "humidity";
    private static final String FIELD_CHANCEOFOVERCAST = "chanceofovercast";
    private static final String FIELD_CHANCEOFFOG = "chanceoffog";
    private static final String FIELD_CHANCEOFRAIN = "chanceofrain";
    private static final String FIELD_TIME = "time";
    private static final String FIELD_CHANCEOFHIGHTEMP = "chanceofhightemp";
    private static final String FIELD_CHANCEOFFROST = "chanceoffrost";
    private static final String FIELD_WEATHER_CODE = "weatherCode";
    @SerializedName(FIELD_WINDSPEED_KMPH)
    private int mWindspeedKmph;
    @SerializedName(FIELD_TEMP_F)
    private int mTempF;
    @SerializedName(FIELD_TEMP_C)
    private int mTempC;
    @SerializedName(FIELD_FEELS_LIKE_F)
    private int mFeelsLikeF;
    @SerializedName(FIELD_PRECIP_MM)
    private double mPrecipMM;
    @SerializedName(FIELD_CHANCEOFSNOW)
    private int mChanceofsnow;
    @SerializedName(FIELD_FEELS_LIKE_C)
    private int mFeelsLikeC;
    @SerializedName(FIELD_HEAT_INDEX_F)
    private int mHeatIndexF;
    @SerializedName(FIELD_WINDSPEED_MILES)
    private int mWindspeedMile;
    @SerializedName(FIELD_WIND_CHILL_C)
    private int mWindChillC;
    @SerializedName(FIELD_WEATHER_DESC)
    private List<WeatherDesc> mWeatherDescs;
    @SerializedName(FIELD_WINDDIR16_POINT)
    private String mWinddir16Point;
    @SerializedName(FIELD_WEATHER_ICON_URL)
    private List<WeatherIconUrl> mWeatherIconUrls;
    @SerializedName(FIELD_WIND_CHILL_F)
    private int mWindChillF;
    @SerializedName(FIELD_HEAT_INDEX_C)
    private int mHeatIndexC;
    @SerializedName(FIELD_CHANCEOFTHUNDER)
    private int mChanceofthunder;
    @SerializedName(FIELD_CHANCEOFREMDRY)
    private int mChanceofremdry;
    @SerializedName(FIELD_CLOUDCOVER)
    private int mCloudcover;
    @SerializedName(FIELD_CHANCEOFSUNSHINE)
    private int mChanceofsunshine;
    @SerializedName(FIELD_DEW_POINT_C)
    private int mDewPointC;
    @SerializedName(FIELD_CHANCEOFWINDY)
    private int mChanceofwindy;
    @SerializedName(FIELD_DEW_POINT_F)
    private int mDewPointF;
    @SerializedName(FIELD_WIND_GUST_MILES)
    private int mWindGustMile;
    @SerializedName(FIELD_WIND_GUST_KMPH)
    private int mWindGustKmph;
    @SerializedName(FIELD_WINDDIR_DEGREE)
    private int mWinddirDegree;
    @SerializedName(FIELD_PRESSURE)
    private int mPressure;
    @SerializedName(FIELD_VISIBILITY)
    private int mVisibility;
    @SerializedName(FIELD_HUMIDITY)
    private int mHumidity;
    @SerializedName(FIELD_CHANCEOFOVERCAST)
    private int mChanceofovercast;
    @SerializedName(FIELD_CHANCEOFFOG)
    private int mChanceoffog;
    @SerializedName(FIELD_CHANCEOFRAIN)
    private int mChanceofrain;
    @SerializedName(FIELD_TIME)
    private int mTime;
    @SerializedName(FIELD_CHANCEOFHIGHTEMP)
    private int mChanceofhightemp;
    @SerializedName(FIELD_CHANCEOFFROST)
    private int mChanceoffrost;
    @SerializedName(FIELD_WEATHER_CODE)
    private int mWeatherCode;

    public Hourly() {

    }

    public Hourly(Parcel in) {
        mWindspeedKmph = in.readInt();
        mTempF = in.readInt();
        mTempC = in.readInt();
        mFeelsLikeF = in.readInt();
        mPrecipMM = in.readDouble();
        mChanceofsnow = in.readInt();
        mFeelsLikeC = in.readInt();
        mHeatIndexF = in.readInt();
        mWindspeedMile = in.readInt();
        mWindChillC = in.readInt();
        mWeatherDescs = new ArrayList<WeatherDesc>();
        in.readTypedList(mWeatherDescs, WeatherDesc.CREATOR);
        mWinddir16Point = in.readString();
        mWeatherIconUrls = new ArrayList<WeatherIconUrl>();
        in.readTypedList(mWeatherIconUrls, WeatherIconUrl.CREATOR);
        mWindChillF = in.readInt();
        mHeatIndexC = in.readInt();
        mChanceofthunder = in.readInt();
        mChanceofremdry = in.readInt();
        mCloudcover = in.readInt();
        mChanceofsunshine = in.readInt();
        mDewPointC = in.readInt();
        mChanceofwindy = in.readInt();
        mDewPointF = in.readInt();
        mWindGustMile = in.readInt();
        mWindGustKmph = in.readInt();
        mWinddirDegree = in.readInt();
        mPressure = in.readInt();
        mVisibility = in.readInt();
        mHumidity = in.readInt();
        mChanceofovercast = in.readInt();
        mChanceoffog = in.readInt();
        mChanceofrain = in.readInt();
        mTime = in.readInt();
        mChanceofhightemp = in.readInt();
        mChanceoffrost = in.readInt();
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

    public int getTempC() {
        return mTempC;
    }

    public void setTempC(int tempC) {
        mTempC = tempC;
    }

    public int getFeelsLikeF() {
        return mFeelsLikeF;
    }

    public void setFeelsLikeF(int feelsLikeF) {
        mFeelsLikeF = feelsLikeF;
    }

    public double getPrecipMM() {
        return mPrecipMM;
    }

    public void setPrecipMM(double precipMM) {
        mPrecipMM = precipMM;
    }

    public int getChanceofsnow() {
        return mChanceofsnow;
    }

    public void setChanceofsnow(int chanceofsnow) {
        mChanceofsnow = chanceofsnow;
    }

    public int getFeelsLikeC() {
        return mFeelsLikeC;
    }

    public void setFeelsLikeC(int feelsLikeC) {
        mFeelsLikeC = feelsLikeC;
    }

    public int getHeatIndexF() {
        return mHeatIndexF;
    }

    public void setHeatIndexF(int heatIndexF) {
        mHeatIndexF = heatIndexF;
    }

    public int getWindspeedMile() {
        return mWindspeedMile;
    }

    public void setWindspeedMile(int windspeedMile) {
        mWindspeedMile = windspeedMile;
    }

    public int getWindChillC() {
        return mWindChillC;
    }

    public void setWindChillC(int windChillC) {
        mWindChillC = windChillC;
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

    public int getWindChillF() {
        return mWindChillF;
    }

    public void setWindChillF(int windChillF) {
        mWindChillF = windChillF;
    }

    public int getHeatIndexC() {
        return mHeatIndexC;
    }

    public void setHeatIndexC(int heatIndexC) {
        mHeatIndexC = heatIndexC;
    }

    public int getChanceofthunder() {
        return mChanceofthunder;
    }

    public void setChanceofthunder(int chanceofthunder) {
        mChanceofthunder = chanceofthunder;
    }

    public int getChanceofremdry() {
        return mChanceofremdry;
    }

    public void setChanceofremdry(int chanceofremdry) {
        mChanceofremdry = chanceofremdry;
    }

    public int getCloudcover() {
        return mCloudcover;
    }

    public void setCloudcover(int cloudcover) {
        mCloudcover = cloudcover;
    }

    public int getChanceofsunshine() {
        return mChanceofsunshine;
    }

    public void setChanceofsunshine(int chanceofsunshine) {
        mChanceofsunshine = chanceofsunshine;
    }

    public int getDewPointC() {
        return mDewPointC;
    }

    public void setDewPointC(int dewPointC) {
        mDewPointC = dewPointC;
    }

    public int getChanceofwindy() {
        return mChanceofwindy;
    }

    public void setChanceofwindy(int chanceofwindy) {
        mChanceofwindy = chanceofwindy;
    }

    public int getDewPointF() {
        return mDewPointF;
    }

    public void setDewPointF(int dewPointF) {
        mDewPointF = dewPointF;
    }

    public int getWindGustMile() {
        return mWindGustMile;
    }

    public void setWindGustMile(int windGustMile) {
        mWindGustMile = windGustMile;
    }

    public int getWindGustKmph() {
        return mWindGustKmph;
    }

    public void setWindGustKmph(int windGustKmph) {
        mWindGustKmph = windGustKmph;
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

    public int getChanceofovercast() {
        return mChanceofovercast;
    }

    public void setChanceofovercast(int chanceofovercast) {
        mChanceofovercast = chanceofovercast;
    }

    public int getChanceoffog() {
        return mChanceoffog;
    }

    public void setChanceoffog(int chanceoffog) {
        mChanceoffog = chanceoffog;
    }

    public int getChanceofrain() {
        return mChanceofrain;
    }

    public void setChanceofrain(int chanceofrain) {
        mChanceofrain = chanceofrain;
    }

    public int getTime() {
        return mTime;
    }

    public void setTime(int time) {
        mTime = time;
    }

    public int getChanceofhightemp() {
        return mChanceofhightemp;
    }

    public void setChanceofhightemp(int chanceofhightemp) {
        mChanceofhightemp = chanceofhightemp;
    }

    public int getChanceoffrost() {
        return mChanceoffrost;
    }

    public void setChanceoffrost(int chanceoffrost) {
        mChanceoffrost = chanceoffrost;
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
        dest.writeInt(mTempC);
        dest.writeInt(mFeelsLikeF);
        dest.writeDouble(mPrecipMM);
        dest.writeInt(mChanceofsnow);
        dest.writeInt(mFeelsLikeC);
        dest.writeInt(mHeatIndexF);
        dest.writeInt(mWindspeedMile);
        dest.writeInt(mWindChillC);
        dest.writeTypedList(mWeatherDescs);
        dest.writeString(mWinddir16Point);
        dest.writeTypedList(mWeatherIconUrls);
        dest.writeInt(mWindChillF);
        dest.writeInt(mHeatIndexC);
        dest.writeInt(mChanceofthunder);
        dest.writeInt(mChanceofremdry);
        dest.writeInt(mCloudcover);
        dest.writeInt(mChanceofsunshine);
        dest.writeInt(mDewPointC);
        dest.writeInt(mChanceofwindy);
        dest.writeInt(mDewPointF);
        dest.writeInt(mWindGustMile);
        dest.writeInt(mWindGustKmph);
        dest.writeInt(mWinddirDegree);
        dest.writeInt(mPressure);
        dest.writeInt(mVisibility);
        dest.writeInt(mHumidity);
        dest.writeInt(mChanceofovercast);
        dest.writeInt(mChanceoffog);
        dest.writeInt(mChanceofrain);
        dest.writeInt(mTime);
        dest.writeInt(mChanceofhightemp);
        dest.writeInt(mChanceoffrost);
        dest.writeInt(mWeatherCode);
    }

    @Override
    public String toString() {
        return "windspeedKmph = " + mWindspeedKmph + ", tempF = " + mTempF + ", tempC = " + mTempC + ", feelsLikeF = " + mFeelsLikeF + ", precipMM = " + mPrecipMM + ", chanceofsnow = " + mChanceofsnow + ", feelsLikeC = " + mFeelsLikeC + ", heatIndexF = " + mHeatIndexF + ", windspeedMile = " + mWindspeedMile + ", windChillC = " + mWindChillC + ", weatherDescs = " + mWeatherDescs + ", winddir16Point = " + mWinddir16Point + ", weatherIconUrls = " + mWeatherIconUrls + ", windChillF = " + mWindChillF + ", heatIndexC = " + mHeatIndexC + ", chanceofthunder = " + mChanceofthunder + ", chanceofremdry = " + mChanceofremdry + ", cloudcover = " + mCloudcover + ", chanceofsunshine = " + mChanceofsunshine + ", dewPointC = " + mDewPointC + ", chanceofwindy = " + mChanceofwindy + ", dewPointF = " + mDewPointF + ", windGustMile = " + mWindGustMile + ", windGustKmph = " + mWindGustKmph + ", winddirDegree = " + mWinddirDegree + ", pressure = " + mPressure + ", visibility = " + mVisibility + ", humidity = " + mHumidity + ", chanceofovercast = " + mChanceofovercast + ", chanceoffog = " + mChanceoffog + ", chanceofrain = " + mChanceofrain + ", time = " + mTime + ", chanceofhightemp = " + mChanceofhightemp + ", chanceoffrost = " + mChanceoffrost + ", weatherCode = " + mWeatherCode;
    }


}
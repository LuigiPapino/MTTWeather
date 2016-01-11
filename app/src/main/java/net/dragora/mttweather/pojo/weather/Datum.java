package net.dragora.mttweather.pojo.weather;

import java.util.ArrayList;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import android.os.Parcel;


public class Datum implements Parcelable {

    public static final Creator<Datum> CREATOR = new Creator<Datum>() {
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        public Datum[] newArray(int size) {
            return new Datum[size];
        }
    };
    private static final String FIELD_REQUEST = "request";
    private static final String FIELD_CURRENT_CONDITION = "current_condition";
    private static final String FIELD_WEATHER = "weather";
    private static final String FIELD_NEAREST_AREA = "nearest_area";
    @SerializedName(FIELD_REQUEST)
    private List<Request> mRequests;
    @SerializedName(FIELD_CURRENT_CONDITION)
    private List<CurrentCondition> mCurrentConditions;
    @SerializedName(FIELD_WEATHER)
    private List<Weather> mWeathers;
    @SerializedName(FIELD_NEAREST_AREA)
    private List<NearestArea> mNearestAreas;

    public Datum() {

    }

    public Datum(Parcel in) {
        mRequests = new ArrayList<Request>();
        in.readTypedList(mRequests, Request.CREATOR);
        mCurrentConditions = new ArrayList<CurrentCondition>();
        in.readTypedList(mCurrentConditions, CurrentCondition.CREATOR);
        mWeathers = new ArrayList<Weather>();
        in.readTypedList(mWeathers, Weather.CREATOR);
        mNearestAreas = new ArrayList<NearestArea>();
        in.readTypedList(mNearestAreas, NearestArea.CREATOR);
    }

    public List<Request> getRequests() {
        return mRequests;
    }

    public void setRequests(List<Request> requests) {
        mRequests = requests;
    }

    public List<CurrentCondition> getCurrentConditions() {
        return mCurrentConditions;
    }

    public void setCurrentConditions(List<CurrentCondition> currentConditions) {
        mCurrentConditions = currentConditions;
    }

    public List<Weather> getWeathers() {
        return mWeathers;
    }

    public void setWeathers(List<Weather> weathers) {
        mWeathers = weathers;
    }

    public List<NearestArea> getNearestAreas() {
        return mNearestAreas;
    }

    public void setNearestAreas(List<NearestArea> nearestAreas) {
        mNearestAreas = nearestAreas;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mRequests);
        dest.writeTypedList(mCurrentConditions);
        dest.writeTypedList(mWeathers);
        dest.writeTypedList(mNearestAreas);
    }

    @Override
    public String toString() {
        return "requests = " + mRequests + ", currentConditions = " + mCurrentConditions + ", weathers = " + mWeathers + ", nearestAreas = " + mNearestAreas;
    }


}
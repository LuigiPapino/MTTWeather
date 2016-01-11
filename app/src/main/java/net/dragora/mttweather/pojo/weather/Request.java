package net.dragora.mttweather.pojo.weather;

import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import android.os.Parcel;


public class Request implements Parcelable {

    public static final Creator<Request> CREATOR = new Creator<Request>() {
        public Request createFromParcel(Parcel in) {
            return new Request(in);
        }

        public Request[] newArray(int size) {
            return new Request[size];
        }
    };
    private static final String FIELD_TYPE = "type";
    private static final String FIELD_QUERY = "query";
    @SerializedName(FIELD_TYPE)
    private String mType;
    @SerializedName(FIELD_QUERY)
    private String mQuery;

    public Request() {

    }

    public Request(Parcel in) {
        mType = in.readString();
        mQuery = in.readString();
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getQuery() {
        return mQuery;
    }

    public void setQuery(String query) {
        mQuery = query;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mType);
        dest.writeString(mQuery);
    }

    @Override
    public String toString() {
        return "type = " + mType + ", query = " + mQuery;
    }


}
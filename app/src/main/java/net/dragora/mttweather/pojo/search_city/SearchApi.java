package net.dragora.mttweather.pojo.search_city;

import java.util.ArrayList;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import android.os.Parcel;


public class SearchApi implements Parcelable {

    public static final Parcelable.Creator<SearchApi> CREATOR = new Parcelable.Creator<SearchApi>() {
        public SearchApi createFromParcel(Parcel in) {
            return new SearchApi(in);
        }

        public SearchApi[] newArray(int size) {
            return new SearchApi[size];
        }
    };
    private static final String FIELD_RESULT = "result";
    @SerializedName(FIELD_RESULT)
    private List<Result> mResults;

    public SearchApi() {

    }

    public SearchApi(Parcel in) {
        mResults = new ArrayList<Result>();
        in.readTypedList(mResults, Result.CREATOR);
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mResults);
    }

    @Override
    public String toString() {
        return "results = " + mResults;
    }


}
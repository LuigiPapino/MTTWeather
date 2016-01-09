package net.dragora.mttweather.pojo.search_city;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


public class SearchCity implements Parcelable {

    public static final Parcelable.Creator<SearchCity> CREATOR = new Parcelable.Creator<SearchCity>() {
        public SearchCity createFromParcel(Parcel in) {
            return new SearchCity(in);
        }

        public SearchCity[] newArray(int size) {
            return new SearchCity[size];
        }
    };
    private static final String FIELD_SEARCH_API = "search_api";
    @SerializedName(FIELD_SEARCH_API)
    private SearchApi mSearchApi;
    private String search;

    public SearchCity() {

    }

    public SearchCity(Parcel in) {
        mSearchApi = in.readParcelable(SearchApi.class.getClassLoader());
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public SearchApi getSearchApi() {
        return mSearchApi;
    }

    public void setSearchApi(SearchApi searchApi) {
        mSearchApi = searchApi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mSearchApi, flags);
    }

    @Override
    public String toString() {
        return "searchApi = " + mSearchApi;
    }


    @NonNull
    public List<Result> getResults() {
        if (mSearchApi != null && mSearchApi.getResults() != null)
            return mSearchApi.getResults();
        else
            return new ArrayList<>();
    }
}
package net.dragora.mttweather.pojo.search_city;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;


public class Country implements Parcelable {

    public static final Parcelable.Creator<Country> CREATOR = new Parcelable.Creator<Country>() {
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
    private static final String FIELD_VALUE = "value";
    @SerializedName(FIELD_VALUE)
    private String mValue;

    public Country() {

    }

    public Country(Parcel in) {
        mValue = in.readString();
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mValue);
    }

    @Override
    public String toString() {
        return "value = " + mValue;
    }


}
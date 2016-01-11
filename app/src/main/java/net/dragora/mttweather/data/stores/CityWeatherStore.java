package net.dragora.mttweather.data.stores;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import net.dragora.mttweather.data.schematicProvider.JsonKeyColumns;
import net.dragora.mttweather.data.schematicProvider.WeatherProvider;
import net.dragora.mttweather.pojo.weather.CityWeather;
import net.dragora.mttweather.pojo.weather.Weather;

import io.reark.reark.data.store.SingleItemContentProviderStore;
import io.reark.reark.utils.Preconditions;

/**
 * Created by nietzsche
 * */
public class CityWeatherStore extends SingleItemContentProviderStore<CityWeather, String> {
    private static final String TAG = CityWeatherStore.class.getSimpleName();

    public CityWeatherStore(@NonNull ContentResolver contentResolver) {
        super(contentResolver);
    }

    @NonNull
    @Override
    protected String getIdFor(@NonNull CityWeather item) {
        Preconditions.checkNotNull(item, "Weather cannot be null.");

        return item.getId();
    }

    @NonNull
    @Override
    public Uri getContentUri() {
        return WeatherProvider.CityWeathers.CITY_WHEATERS;
    }


    @NonNull
    @Override
    protected String[] getProjection() {
        return new String[]{JsonKeyColumns.ID, JsonKeyColumns.JSON};
    }

    @NonNull
    @Override
    protected ContentValues getContentValuesForItem(CityWeather item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(JsonKeyColumns.ID, item.getId());
        contentValues.put(JsonKeyColumns.JSON, new Gson().toJson(item));
        return contentValues;
    }

    @NonNull
    @Override
    protected CityWeather read(Cursor cursor) {
        final String json = cursor.getString(cursor.getColumnIndex(JsonKeyColumns.JSON));
        final CityWeather value = new Gson().fromJson(json, CityWeather.class);
        return value;
    }

    @NonNull
    @Override
    protected ContentValues readRaw(Cursor cursor) {
        final String json = cursor.getString(cursor.getColumnIndex(JsonKeyColumns.JSON));
        ContentValues contentValues = new ContentValues();
        contentValues.put(JsonKeyColumns.JSON, json);
        return contentValues;
    }

    @NonNull
    @Override
    public Uri getUriForKey(@NonNull String id) {
        Preconditions.checkNotNull(id, "Id cannot be null.");

        return WeatherProvider.CityWeathers.withId(id);
    }

    @Override
    protected boolean contentValuesEqual(ContentValues v1, ContentValues v2) {
        return v1.getAsString(JsonKeyColumns.JSON).equals(v2.getAsString(JsonKeyColumns.JSON));
    }
}

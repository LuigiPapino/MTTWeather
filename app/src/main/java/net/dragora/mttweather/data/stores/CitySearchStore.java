package net.dragora.mttweather.data.stores;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import net.dragora.mttweather.data.schematicProvider.WeatherProvider;
import net.dragora.mttweather.data.schematicProvider.SearchCityColumns;
import net.dragora.mttweather.pojo.search_city.SearchCity;

import io.reark.reark.data.store.SingleItemContentProviderStore;
import io.reark.reark.utils.Preconditions;

/**
 * Created by nietzsche
 */
public class CitySearchStore extends SingleItemContentProviderStore<SearchCity, String> {
    private static final String TAG = CitySearchStore.class.getSimpleName();

    public CitySearchStore(@NonNull ContentResolver contentResolver) {
        super(contentResolver);
    }

    @NonNull
    @Override
    protected String getIdFor(@NonNull SearchCity item) {
        Preconditions.checkNotNull(item, "SearchCity cannot be null.");

        return item.getSearch();
    }

    @NonNull
    @Override
    public Uri getContentUri() {
        return WeatherProvider.CitySearches.CITY_SEARCHES;
    }

    @NonNull
    @Override
    protected String[] getProjection() {
        return new String[]{SearchCityColumns.SEARCH, SearchCityColumns.JSON};
    }

    @NonNull
    @Override
    protected ContentValues getContentValuesForItem(SearchCity item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SearchCityColumns.SEARCH, item.getSearch());
        contentValues.put(SearchCityColumns.JSON, new Gson().toJson(item));
        return contentValues;
    }

    @NonNull
    @Override
    protected SearchCity read(Cursor cursor) {
        final String json = cursor.getString(cursor.getColumnIndex(SearchCityColumns.JSON));
        final SearchCity value = new Gson().fromJson(json, SearchCity.class);
        return value;
    }

    @NonNull
    @Override
    protected ContentValues readRaw(Cursor cursor) {
        final String json = cursor.getString(cursor.getColumnIndex(SearchCityColumns.JSON));
        ContentValues contentValues = new ContentValues();
        contentValues.put(SearchCityColumns.JSON, json);
        return contentValues;
    }

    @NonNull
    @Override
    public Uri getUriForKey(@NonNull String id) {
        Preconditions.checkNotNull(id, "Id cannot be null.");

        return WeatherProvider.CitySearches.withSearch(id);
    }

    @Override
    protected boolean contentValuesEqual(ContentValues v1, ContentValues v2) {
        return v1.getAsString(SearchCityColumns.JSON).equals(v2.getAsString(SearchCityColumns.JSON));
    }
}

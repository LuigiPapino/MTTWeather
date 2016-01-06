package net.dragora.mttweather.data.stores;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import net.dragora.mttweather.data.DataLayer;
import net.dragora.mttweather.data.schematicProvider.GitHubProvider;
import net.dragora.mttweather.data.schematicProvider.JsonIdColumns;
import net.dragora.mttweather.data.schematicProvider.UserSettingsColumns;
import net.dragora.mttweather.pojo.UserSettings;

import io.reark.reark.data.store.SingleItemContentProviderStore;
import io.reark.reark.utils.Preconditions;

/**
 * Created by ttuo on 07/01/15.
 */
public class UserSettingsStore extends SingleItemContentProviderStore<UserSettings, Integer> {
    private static final String TAG = UserSettingsStore.class.getSimpleName();

    private static final int DEFAULT_REPOSITORY_ID = 15491874;

    public UserSettingsStore(@NonNull ContentResolver contentResolver) {
        super(contentResolver);

        initUserSettings();
    }

    @NonNull
    @Override
    protected Integer getIdFor(@NonNull UserSettings item) {
        return DataLayer.DEFAULT_USER_ID;
    }

    @NonNull
    @Override
    public Uri getContentUri() {
        return GitHubProvider.UserSettings.USER_SETTINGS;
    }

    private void initUserSettings() {
        query(DataLayer.DEFAULT_USER_ID)
                .first()
                .filter(userSettings -> userSettings == null)
                .subscribe(userSettings -> {
                    put(new UserSettings(DEFAULT_REPOSITORY_ID));
                });
    }

    @NonNull
    @Override
    protected String[] getProjection() {
        return new String[] { UserSettingsColumns.ID, UserSettingsColumns.JSON };
    }

    @NonNull
    @Override
    protected ContentValues getContentValuesForItem(UserSettings item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(JsonIdColumns.ID, DataLayer.DEFAULT_USER_ID);
        contentValues.put(JsonIdColumns.JSON, new Gson().toJson(item));
        return contentValues;
    }

    @NonNull
    @Override
    protected UserSettings read(Cursor cursor) {
        final String json = cursor.getString(cursor.getColumnIndex(JsonIdColumns.JSON));
        final UserSettings value = new Gson().fromJson(json, UserSettings.class);
        return value;
    }

    @NonNull
    @Override
    protected ContentValues readRaw(Cursor cursor) {
        final String json = cursor.getString(cursor.getColumnIndex(JsonIdColumns.JSON));
        ContentValues contentValues = new ContentValues();
        contentValues.put(JsonIdColumns.JSON, json);
        return contentValues;
    }

    @NonNull
    @Override
    public Uri getUriForKey(@NonNull Integer id) {
        Preconditions.checkNotNull(id, "Id cannot be null.");

        return GitHubProvider.UserSettings.withId(id);
    }

    @Override
    protected boolean contentValuesEqual(ContentValues v1, ContentValues v2) {
        return v1.getAsString(JsonIdColumns.JSON).equals(v2.getAsString(JsonIdColumns.JSON));
    }
}

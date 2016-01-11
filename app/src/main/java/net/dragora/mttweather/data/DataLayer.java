package net.dragora.mttweather.data;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import net.dragora.mttweather.data.stores.CitySearchStore;
import net.dragora.mttweather.data.stores.CityWeatherStore;
import net.dragora.mttweather.data.stores.NetworkRequestStatusStore;
import net.dragora.mttweather.data.stores.UserSettingsStore;
import net.dragora.mttweather.network.WeatherService;
import net.dragora.mttweather.network.NetworkService;
import net.dragora.mttweather.pojo.UserSettings;
import net.dragora.mttweather.pojo.search_city.SearchCity;
import net.dragora.mttweather.pojo.weather.CityWeather;
import net.dragora.mttweather.pojo.weather.Weather;

import io.reark.reark.data.DataStreamNotification;
import io.reark.reark.data.utils.DataLayerUtils;
import io.reark.reark.pojo.NetworkRequestStatus;
import io.reark.reark.utils.Preconditions;
import jonathanfinerty.once.Once;
import rx.Observable;



public class DataLayer extends DataLayerBase {
    public static final int DEFAULT_USER_ID = 0;
    private static final String TAG = DataLayer.class.getSimpleName();
    protected final UserSettingsStore userSettingsStore;
    private final Context context;

    public DataLayer(@NonNull Context context,
                     @NonNull UserSettingsStore userSettingsStore,
                     @NonNull NetworkRequestStatusStore networkRequestStatusStore,
                     @NonNull CityWeatherStore cityWeatherStore,
                     @NonNull CitySearchStore citySearchStore) {
        super(networkRequestStatusStore, cityWeatherStore, citySearchStore);

        Preconditions.checkNotNull(context, "Context cannot be null.");
        Preconditions.checkNotNull(userSettingsStore, "User Settings Store cannot be null.");

        this.context = context;
        this.userSettingsStore = userSettingsStore;

        if (!Once.beenDone(Once.THIS_APP_INSTALL, "user_settings_init")) {
            Once.markDone("user_settings_init");
            UserSettings userSettings = UserSettings.init(context);
            setUserSettings(userSettings);
        }
    }

    @NonNull
    public Observable<DataStreamNotification<SearchCity>> citySearch(@NonNull final String searchString) {
        Preconditions.checkNotNull(searchString, "Search string Store cannot be null.");

        final Uri uri = citySearchStore.getUriForKey(searchString);
        final Observable<NetworkRequestStatus> networkRequestStatusObservable =
                networkRequestStatusStore.getStream(uri.toString().hashCode());
        final Observable<SearchCity> citySearchObservable =
                citySearchStore.getStream(searchString);
        return DataLayerUtils.createDataStreamNotificationObservable(
                networkRequestStatusObservable, citySearchObservable);
    }

    @NonNull
    public Observable<DataStreamNotification<SearchCity>> fetchAndGetCitySearch(@NonNull final String searchString) {
        Preconditions.checkNotNull(searchString, "Search string Store cannot be null.");
        fetchCitySearch(searchString);

        final Observable<DataStreamNotification<SearchCity>> citySearchStream =
                citySearch(searchString);
        return citySearchStream;
    }

    private void fetchCitySearch(@NonNull final String searchString) {
        Preconditions.checkNotNull(searchString, "Search string Store cannot be null.");

        Intent intent = new Intent(context, NetworkService.class);
        intent.putExtra("serviceUriString", WeatherService.CITY_SEARCH.toString());
        intent.putExtra("searchString", searchString);
        context.startService(intent);
    }

    @NonNull
    public Observable<DataStreamNotification<CityWeather>> getWeather(@NonNull String id) {
        Preconditions.checkNotNull(id, "Id cannot be null.");
        final Uri uri = cityWeatherStore.getUriForKey(id);
        final Observable<NetworkRequestStatus> networkRequestStatusObservable =
                networkRequestStatusStore.getStream(uri.toString().hashCode());
        final Observable<CityWeather> cityWeatherObservable =
                cityWeatherStore.getStream(id);
        return DataLayerUtils.createDataStreamNotificationObservable(
                networkRequestStatusObservable, cityWeatherObservable);
    }

    @NonNull
    public Observable<DataStreamNotification<CityWeather>> fetchAndGetWeather(@NonNull String id) {
        Preconditions.checkNotNull(id, "Id cannot be null.");

        fetchWeather(id);
        return getWeather(id);
    }

    private void fetchWeather(@NonNull String id) {
        Intent intent = new Intent(context, NetworkService.class);
        intent.putExtra("serviceUriString", WeatherService.CITY_WEATHER.toString());
        intent.putExtra("id", id);
        context.startService(intent);
    }

    @NonNull
    public Observable<UserSettings> getUserSettings() {
        return userSettingsStore.getStream(DEFAULT_USER_ID);
    }

    public void setUserSettings(@NonNull UserSettings userSettings) {
        Preconditions.checkNotNull(userSettings, "User Settings cannot be null.");

        userSettingsStore.put(userSettings);
    }

    public interface GetUserSettings {
        @NonNull
        Observable<UserSettings> call();
    }

    public interface SetUserSettings {
        void call(@NonNull UserSettings userSettings);
    }

    public interface GetWeather {
        @NonNull
        Observable<DataStreamNotification<CityWeather>> call(@NonNull String id);
    }

    public interface FetchAndGetWeather extends GetWeather {

    }

    public interface GetCitySearch {
        @NonNull
        Observable<DataStreamNotification<SearchCity>> call(@NonNull String search);
    }
}

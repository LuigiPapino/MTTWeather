package net.dragora.mttweather.network.fetchers;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import net.dragora.mttweather.data.stores.CityWeatherStore;
import net.dragora.mttweather.network.WeatherService;
import net.dragora.mttweather.network.NetworkApi;
import net.dragora.mttweather.pojo.weather.CityWeather;
import net.dragora.mttweather.pojo.weather.Weather;

import io.reark.reark.pojo.NetworkRequestStatus;
import io.reark.reark.utils.Log;
import io.reark.reark.utils.Preconditions;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class CityWeatherFetcher extends AppFetcherBase {
    private static final String TAG = CityWeatherFetcher.class.getSimpleName();

    @NonNull
    private final CityWeatherStore cityWeatherStore;

    public CityWeatherFetcher(@NonNull NetworkApi networkApi,
                              @NonNull Action1<NetworkRequestStatus> updateNetworkRequestStatus,
                              @NonNull CityWeatherStore cityWeatherStore) {
        super(networkApi, updateNetworkRequestStatus);

        Preconditions.checkNotNull(cityWeatherStore, "weather Store cannot be null.");

        this.cityWeatherStore = cityWeatherStore;
    }

    @Override
    public void fetch(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent, "Fetch Intent cannot be null.");

        final String id = intent.getStringExtra("id");
        if (id != null) {
            fetchWeather(id);
        } else {
            Log.e(TAG, "No repositoryId provided in the intent extras");
        }
    }

    private void fetchWeather(final String id) {
        Log.d(TAG, "fetchWeather(" + id + ")");
        if (requestMap.containsKey(id.hashCode()) &&
                !requestMap.get(id.hashCode()).isUnsubscribed()) {
            Log.d(TAG, "Found an ongoing request for weather " + id);
            return;
        }
        final String uri = cityWeatherStore.getUriForKey(id).toString();
        Subscription subscription = createNetworkObservable(id)
                .subscribeOn(Schedulers.computation())
                .doOnError(doOnError(uri))
                .doOnCompleted(() -> completeRequest(uri))
                .subscribe(cityWeatherStore::put,
                        e -> Log.e(TAG, "Error fetching Weather " + id, e));
        requestMap.put(id.hashCode(), subscription);
        startRequest(uri);
    }

    @NonNull
    private Observable<CityWeather> createNetworkObservable(String id) {
        return networkApi.getWeather(id);
    }

    @NonNull
    @Override
    public Uri getServiceUri() {
        return WeatherService.CITY_WEATHER;
    }
}

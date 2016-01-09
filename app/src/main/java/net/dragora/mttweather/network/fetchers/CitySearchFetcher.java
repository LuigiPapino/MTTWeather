package net.dragora.mttweather.network.fetchers;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import net.dragora.mttweather.data.stores.CitySearchStore;
import net.dragora.mttweather.network.WeatherService;
import net.dragora.mttweather.network.NetworkApi;
import net.dragora.mttweather.pojo.search_city.SearchCity;

import io.reark.reark.pojo.NetworkRequestStatus;
import io.reark.reark.utils.Log;
import io.reark.reark.utils.Preconditions;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class CitySearchFetcher extends AppFetcherBase {
    private static final String TAG = CitySearchFetcher.class.getSimpleName();

    private final CitySearchStore citySearchStore;

    public CitySearchFetcher(@NonNull NetworkApi networkApi,
                             @NonNull Action1<NetworkRequestStatus> updateNetworkRequestStatus,
                             @NonNull CitySearchStore citySearchStore) {
        super(networkApi, updateNetworkRequestStatus);

        Preconditions.checkNotNull(citySearchStore, ""
                + "City Search Store cannot be null.");

        this.citySearchStore = citySearchStore;
    }

    @Override
    public void fetch(@NonNull Intent intent) {
        final String searchString = intent.getStringExtra("searchString");
        if (searchString != null) {
            fetchCitySearch(searchString);
        } else {
            Log.e(TAG, "No searchString provided in the intent extras");
        }
    }

    private void fetchCitySearch(@NonNull final String searchString) {
        Preconditions.checkNotNull(searchString, "Search String cannot be null.");

        Log.d(TAG, "fetchCitySearch(" + searchString + ")");
        if (requestMap.containsKey(searchString.hashCode()) &&
                !requestMap.get(searchString.hashCode()).isUnsubscribed()) {
            Log.d(TAG, "Found an ongoing request for repository " + searchString);
            return;
        }
        final String uri = citySearchStore.getUriForKey(searchString).toString();
        Subscription subscription = createNetworkObservable(searchString)
                .subscribeOn(Schedulers.computation())
                .map((citySearch) -> {
                    citySearch.setSearch(searchString);
                    return citySearch;
                })
                .doOnCompleted(() -> completeRequest(uri))
                .doOnError(doOnError(uri))
                .subscribe(citySearchStore::put,
                        e -> Log.e(TAG, "Error fetching city search for '" + searchString + "'", e));
        requestMap.put(searchString.hashCode(), subscription);
        startRequest(uri);
    }

    @NonNull
    private Observable<SearchCity> createNetworkObservable(@NonNull final String searchString) {
        Preconditions.checkNotNull(searchString, "Search String cannot be null.");

        return networkApi.search(searchString);
    }

    @NonNull
    @Override
    public Uri getServiceUri() {
        return WeatherService.CITY_SEARCH;
    }
}

package net.dragora.mttweather.network;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import net.dragora.mttweather.data.DataLayerBase;
import net.dragora.mttweather.data.stores.CitySearchStore;
import net.dragora.mttweather.data.stores.CityWeatherStore;
import net.dragora.mttweather.data.stores.NetworkRequestStatusStore;

import io.reark.reark.network.fetchers.Fetcher;
import io.reark.reark.network.fetchers.UriFetcherManager;
import io.reark.reark.utils.Log;
import io.reark.reark.utils.Preconditions;


/**
 * Created by ttuo on 16/04/15.
 */
public class ServiceDataLayer extends DataLayerBase {
    private static final String TAG = ServiceDataLayer.class.getSimpleName();

    final private UriFetcherManager fetcherManager;

    public ServiceDataLayer(@NonNull UriFetcherManager fetcherManager,
                            @NonNull NetworkRequestStatusStore networkRequestStatusStore,
                            @NonNull CityWeatherStore cityWeatherStore,
                            @NonNull CitySearchStore citySearchStore) {
        super(networkRequestStatusStore, cityWeatherStore, citySearchStore);

        Preconditions.checkNotNull(fetcherManager,
                "FetcherManager cannot be null.");
        this.fetcherManager = fetcherManager;
    }

    public void processIntent(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent, "Intent cannot be null.");

        final String serviceUriString = intent.getStringExtra("serviceUriString");
        if (serviceUriString != null) {
            final Uri serviceUri = Uri.parse(serviceUriString);
            Fetcher matchingFetcher = fetcherManager.findFetcher(serviceUri);
            if (matchingFetcher != null) {
                Log.v(TAG, "Fetcher found for " + serviceUri);
                matchingFetcher.fetch(intent);
            } else {
                Log.e(TAG, "Unknown Uri " + serviceUri);
            }
        } else {
            Log.e(TAG, "No Uri defined");
        }
    }
}

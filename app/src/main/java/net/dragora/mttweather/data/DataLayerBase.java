package net.dragora.mttweather.data;

import android.support.annotation.NonNull;

import net.dragora.mttweather.data.stores.CitySearchStore;
import net.dragora.mttweather.data.stores.CityWeatherStore;
import net.dragora.mttweather.data.stores.NetworkRequestStatusStore;

import io.reark.reark.utils.Preconditions;


/**
 * Created by ttuo on 16/04/15.
 */
abstract public class DataLayerBase {
    protected final NetworkRequestStatusStore networkRequestStatusStore;
    protected final CityWeatherStore cityWeatherStore;
    protected final CitySearchStore citySearchStore;

    public DataLayerBase(@NonNull NetworkRequestStatusStore networkRequestStatusStore,
                         @NonNull CityWeatherStore cityWeatherStore,
                         @NonNull CitySearchStore citySearchStore) {
        Preconditions.checkNotNull(networkRequestStatusStore,
                                   "Network Request Status Store cannot be null.");
        Preconditions.checkNotNull(cityWeatherStore,
                                   "GitHub Repository Store cannot be null.");
        Preconditions.checkNotNull(citySearchStore,
                                   "GitHub Repository Search Store cannot be null.");

        this.networkRequestStatusStore = networkRequestStatusStore;
        this.cityWeatherStore = cityWeatherStore;
        this.citySearchStore = citySearchStore;
    }
}

package net.dragora.mttweather.data;

import android.content.Context;

import net.dragora.mttweather.data.stores.CitySearchStore;
import net.dragora.mttweather.data.stores.CityWeatherStore;
import net.dragora.mttweather.data.stores.NetworkRequestStatusStore;
import net.dragora.mttweather.data.stores.StoreModule;
import net.dragora.mttweather.data.stores.UserSettingsStore;
import net.dragora.mttweather.injections.ForApplication;
import net.dragora.mttweather.network.ServiceDataLayer;
import net.dragora.mttweather.network.fetchers.FetcherModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reark.reark.network.fetchers.UriFetcherManager;


@Module(includes = { FetcherModule.class, StoreModule.class })
public final class DataStoreModule {

    @Provides
    public DataLayer.GetUserSettings provideGetUserSettings(DataLayer dataLayer) {
        return dataLayer::getUserSettings;
    }

    @Provides
    public DataLayer.SetUserSettings provideSetUserSettings(DataLayer dataLayer) {
        return dataLayer::setUserSettings;
    }

    @Provides
    public DataLayer.FetchAndGetWeather provideFetchAndGetWeather(DataLayer dataLayer) {
        return dataLayer::fetchAndGetWeather;
    }

    @Provides
    public DataLayer.GetCitySearch provideGetCitySearch(DataLayer dataLayer) {
        return dataLayer::fetchAndGetCitySearch;
    }

    @Provides
    public DataLayer.GetWeather provideGetWeather(DataLayer dataLayer) {
        return dataLayer::getWeather;
    }

    @Provides
    @Singleton
    public DataLayer provideApplicationDataLayer(@ForApplication Context context,
                                                 UserSettingsStore userSettingsStore,
                                                 NetworkRequestStatusStore networkRequestStatusStore,
                                                 CityWeatherStore cityWeatherStore,
                                                 CitySearchStore citySearchStore) {
        return new DataLayer(context, userSettingsStore, networkRequestStatusStore, cityWeatherStore, citySearchStore);
    }

    @Provides
    @Singleton
    public ServiceDataLayer provideServiceDataLayer(UriFetcherManager fetcherManager,
                                                    NetworkRequestStatusStore networkRequestStatusStore,
                                                    CityWeatherStore cityWeatherStore,
                                                    CitySearchStore citySearchStore) {
        return new ServiceDataLayer(fetcherManager,
                                    networkRequestStatusStore,
                cityWeatherStore,
                citySearchStore);
    }

}

package net.dragora.mttweather.network.fetchers;

import net.dragora.mttweather.data.stores.CitySearchStore;
import net.dragora.mttweather.data.stores.CityWeatherStore;
import net.dragora.mttweather.data.stores.NetworkRequestStatusStore;
import net.dragora.mttweather.network.NetworkApi;
import net.dragora.mttweather.network.NetworkModule;

import java.util.Arrays;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reark.reark.network.fetchers.Fetcher;
import io.reark.reark.network.fetchers.UriFetcherManager;


/**
 * Created by Pawel Polanski on 5/16/15.
 */
@Module(includes = NetworkModule.class)
public final class FetcherModule {

    @Provides
    @Named("cityWeather")
    public Fetcher provideWeatherFetcher(NetworkApi networkApi,
                                         NetworkRequestStatusStore networkRequestStatusStore,
                                         CityWeatherStore cityWeatherStore) {
        return new CityWeatherFetcher(networkApi,
                                           networkRequestStatusStore::put,
                cityWeatherStore);
    }

    @Provides
    @Named("citySearch")
    public Fetcher provideCitySearchFetcher(NetworkApi networkApi,
                                            NetworkRequestStatusStore networkRequestStatusStore,
                                            CityWeatherStore cityWeatherStore,
                                            CitySearchStore citySearchStore) {
        return new CitySearchFetcher(networkApi,
                                                 networkRequestStatusStore::put,

                citySearchStore);
    }

    @Provides
    public UriFetcherManager provideUriFetcherManager(@Named("cityWeather") Fetcher cityWeatherFetcher,
                                                      @Named("citySearch") Fetcher citySearchFetcher) {
        return new UriFetcherManager.Builder()
                .fetchers(Arrays.asList(cityWeatherFetcher, citySearchFetcher))
                .build();
    }
}

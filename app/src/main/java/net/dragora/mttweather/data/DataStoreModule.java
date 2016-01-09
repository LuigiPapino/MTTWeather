package net.dragora.mttweather.data;

import android.content.Context;

import net.dragora.mttweather.data.stores.CitySearchStore;
import net.dragora.mttweather.data.stores.GitHubRepositoryStore;
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


/**
 * Created by pt2121 on 2/20/15.
 */
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
    public DataLayer.FetchAndGetGitHubRepository provideFetchAndGetGitHubRepository(DataLayer dataLayer) {
        return dataLayer::fetchAndGetGitHubRepository;
    }

    @Provides
    public DataLayer.GetCitySearch provideGetGitHubRepositorySearch(DataLayer dataLayer) {
        return dataLayer::fetchAndGetCitySearch;
    }

    @Provides
    public DataLayer.GetGitHubRepository provideGetGitHubRepository(DataLayer dataLayer) {
        return dataLayer::getGitHubRepository;
    }

    @Provides
    @Singleton
    public DataLayer provideApplicationDataLayer(@ForApplication Context context,
                                                 UserSettingsStore userSettingsStore,
                                                 NetworkRequestStatusStore networkRequestStatusStore,
                                                 GitHubRepositoryStore gitHubRepositoryStore,
                                                 CitySearchStore citySearchStore) {
        return new DataLayer(context, userSettingsStore, networkRequestStatusStore, gitHubRepositoryStore, citySearchStore);
    }

    @Provides
    @Singleton
    public ServiceDataLayer provideServiceDataLayer(UriFetcherManager fetcherManager,
                                                    NetworkRequestStatusStore networkRequestStatusStore,
                                                    GitHubRepositoryStore gitHubRepositoryStore,
                                                    CitySearchStore citySearchStore) {
        return new ServiceDataLayer(fetcherManager,
                                    networkRequestStatusStore,
                                    gitHubRepositoryStore,
                citySearchStore);
    }

}

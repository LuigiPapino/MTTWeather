package net.dragora.mttweather.data.stores;

import android.content.ContentResolver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Pawel Polanski on 5/16/15.
 */
@Module
public final class StoreModule {

    @Provides
    @Singleton
    public UserSettingsStore provideUserSettingsStore(ContentResolver contentResolver) {
        return new UserSettingsStore(contentResolver);
    }

    @Provides
    @Singleton
    public NetworkRequestStatusStore provideNetworkRequestStatusStore(ContentResolver contentResolver) {
        return new NetworkRequestStatusStore(contentResolver);
    }

    @Provides
    @Singleton
    public CityWeatherStore provideGitHubRepositoryStore(ContentResolver contentResolver) {
        return new CityWeatherStore(contentResolver);
    }

    @Provides
    @Singleton
    public CitySearchStore provideGitHubRepositorySearchStore(ContentResolver contentResolver) {
        return new CitySearchStore(contentResolver);
    }

}

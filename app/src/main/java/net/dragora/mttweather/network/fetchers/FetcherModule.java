package net.dragora.mttweather.network.fetchers;

import net.dragora.mttweather.data.stores.CitySearchStore;
import net.dragora.mttweather.data.stores.GitHubRepositoryStore;
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
    @Named("gitHubRepository")
    public Fetcher provideGitHubRepositoryFetcher(NetworkApi networkApi,
                                                  NetworkRequestStatusStore networkRequestStatusStore,
                                                  GitHubRepositoryStore gitHubRepositoryStore) {
        return new GitHubRepositoryFetcher(networkApi,
                                           networkRequestStatusStore::put,
                                           gitHubRepositoryStore);
    }

    @Provides
    @Named("citySearch")
    public Fetcher provideCitySearchFetcher(NetworkApi networkApi,
                                            NetworkRequestStatusStore networkRequestStatusStore,
                                            GitHubRepositoryStore gitHubRepositoryStore,
                                            CitySearchStore citySearchStore) {
        return new CitySearchFetcher(networkApi,
                                                 networkRequestStatusStore::put,

                citySearchStore);
    }

    @Provides
    public UriFetcherManager provideUriFetcherManager(@Named("gitHubRepository")Fetcher gitHubRepositoryFetcher,
                                                      @Named("citySearch") Fetcher gitHubRepositorySearchFetcher) {
        return new UriFetcherManager.Builder()
                .fetchers(Arrays.asList(gitHubRepositoryFetcher, gitHubRepositorySearchFetcher))
                .build();
    }
}

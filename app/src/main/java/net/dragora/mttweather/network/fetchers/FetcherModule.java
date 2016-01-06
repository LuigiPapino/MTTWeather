package net.dragora.mttweather.network.fetchers;

import net.dragora.mttweather.data.stores.GitHubRepositorySearchStore;
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
    @Named("gitHubRepositorySearch")
    public Fetcher provideGitHubRepositorySearchFetcher(NetworkApi networkApi,
                                                        NetworkRequestStatusStore networkRequestStatusStore,
                                                        GitHubRepositoryStore gitHubRepositoryStore,
                                                        GitHubRepositorySearchStore gitHubRepositorySearchStore) {
        return new GitHubRepositorySearchFetcher(networkApi,
                                                 networkRequestStatusStore::put,
                                                 gitHubRepositoryStore,
                                                 gitHubRepositorySearchStore);
    }

    @Provides
    public UriFetcherManager provideUriFetcherManager(@Named("gitHubRepository")Fetcher gitHubRepositoryFetcher,
                                                      @Named("gitHubRepositorySearch") Fetcher gitHubRepositorySearchFetcher) {
        return new UriFetcherManager.Builder()
                .fetchers(Arrays.asList(gitHubRepositoryFetcher, gitHubRepositorySearchFetcher))
                .build();
    }
}

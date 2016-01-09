package net.dragora.mttweather.data;

import android.support.annotation.NonNull;

import net.dragora.mttweather.data.stores.CitySearchStore;
import net.dragora.mttweather.data.stores.GitHubRepositoryStore;
import net.dragora.mttweather.data.stores.NetworkRequestStatusStore;

import io.reark.reark.utils.Preconditions;


/**
 * Created by ttuo on 16/04/15.
 */
abstract public class DataLayerBase {
    protected final NetworkRequestStatusStore networkRequestStatusStore;
    protected final GitHubRepositoryStore gitHubRepositoryStore;
    protected final CitySearchStore citySearchStore;

    public DataLayerBase(@NonNull NetworkRequestStatusStore networkRequestStatusStore,
                         @NonNull GitHubRepositoryStore gitHubRepositoryStore,
                         @NonNull CitySearchStore citySearchStore) {
        Preconditions.checkNotNull(networkRequestStatusStore,
                                   "Network Request Status Store cannot be null.");
        Preconditions.checkNotNull(gitHubRepositoryStore,
                                   "GitHub Repository Store cannot be null.");
        Preconditions.checkNotNull(citySearchStore,
                                   "GitHub Repository Search Store cannot be null.");

        this.networkRequestStatusStore = networkRequestStatusStore;
        this.gitHubRepositoryStore = gitHubRepositoryStore;
        this.citySearchStore = citySearchStore;
    }
}

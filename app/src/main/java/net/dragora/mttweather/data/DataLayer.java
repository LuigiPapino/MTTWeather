package net.dragora.mttweather.data;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import net.dragora.mttweather.data.stores.CitySearchStore;
import net.dragora.mttweather.data.stores.GitHubRepositoryStore;
import net.dragora.mttweather.data.stores.NetworkRequestStatusStore;
import net.dragora.mttweather.data.stores.UserSettingsStore;
import net.dragora.mttweather.network.WeatherService;
import net.dragora.mttweather.network.NetworkService;
import net.dragora.mttweather.pojo.GitHubRepository;
import net.dragora.mttweather.pojo.GitHubRepositorySearch;
import net.dragora.mttweather.pojo.UserSettings;
import net.dragora.mttweather.pojo.search_city.SearchCity;

import io.reark.reark.data.DataStreamNotification;
import io.reark.reark.data.utils.DataLayerUtils;
import io.reark.reark.pojo.NetworkRequestStatus;
import io.reark.reark.utils.Preconditions;
import rx.Observable;


/**
 * Created by ttuo on 19/03/14.
 */
public class DataLayer extends DataLayerBase {
    public static final int DEFAULT_USER_ID = 0;
    private static final String TAG = DataLayer.class.getSimpleName();
    protected final UserSettingsStore userSettingsStore;
    private final Context context;

    public DataLayer(@NonNull Context context,
                     @NonNull UserSettingsStore userSettingsStore,
                     @NonNull NetworkRequestStatusStore networkRequestStatusStore,
                     @NonNull GitHubRepositoryStore gitHubRepositoryStore,
                     @NonNull CitySearchStore citySearchStore) {
        super(networkRequestStatusStore, gitHubRepositoryStore, citySearchStore);

        Preconditions.checkNotNull(context, "Context cannot be null.");
        Preconditions.checkNotNull(userSettingsStore, "User Settings Store cannot be null.");

        this.context = context;
        this.userSettingsStore = userSettingsStore;
    }

    @NonNull
    public Observable<DataStreamNotification<SearchCity>> citySearch(@NonNull final String searchString) {
        Preconditions.checkNotNull(searchString, "Search string Store cannot be null.");

        final Uri uri = citySearchStore.getUriForKey(searchString);
        final Observable<NetworkRequestStatus> networkRequestStatusObservable =
                networkRequestStatusStore.getStream(uri.toString().hashCode());
        final Observable<SearchCity> citySearchObservable =
                citySearchStore.getStream(searchString);
        return DataLayerUtils.createDataStreamNotificationObservable(
                networkRequestStatusObservable, citySearchObservable);
    }

    @NonNull
    public Observable<DataStreamNotification<SearchCity>> fetchAndGetCitySearch(@NonNull final String searchString) {
        Preconditions.checkNotNull(searchString, "Search string Store cannot be null.");
        fetchCitySearch(searchString);

        final Observable<DataStreamNotification<SearchCity>> citySearchStream =
                citySearch(searchString);
        return citySearchStream;
    }

    private void fetchCitySearch(@NonNull final String searchString) {
        Preconditions.checkNotNull(searchString, "Search string Store cannot be null.");

        Intent intent = new Intent(context, NetworkService.class);
        intent.putExtra("serviceUriString", WeatherService.CITY_SEARCH.toString());
        intent.putExtra("searchString", searchString);
        context.startService(intent);
    }

    @NonNull
    public Observable<GitHubRepository> getGitHubRepository(@NonNull Integer repositoryId) {
        Preconditions.checkNotNull(repositoryId, "Repository Id cannot be null.");

        return gitHubRepositoryStore.getStream(repositoryId);
    }

    @NonNull
    public Observable<GitHubRepository> fetchAndGetGitHubRepository(@NonNull Integer repositoryId) {
        Preconditions.checkNotNull(repositoryId, "Repository Id cannot be null.");

        fetchGitHubRepository(repositoryId);
        return getGitHubRepository(repositoryId);
    }

    private void fetchGitHubRepository(@NonNull Integer repositoryId) {
        Intent intent = new Intent(context, NetworkService.class);
        intent.putExtra("serviceUriString", WeatherService.REPOSITORY.toString());
        intent.putExtra("id", repositoryId);
        context.startService(intent);
    }

    @NonNull
    public Observable<UserSettings> getUserSettings() {
        return userSettingsStore.getStream(DEFAULT_USER_ID);
    }

    public void setUserSettings(@NonNull UserSettings userSettings) {
        Preconditions.checkNotNull(userSettings, "User Settings cannot be null.");

        userSettingsStore.put(userSettings);
    }

    public interface GetUserSettings {
        @NonNull
        Observable<UserSettings> call();
    }

    public interface SetUserSettings {
        void call(@NonNull UserSettings userSettings);
    }

    public interface GetGitHubRepository {
        @NonNull
        Observable<GitHubRepository> call(int repositoryId);
    }

    public interface FetchAndGetGitHubRepository extends GetGitHubRepository {

    }

    public interface GetCitySearch {
        @NonNull
        Observable<DataStreamNotification<SearchCity>> call(@NonNull String search);
    }
}

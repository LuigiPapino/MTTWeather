package net.dragora.mttweather.data.schematicProvider;

import android.net.Uri;
import android.support.annotation.NonNull;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

import io.reark.reark.utils.Preconditions;


@ContentProvider(authority = WeatherProvider.AUTHORITY, database = WeatherDatabase.class)
public class WeatherProvider {
    public static final String AUTHORITY = "net.dragora.mttweather.data.schematicProvider.WeatherProvider";

    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    private static Uri buildUri(@NonNull String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        Uri uri = builder.build();
        Preconditions.checkNotNull(uri, "Uri cannot be null.");
        return uri;
    }

    @TableEndpoint(table = WeatherDatabase.GITHUB_REPOSITORIES)
    public static class GitHubRepositories {
        @ContentUri(
                path = WeatherDatabase.GITHUB_REPOSITORIES,
                type = "vnd.android.cursor.dir/vnd.io.reark.rxgithubapp.repository",
                defaultSort = JsonIdColumns.ID + " ASC")
        public static final Uri GITHUB_REPOSITORIES = Uri.parse("content://" + AUTHORITY + "/" + WeatherDatabase.GITHUB_REPOSITORIES);

        @InexactContentUri(
                path = WeatherDatabase.GITHUB_REPOSITORIES + "/*",
                name = "GITHUB_REPOSITORIES_ID",
                type = "vnd.android.cursor.item/vnd.io.reark.rxgithubapp.repository",
                whereColumn = GitHubRepositoryColumns.ID,
                pathSegment = 1)
        public static Uri withId(long id) {
            return buildUri(WeatherDatabase.GITHUB_REPOSITORIES, String.valueOf(id));
        }
    }

    @TableEndpoint(table = WeatherDatabase.CITY_SEARCHES)
    public static class CitySearches {
        @ContentUri(
                path = WeatherDatabase.CITY_SEARCHES,
                type = "vnd.android.cursor.dir/vnd.net.dragora.mttweather.citysearch",
                defaultSort = SearchCityColumns.SEARCH + " ASC")
        public static final Uri CITY_SEARCHES = Uri.parse("content://" + AUTHORITY + "/" + WeatherDatabase.CITY_SEARCHES);

        @InexactContentUri(
                path = WeatherDatabase.CITY_SEARCHES + "/*",
                name = "CITY_SEARCHES_SEARCH",
                type = "vnd.android.cursor.item/vnd.net.dragora.mttweather.citysearch",
                whereColumn = SearchCityColumns.SEARCH,
                pathSegment = 1)
        public static Uri withSearch(String search) {
            return buildUri(WeatherDatabase.CITY_SEARCHES, search);
        }
    }

    @TableEndpoint(table = WeatherDatabase.USER_SETTINGS)
    public static class UserSettings {
        @ContentUri(
                path = WeatherDatabase.USER_SETTINGS,
                type = "vnd.android.cursor.dir/vnd.net.dragora.mttweather.usersettings",
                defaultSort = JsonIdColumns.ID + " ASC")
        public static final Uri USER_SETTINGS = Uri.parse("content://" + AUTHORITY + "/" + WeatherDatabase.USER_SETTINGS);

        @InexactContentUri(
                path = WeatherDatabase.USER_SETTINGS + "/#",
                name = "USER_SETTINGS_ID",
                type = "vnd.android.cursor.item/vnd.net.dragora.mttweather.usersettings",
                whereColumn = UserSettingsColumns.ID,
                pathSegment = 1)
        public static Uri withId(long id) {
            return buildUri(WeatherDatabase.USER_SETTINGS, String.valueOf(id));
        }
    }

    @TableEndpoint(table = WeatherDatabase.NETWORK_REQUEST_STATUSES)
    public static class NetworkRequestStatuses {
        @ContentUri(
                path = WeatherDatabase.NETWORK_REQUEST_STATUSES,
                type = "vnd.android.cursor.dir/vnd.net.dragora.mttweather.networkrequeststatus",
                defaultSort = JsonIdColumns.ID + " ASC")
        public static final Uri NETWORK_REQUEST_STATUSES = Uri.parse("content://" + AUTHORITY + "/" + WeatherDatabase.NETWORK_REQUEST_STATUSES);

        @InexactContentUri(
                path = WeatherDatabase.NETWORK_REQUEST_STATUSES + "/*",
                name = "NETWORK_REQUEST_STATUSES_ID",
                type = "vnd.android.cursor.item/vnd.net.dragora.mttweather.networkrequeststatus",
                whereColumn = NetworkRequestStatusColumns.ID,
                pathSegment = 1)
        public static Uri withId(long id) {
            return buildUri(WeatherDatabase.NETWORK_REQUEST_STATUSES, String.valueOf(id));
        }
    }
}

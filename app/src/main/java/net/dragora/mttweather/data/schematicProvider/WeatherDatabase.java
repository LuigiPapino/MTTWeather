package net.dragora.mttweather.data.schematicProvider;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;


@Database(version = WeatherDatabase.VERSION)
public final class WeatherDatabase {
    public static final int VERSION = 1;

    @Table(GitHubRepositoryColumns.class) public static final String GITHUB_REPOSITORIES = "repositories";
    @Table(SearchCityColumns.class)
    public static final String CITY_SEARCHES = "citySearches";
    @Table(NetworkRequestStatusColumns.class) public static final String NETWORK_REQUEST_STATUSES = "networkRequestStatuses";
    @Table(UserSettingsColumns.class) public static final String USER_SETTINGS = "userSettings";
}

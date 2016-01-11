package net.dragora.mttweather.data.schematicProvider;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by nietzsche on 10/01/16.
 */
@Database(version = WeatherDatabase.VERSION)
public final class WeatherDatabase {
    public static final int VERSION = 1;

    @Table(CityWeatherColumns.class)
    public static final String CITY_WEATHERS = "cityWeathers";
    @Table(SearchCityColumns.class)
    public static final String CITY_SEARCHES = "citySearches";
    @Table(NetworkRequestStatusColumns.class)
    public static final String NETWORK_REQUEST_STATUSES = "networkRequestStatuses";
    @Table(UserSettingsColumns.class)
    public static final String USER_SETTINGS = "userSettings";
}

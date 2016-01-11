package net.dragora.mttweather.network;

import android.net.Uri;

import net.dragora.mttweather.pojo.search_city.SearchCity;
import net.dragora.mttweather.pojo.weather.CityWeather;
import net.dragora.mttweather.pojo.weather.Weather;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface WeatherService {
    static Uri CITY_SEARCH = Uri.parse("city/search");
    static Uri CITY_WEATHER = Uri.parse("city/weather");

    @GET("/search.ashx")
    Observable<SearchCity> search(@Query("query") String query);

    //q=41.3833,2.1833&showmap=yes&num_of_days=3&mca=no&includelocation=yes
    @GET("/weather.ashx")
    Observable<CityWeather> weather(@Query("q") String query,
                                    @Query("num_of_days") int numOfDays,
                                    @Query("mca") String mca,
                                    @Query("includelocation") String includeLocation);


}

package net.dragora.mttweather.network;

import android.net.Uri;

import net.dragora.mttweather.pojo.GitHubRepository;
import net.dragora.mttweather.pojo.search_city.SearchCity;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import rx.Observable;

public interface WeatherService {
    static Uri CITY_SEARCH = Uri.parse("city/search");
    static Uri REPOSITORY = Uri.parse("github/repository");

    @GET("/search.ashx")
    Observable<SearchCity> search(@Query("query") String query);

    @GET("/repositories/{id}")
    Observable<GitHubRepository> getRepository(@Path("id") Integer id);
}

package net.dragora.mttweather.network;

import android.support.annotation.NonNull;

import net.dragora.mttweather.pojo.search_city.SearchCity;
import net.dragora.mttweather.pojo.weather.CityWeather;
import net.dragora.mttweather.pojo.weather.Weather;

import io.reark.reark.utils.Preconditions;
import retrofit.RestAdapter;
import retrofit.client.Client;
import rx.Observable;

public class NetworkApi {

    private static final String API_KEY = "a8c96e6d726cb7bd73b867681ae88";
    private final WeatherService weatherService;
    public NetworkApi(@NonNull Client client) {
        Preconditions.checkNotNull(client, "Client cannot be null.");

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(client)
                .setEndpoint("http://api.worldweatheronline.com/premium/v1/")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(request -> {
                    request.addQueryParam("key", API_KEY);
                    request.addQueryParam("format", "json");
                })
                .build();
        weatherService = restAdapter.create(WeatherService.class);
    }

    public Observable<SearchCity> search(String query) {
        return weatherService.search(query);
    }

    public Observable<CityWeather> getWeather(String id) {
        return weatherService.weather(id, 7, "no", "yes");
    }
}

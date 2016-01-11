package net.dragora.mttweather.viewmodels;

import net.dragora.mttweather.data.DataLayer;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Pawel Polanski on 5/16/15.
 */
@Module
public class ViewModelModule {

    @Provides
    public CitySearchViewModel provideCitySearchViewModel(DataLayer.GetCitySearch repositorySearch,
                                                          DataLayer.GetUserSettings getUserSettings,
                                                          DataLayer.SetUserSettings setUserSettings) {
        return new CitySearchViewModel(repositorySearch, getUserSettings, setUserSettings);
    }

    @Provides
    public CityListViewModel provideCityListViewModel(DataLayer.GetUserSettings getUserSettings,
                                                      DataLayer.SetUserSettings setUserSettings) {
        return new CityListViewModel(getUserSettings, setUserSettings);
    }

    @Provides
    public WeatherViewModel provideWeatherViewModel(DataLayer.FetchAndGetWeather fetchAndGetGitHubRepository) {
        return new WeatherViewModel(fetchAndGetGitHubRepository);
    }

}

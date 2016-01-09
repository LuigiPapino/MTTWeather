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
    public CitySearchViewModel provideCitySearchViewModel(DataLayer.GetCitySearch repositorySearch) {
        return new CitySearchViewModel(repositorySearch);
    }

    @Provides
    public RepositoryViewModel provideRepositoryViewModel(DataLayer.GetUserSettings getUserSettings,
                                                          DataLayer.FetchAndGetGitHubRepository fetchAndGetGitHubRepository) {
        return new RepositoryViewModel(getUserSettings, fetchAndGetGitHubRepository);
    }

}

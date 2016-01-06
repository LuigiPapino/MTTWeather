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
    public RepositoriesViewModel provideRepositoriesViewModel(DataLayer.GetGitHubRepositorySearch repositorySearch,
                                                              DataLayer.GetGitHubRepository repositoryRepository) {
        return new RepositoriesViewModel(repositorySearch, repositoryRepository);
    }

    @Provides
    public RepositoryViewModel provideRepositoryViewModel(DataLayer.GetUserSettings getUserSettings,
                                                          DataLayer.FetchAndGetGitHubRepository fetchAndGetGitHubRepository) {
        return new RepositoryViewModel(getUserSettings, fetchAndGetGitHubRepository);
    }

}

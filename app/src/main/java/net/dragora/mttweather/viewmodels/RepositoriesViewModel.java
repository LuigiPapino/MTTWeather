package net.dragora.mttweather.viewmodels;

import android.support.annotation.NonNull;

import net.dragora.mttweather.data.DataLayer;
import net.dragora.mttweather.pojo.GitHubRepository;
import net.dragora.mttweather.pojo.GitHubRepositorySearch;
import net.dragora.mttweather.pojo.search_city.SearchCity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reark.reark.data.DataStreamNotification;
import io.reark.reark.utils.Log;
import io.reark.reark.utils.Preconditions;
import io.reark.reark.utils.RxUtils;
import io.reark.reark.viewmodels.AbstractViewModel;

import rx.Observable;
import rx.functions.Func1;
import rx.observables.ConnectableObservable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ttuo on 19/03/14.
 */
public class RepositoriesViewModel extends AbstractViewModel {
    private static final String TAG = RepositoriesViewModel.class.getSimpleName();
    private static final int MAX_REPOSITORIES_DISPLAYED = 5;
    @NonNull
    private final DataLayer.GetCitySearch getCitySearch;
    @NonNull
    private final DataLayer.GetGitHubRepository getGitHubRepository;
    private final PublishSubject<String> searchString = PublishSubject.create();
    private final PublishSubject<GitHubRepository> selectRepository = PublishSubject.create();
    private final BehaviorSubject<List<GitHubRepository>> repositories = BehaviorSubject.create();
    private final BehaviorSubject<ProgressStatus> networkRequestStatusText = BehaviorSubject.create();

    public RepositoriesViewModel(@NonNull DataLayer.GetCitySearch getCitySearch,
                                 @NonNull DataLayer.GetGitHubRepository getGitHubRepository) {
        Preconditions.checkNotNull(getCitySearch,
                "GetCitySearch cannot be null.");
        Preconditions.checkNotNull(getGitHubRepository,
                                   "GetGitHubRepository cannot be null.");

        this.getCitySearch = getCitySearch;
        this.getGitHubRepository = getGitHubRepository;
        Log.v(TAG, "RepositoriesViewModel");
    }

    @NonNull
    static Func1<DataStreamNotification<SearchCity>, ProgressStatus> toProgressStatus() {
        return notification -> {
            if (notification.isFetchingStart()) {
                return ProgressStatus.LOADING;
            } else if (notification.isFetchingError()) {
                return ProgressStatus.ERROR;
            } else {
                return ProgressStatus.IDLE;
            }
        };
    }

    @NonNull
    public Observable<GitHubRepository> getSelectRepository() {
        return selectRepository.asObservable();
    }

    @NonNull
    public Observable<List<GitHubRepository>> getRepositories() {
        return repositories.asObservable();
    }

    @NonNull
    public Observable<ProgressStatus> getNetworkRequestStatusText() {
        return networkRequestStatusText.asObservable();
    }

    public void setSearchString(@NonNull String searchString) {
        Preconditions.checkNotNull(searchString, "SearchString cannot be null.");

        this.searchString.onNext(searchString);
    }

    public void selectRepository(@NonNull GitHubRepository repository) {
        Preconditions.checkNotNull(repository, "Repository cannot be null.");

        this.selectRepository.onNext(repository);
    }

    @Override
    public void subscribeToDataStoreInternal(@NonNull CompositeSubscription compositeSubscription) {
        Log.v(TAG, "subscribeToDataStoreInternal");

        ConnectableObservable<DataStreamNotification<SearchCity>> repositorySearchSource =
                searchString
                          .filter((string) -> string.length() > 2)
                          .throttleLast(500, TimeUnit.MILLISECONDS)
                        .switchMap(getCitySearch::call)
                          .publish();

        compositeSubscription.add(repositorySearchSource
                .map(toProgressStatus())
                .subscribe(this::setNetworkStatusText));

/*     TODO    compositeSubscription.add(repositorySearchSource
                .filter(DataStreamNotification::isOnNext)
                .map(DataStreamNotification::getValue)
                .map(GitHubRepositorySearch::getItems)
                .flatMap(toGitHubRepositoryList())
                .doOnNext(list -> Log.d(TAG, "Publishing " + list.size() + " repositories from the ViewModel"))
                .subscribe(RepositoriesViewModel.this.repositories::onNext));*/

        compositeSubscription.add(repositorySearchSource.connect());
    }

    @NonNull
    Func1<List<Integer>, Observable<List<GitHubRepository>>> toGitHubRepositoryList() {
        return repositoryIds -> Observable.from(repositoryIds)
                .take(MAX_REPOSITORIES_DISPLAYED)
                .map(this::getGitHubRepositoryObservable)
                .toList()
                .flatMap(RxUtils::toObservableList);
    }

    @NonNull
    Observable<GitHubRepository> getGitHubRepositoryObservable(@NonNull Integer repositoryId) {
        Preconditions.checkNotNull(repositoryId, "Repository Id cannot be null.");

        return getGitHubRepository
                .call(repositoryId)
                .doOnNext((repository) -> Log.v(TAG, "Received repository " + repository.getId()));
    }

    void setNetworkStatusText(@NonNull ProgressStatus status) {
        Preconditions.checkNotNull(status, "ProgressStatus cannot be null.");

        networkRequestStatusText.onNext(status);
    }

    public enum ProgressStatus {
        LOADING, ERROR, IDLE
    }
}

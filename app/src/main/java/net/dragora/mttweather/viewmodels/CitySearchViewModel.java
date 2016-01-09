package net.dragora.mttweather.viewmodels;

import android.support.annotation.NonNull;

import net.dragora.mttweather.data.DataLayer;
import net.dragora.mttweather.pojo.GitHubRepository;
import net.dragora.mttweather.pojo.search_city.Result;
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
public class CitySearchViewModel extends AbstractViewModel {
    private static final String TAG = CitySearchViewModel.class.getSimpleName();
    @NonNull
    private final DataLayer.GetCitySearch getCitySearch;
    private final PublishSubject<String> searchString = PublishSubject.create();
    private final PublishSubject<GitHubRepository> selectRepository = PublishSubject.create();
    private final BehaviorSubject<List<Result>> results = BehaviorSubject.create();
    private final BehaviorSubject<ProgressStatus> networkRequestStatusText = BehaviorSubject.create();

    public CitySearchViewModel(@NonNull DataLayer.GetCitySearch getCitySearch
    ) {
        Preconditions.checkNotNull(getCitySearch,
                "GetCitySearch cannot be null.");

        this.getCitySearch = getCitySearch;

        Log.v(TAG, "RepositoriesViewModel");
    }

    @NonNull
    static Func1<DataStreamNotification<SearchCity>, ProgressStatus> toProgressStatus() {
        return notification -> {
            android.util.Log.d(TAG, "toProgressStatus() called with: " + notification + "");
            if (notification.isFetchingStart()) {
                return ProgressStatus.LOADING;
            } else if (notification.isFetchingError()) {
                return ProgressStatus.ERROR;
            } else if (notification.isNoLoading()) {
                return ProgressStatus.IDLE;
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
    public Observable<List<Result>> getResults() {
        return results.asObservable();
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

        ConnectableObservable<DataStreamNotification<SearchCity>> citySearchSource =
                searchString
                        .filter((string) -> string.length() > 2)
                        .throttleLast(500, TimeUnit.MILLISECONDS)
                        .switchMap(getCitySearch::call)
                        .publish();

        compositeSubscription.add(citySearchSource
                .map(toProgressStatus())
                .subscribe(this::setNetworkStatusText));

        compositeSubscription.add(citySearchSource
                .filter(DataStreamNotification::isOnNext)
                .map(DataStreamNotification::getValue)
                .map(SearchCity::getResults)
                .doOnNext(list -> Log.d(TAG, "Publishing " + list.size() + " results from the ViewModel"))
                .subscribe(CitySearchViewModel.this.results::onNext));

        compositeSubscription.add(citySearchSource.connect());
    }

    void setNetworkStatusText(@NonNull ProgressStatus status) {
        Preconditions.checkNotNull(status, "ProgressStatus cannot be null.");

        networkRequestStatusText.onNext(status);
    }


    public enum ProgressStatus {
        LOADING, ERROR, IDLE
    }
}

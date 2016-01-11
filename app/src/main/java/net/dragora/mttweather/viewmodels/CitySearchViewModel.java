package net.dragora.mttweather.viewmodels;

import android.support.annotation.NonNull;

import net.dragora.mttweather.data.DataLayer;
import net.dragora.mttweather.pojo.UserSettings;
import net.dragora.mttweather.pojo.search_city.Result;
import net.dragora.mttweather.pojo.search_city.SearchCity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reark.reark.data.DataStreamNotification;
import io.reark.reark.utils.Log;
import io.reark.reark.utils.Preconditions;
import io.reark.reark.viewmodels.AbstractViewModel;
import rx.Observable;
import rx.functions.Func1;
import rx.observables.ConnectableObservable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;


public class CitySearchViewModel extends AbstractViewModel {
    private static final String TAG = CitySearchViewModel.class.getSimpleName();
    private final DataLayer.GetCitySearch getCitySearch;
    private final DataLayer.GetUserSettings getUserSettings;
    private final DataLayer.SetUserSettings setUserSettings;

    private final PublishSubject<String> searchString = PublishSubject.create();
    private final PublishSubject<UserSettings> userSettingsPublishSubject = PublishSubject.create();
    private final BehaviorSubject<List<Result>> results = BehaviorSubject.create();
    private final BehaviorSubject<ProgressStatus> networkRequestStatusText = BehaviorSubject.create();

    private BehaviorSubject<UserSettings> userSettings = BehaviorSubject.create();

    public CitySearchViewModel(@NonNull DataLayer.GetCitySearch getCitySearch,
                               @NonNull DataLayer.GetUserSettings getUserSettings,
                               @NonNull DataLayer.SetUserSettings setUserSettings
    ) {
        Preconditions.checkNotNull(getCitySearch,
                "GetCitySearch cannot be null.");
        Preconditions.checkNotNull(getUserSettings,
                "getUserSettings cannot be null.");
        Preconditions.checkNotNull(setUserSettings,
                "setUserSettings cannot be null.");
        this.getCitySearch = getCitySearch;
        this.getUserSettings = getUserSettings;
        this.setUserSettings = setUserSettings;

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
    public Observable<List<Result>> getResults() {
        return results.asObservable();
    }


    @NonNull
    public PublishSubject<UserSettings> getUserSettingsPublishSubject() {
        return userSettingsPublishSubject;
    }
    @NonNull
    public Observable<UserSettings> getUserSettings() {
        return userSettings.asObservable();
    }

    @NonNull
    public Observable<ProgressStatus> getNetworkRequestStatusText() {
        return networkRequestStatusText.asObservable();
    }

    public void setSearchString(@NonNull String searchString) {
        Preconditions.checkNotNull(searchString, "SearchString cannot be null.");

        this.searchString.onNext(searchString);
    }


    @Override
    public void subscribeToDataStoreInternal(@NonNull CompositeSubscription compositeSubscription) {
        Log.v(TAG, "subscribeToDataStoreInternal");

        ConnectableObservable<DataStreamNotification<SearchCity>> citySearchSource =
                searchString
                        .filter((string) -> string.length() > 2)
                        .throttleLast(500, TimeUnit.MILLISECONDS) //TODO hard to test
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

        compositeSubscription.add(getUserSettings.call()
                .subscribe(CitySearchViewModel.this.userSettings::onNext));

        compositeSubscription.add(userSettingsPublishSubject.subscribe(
                setUserSettings::call
        ));
    }

    void setNetworkStatusText(@NonNull ProgressStatus status) {
        Preconditions.checkNotNull(status, "ProgressStatus cannot be null.");

        networkRequestStatusText.onNext(status);
    }


    public enum ProgressStatus {
        LOADING, ERROR, IDLE
    }
}

package net.dragora.mttweather.viewmodels;

import android.support.annotation.NonNull;

import net.dragora.mttweather.data.DataLayer;
import net.dragora.mttweather.pojo.weather.CityWeather;
import net.dragora.mttweather.pojo.weather.Weather;

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


public class WeatherViewModel extends AbstractViewModel {
    private static final String TAG = WeatherViewModel.class.getSimpleName();

    private final DataLayer.FetchAndGetWeather fetchAndGetWeather;

    private final BehaviorSubject<CityWeather> weather = BehaviorSubject.create();

    private final PublishSubject<String> weatherId = PublishSubject.create();
    private final BehaviorSubject<ProgressStatus> networkRequestStatusText = BehaviorSubject.create();

    public WeatherViewModel(@NonNull DataLayer.FetchAndGetWeather fetchAndGetWeather) {
        Preconditions.checkNotNull(fetchAndGetWeather,
                "Fetch And Get Weather cannot be null.");

        this.fetchAndGetWeather = fetchAndGetWeather;
        Log.v(TAG, "WeatherViewModel");
    }

    @NonNull
    static Func1<DataStreamNotification<CityWeather>, ProgressStatus> toProgressStatus() {
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

    void setNetworkStatusText(@NonNull ProgressStatus status) {
        Preconditions.checkNotNull(status, "ProgressStatus cannot be null.");

        networkRequestStatusText.onNext(status);
    }

    public void setWeatherId(@NonNull String weatherId) {
        Preconditions.checkNotNull(weatherId, "weatherId cannot be null.");

        this.weatherId.onNext(weatherId);
    }

    @NonNull
    public Observable<ProgressStatus> getNetworkRequestStatusText() {
        return networkRequestStatusText.asObservable();
    }
    @Override
    public void subscribeToDataStoreInternal(@NonNull CompositeSubscription compositeSubscription) {
        android.util.Log.d(TAG, "subscribeToDataStoreInternal() called with: " + "compositeSubscription = [" + compositeSubscription + "]");
        ConnectableObservable<DataStreamNotification<CityWeather>> citySearchSource =
                weatherId
                        .switchMap(fetchAndGetWeather::call)
                        .publish();

        compositeSubscription.add(citySearchSource
                .map(toProgressStatus())
                .subscribe(this::setNetworkStatusText));

        compositeSubscription.add(citySearchSource
                .filter(DataStreamNotification::isOnNext)
                .map(DataStreamNotification::getValue)
                .doOnNext(weather -> Log.d(TAG, "Publishing " + weather.getId() + " from the ViewModel"))
                .subscribe(
                        WeatherViewModel.this.weather::onNext)
        );

        compositeSubscription.add(citySearchSource.connect());


    }

    @NonNull
    public Observable<CityWeather> getWeather() {
        return weather.asObservable();
    }

    public enum ProgressStatus {
        LOADING, ERROR, IDLE
    }
}

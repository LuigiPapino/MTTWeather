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
import jonathanfinerty.once.Once;
import rx.Observable;
import rx.functions.Func1;
import rx.observables.ConnectableObservable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;


public class CityListViewModel extends AbstractViewModel {
    private static final String TAG = CityListViewModel.class.getSimpleName();

    private final DataLayer.GetUserSettings getUserSettings;
    private final DataLayer.SetUserSettings setUserSettings;

    private final PublishSubject<UserSettings> userSettingsPublishSubject = PublishSubject.create();
    private BehaviorSubject<UserSettings> userSettings = BehaviorSubject.create();

    public CityListViewModel(
            @NonNull DataLayer.GetUserSettings getUserSettings,
            @NonNull DataLayer.SetUserSettings setUserSettings
    ) {

        Preconditions.checkNotNull(getUserSettings,
                "getUserSettings cannot be null.");
        Preconditions.checkNotNull(setUserSettings,
                "setUserSettings cannot be null.");
        this.getUserSettings = getUserSettings;
        this.setUserSettings = setUserSettings;


        Log.v(TAG, "RepositoriesViewModel");
    }


    @NonNull
    public PublishSubject<UserSettings> getUserSettingsPublishSubject() {
        return userSettingsPublishSubject;
    }

    @NonNull
    public Observable<UserSettings> getUserSettings() {
        return userSettings.asObservable();
    }


    @Override
    public void subscribeToDataStoreInternal(@NonNull CompositeSubscription compositeSubscription) {
        Log.v(TAG, "subscribeToDataStoreInternal");


        compositeSubscription.add(getUserSettings.call()
                .subscribe(CityListViewModel.this.userSettings::onNext));

        compositeSubscription.add(userSettingsPublishSubject.subscribe(
                setUserSettings::call
        ));
    }


}

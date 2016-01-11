package net.dragora.mttweather.ui.city_weather;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import net.dragora.mttweather.R;
import net.dragora.mttweather.pojo.weather.CityWeather;
import net.dragora.mttweather.pojo.weather.CurrentCondition;
import net.dragora.mttweather.pojo.weather.Weather;
import net.dragora.mttweather.viewmodels.CitySearchViewModel;
import net.dragora.mttweather.viewmodels.WeatherViewModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.ViewById;

import io.reark.reark.utils.Preconditions;
import io.reark.reark.utils.RxViewBinder;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by nietzsche on 10/01/16.
 */
@EViewGroup(R.layout.city_weather_view)
public class CityWeatherView extends RelativeLayout {
    private static final String TAG = CityWeatherView.class.getSimpleName();
    private final PublishSubject<String> weatherIdPublishSubject = PublishSubject.create();
    @ViewById
    UltimateRecyclerView ultimateRecyclerView;
    @ViewById
    ImageView currentIcon;
    @ViewById
    TextView currentTemp;
    @ViewById
    TextView currentArea;
    @ViewById
    TextView currentTempFeel;
    @ViewById
    TextView currentVisibility;
    @ViewById
    TextView currentWindSpeed;
    @ViewById
    TextView currentPrecipitations;
    @ViewById
    TextView currentHumidity;
    @ViewById
    TextView currentPressure;
    @ViewById
    RelativeLayout currentLayout;
    @ViewById
    ProgressBar progressBar;
    @Bean
    ForecastAdapter adapter;
    @SystemService
    LayoutInflater layoutInflater;
    Snackbar snackbar;
    private CityWeather weather;

    public CityWeatherView(Context context) {
        super(context);
    }

    public CityWeatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CityWeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @AfterViews
    protected void afterViews() {

        ultimateRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ultimateRecyclerView.setAdapter(adapter);

    }

    private void setWeather(@NonNull CityWeather weather) {
        this.weather = weather;
        adapter.setItems(weather.getDatum().getWeathers());
        CurrentCondition currentCondition = weather.getDatum().getCurrentConditions().get(0);
        Glide.with(getContext())
                .load(currentCondition.getWeatherIconUrls().get(0).getValue())
                .crossFade()
                .into(currentIcon);
        currentTemp.setText(getContext().getString(R.string.temp, currentCondition.getTempC()));
        currentArea.setText(weather.getDatum().getNearestAreas().get(0).getAreaNames().get(0).getValue());
        currentTempFeel.setText(getContext().getString(R.string.feels_like, currentCondition.getFeelsLikeC()));
        currentVisibility.setText(getContext().getString(R.string.visibility, currentCondition.getVisibility()));
        currentWindSpeed.setText(getContext().getString(R.string.wind_speed, currentCondition.getWindspeedKmph()));
        currentPrecipitations.setText(getContext().getString(R.string.precipitations_mm, currentCondition.getPrecipMM()));
        currentHumidity.setText(getContext().getString(R.string.humidity, currentCondition.getHumidity()));
        currentPressure.setText(getContext().getString(R.string.pressure, currentCondition.getPressure()));

    }

    private void setNetworkRequestStatus(@NonNull WeatherViewModel.ProgressStatus networkRequestStatus) {
        Preconditions.checkNotNull(networkRequestStatus, "Network Request Status cannot be null.");
        Log.d(TAG, "setNetworkRequestStatus() called with: " + "networkRequestStatus = [" + networkRequestStatus + "]");

        String networkStatusText = "";
        progressBar.setVisibility(INVISIBLE);
        progressBar.setIndeterminate(false); //useful with espresso


        switch (networkRequestStatus) {

            case LOADING:
                networkStatusText = "";
                progressBar.setVisibility(VISIBLE);
                progressBar.setIndeterminate(true);
                break;
            case ERROR:
                networkStatusText = getContext().getString(R.string.error_occured);
                progressBar.setVisibility(INVISIBLE);
                progressBar.setIndeterminate(false);
                break;
            case IDLE:
                networkStatusText = "";
                progressBar.setVisibility(INVISIBLE);
                progressBar.setIndeterminate(false); //useful with espresso
                break;
        }
        setNetworkRequestStatusText(networkStatusText);
    }

    private void setNetworkRequestStatusText(@NonNull String networkRequestStatusText) {
        Preconditions.checkNotNull(networkRequestStatusText, "Network Status Text cannot be null.");
        if (!TextUtils.isEmpty(networkRequestStatusText)) {
            if (snackbar != null)
                snackbar.dismiss();

            snackbar = Snackbar.make(this, R.string.error_occured, Snackbar.LENGTH_LONG)
                    .setAction(R.string.ok, v -> {
                        snackbar.dismiss();
                    });
            snackbar.show();
        }
    }

    public void setWeatherId(@NonNull String weatherId) {
        weatherIdPublishSubject.onNext(weatherId);
    }

    public static class ViewBinder extends RxViewBinder {
        private CityWeatherView view;
        private WeatherViewModel viewModel;

        public ViewBinder(@NonNull final CityWeatherView view,
                          @NonNull final WeatherViewModel viewModel) {
            Preconditions.checkNotNull(view, "View cannot be null.");
            Preconditions.checkNotNull(viewModel, "ViewModel cannot be null.");

            this.view = view;
            this.viewModel = viewModel;
        }

        @Override
        protected void bindInternal(@NonNull final CompositeSubscription s) {
            s.add(viewModel.getWeather()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(view::setWeather));
            s.add(viewModel.getNetworkRequestStatusText()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(view::setNetworkRequestStatus));
            s.add(view.weatherIdPublishSubject
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(viewModel::setWeatherId));
        }

    }
}

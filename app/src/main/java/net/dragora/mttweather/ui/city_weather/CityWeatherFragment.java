package net.dragora.mttweather.ui.city_weather;

import android.support.v7.widget.Toolbar;

import net.dragora.mttweather.MyApplication;
import net.dragora.mttweather.R;
import net.dragora.mttweather.ui.BaseFragment;
import net.dragora.mttweather.ui.city_list.CityListActivity;
import net.dragora.mttweather.ui.search.SearchCityView;
import net.dragora.mttweather.utils.ApplicationInstrumentation;
import net.dragora.mttweather.viewmodels.WeatherViewModel;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import javax.inject.Inject;

/**
 * A fragment representing a single City detail screen.
 * This fragment is either contained in a {@link CityListActivity}
 * in two-pane mode (on tablets) or a {@link CityWeatherActivity}
 * on handsets.
 */
@EFragment(R.layout.city_weather_fragment)
public class CityWeatherFragment extends BaseFragment {


    @ViewById
    CityWeatherView cityWeather;
    @Inject
    WeatherViewModel viewModel;
    @Inject
    ApplicationInstrumentation mInstrumentation;
    @ViewById
    Toolbar detailToolbar;
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    @FragmentArg
    String weatherId = "";
    private CityWeatherView.ViewBinder viewBinder;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CityWeatherFragment() {
    }


    @AfterViews
    void afterViews() {
        MyApplication.getInstance().getGraph().inject(this);
        if (detailToolbar != null)
            detailToolbar.setTitle(R.string.city_weather);

        viewBinder = new CityWeatherView.ViewBinder(cityWeather, viewModel);
        viewModel.subscribeToDataStore();


    }

    @Override
    public void onResume() {
        super.onResume();
        viewBinder.bind();
        cityWeather.setWeatherId(weatherId);

    }

    @Override
    public void onPause() {
        super.onPause();
        viewBinder.unbind();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.unsubscribeFromDataStore();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.dispose();
        viewModel = null;
        mInstrumentation.getLeakTracing().traceLeakage(this);
    }
}

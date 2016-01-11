package net.dragora.mttweather.injections;

import android.app.Application;

import net.dragora.mttweather.MyApplication;
import net.dragora.mttweather.data.DataStoreModule;
import net.dragora.mttweather.network.NetworkService;
import net.dragora.mttweather.ui.city_list.CityListFragment;
import net.dragora.mttweather.ui.city_weather.CityWeatherFragment;
import net.dragora.mttweather.ui.search.SearchCityFragment;
import net.dragora.mttweather.viewmodels.ViewModelModule;
import net.dragora.mttweather.widget.WidgetService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by pt2121 on 2/20/15.
 */
@Singleton
@Component(modules = {ApplicationModule.class, DataStoreModule.class, ViewModelModule.class,
                      DebugInstrumentationModule.class})
public interface Graph {


    void inject(MyApplication myApplication);

    void inject(NetworkService networkService);

    void inject(WidgetService widgetService);

    void inject(SearchCityFragment searchCityFragment);

    void inject(CityListFragment cityListFragment);

    void inject(CityWeatherFragment cityWeatherFragment);

    final class Initializer {

        public static Graph init(Application application) {
            return DaggerGraph.builder()
                    .applicationModule(new ApplicationModule(application))
                    .build();
        }

    }
}

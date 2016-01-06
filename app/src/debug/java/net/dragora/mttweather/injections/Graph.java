package net.dragora.mttweather.injections;

import android.app.Application;

import net.dragora.mttweather.MyApplication;
import net.dragora.mttweather.data.DataStoreModule;
import net.dragora.mttweather.network.NetworkService;
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

    final class Initializer {

        public static Graph init(Application application) {
            return DaggerGraph.builder()
                    .applicationModule(new ApplicationModule(application))
                    .build();
        }

    }
}

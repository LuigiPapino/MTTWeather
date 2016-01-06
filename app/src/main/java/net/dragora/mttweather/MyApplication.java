package net.dragora.mttweather;

import android.app.Application;
import android.support.annotation.NonNull;

import net.dragora.mttweather.injections.Graph;
import net.dragora.mttweather.utils.ApplicationInstrumentation;

import javax.inject.Inject;

/**
 * Created by nietzsche on 06/01/16.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    private Graph mGraph;

    @Inject
    ApplicationInstrumentation instrumentation;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mGraph = Graph.Initializer.init(this);
        getGraph().inject(this);

        instrumentation.init();
    }

    @NonNull
    public static MyApplication getInstance() {
        return instance;
    }

    @NonNull
    public Graph getGraph() {
        return mGraph;
    }
}

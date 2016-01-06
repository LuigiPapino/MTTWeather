package net.dragora.mttweather.injections;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import net.dragora.mttweather.network.NetworkInstrumentation;
import net.dragora.mttweather.utils.ApplicationInstrumentation;
import net.dragora.mttweather.utils.DebugApplicationInstrumentation;
import net.dragora.mttweather.utils.LeakCanaryTracing;
import net.dragora.mttweather.utils.LeakTracing;
import net.dragora.mttweather.utils.StethoInstrumentation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Pawel Polanski on 4/24/15.
 */
@Module
public class DebugInstrumentationModule {

    @Provides
    @Singleton
    public ApplicationInstrumentation providesInstrumentation(LeakTracing leakTracing, StethoInstrumentation instrumentation) {
        return new DebugApplicationInstrumentation(leakTracing, instrumentation);
    }

    @Provides
    @Singleton
    public LeakTracing providesLeakTracing(Application application) {
        return new LeakCanaryTracing(application);
    }

    @Provides
    @Singleton
    public StethoInstrumentation providesStethoInstrumentation(@ForApplication Context context,
                                                               Interceptor interceptor) {
        return new StethoInstrumentation(context, interceptor);
    }

    @Provides
    @Singleton
    public NetworkInstrumentation<OkHttpClient> providesNetworkInstrumentation(StethoInstrumentation instrumentation) {
        return instrumentation;
    }

    @Provides
    @Singleton
    public Interceptor providesStethoIntercetor() {
        return new StethoInterceptor();
    }

}

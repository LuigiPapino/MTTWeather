package net.dragora.mttweather.utils;

import android.support.annotation.NonNull;

public interface ApplicationInstrumentation extends Instrumentation
{

    @NonNull
    LeakTracing getLeakTracing();

}

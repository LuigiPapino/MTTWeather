package net.dragora.mttweather.network;

import android.support.annotation.NonNull;

import net.dragora.mttweather.utils.Instrumentation;


public interface NetworkInstrumentation<T> extends Instrumentation
{

    @NonNull
    T decorateNetwork(@NonNull final T httpClient);

}

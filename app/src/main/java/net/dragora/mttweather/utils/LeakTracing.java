package net.dragora.mttweather.utils;

/**
 * Created by Pawel Polanski on 5/9/15.
 */
public interface LeakTracing extends Instrumentation {

    void traceLeakage(Object reference);

}

package io.reark.reark.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reark.reark.utils.Preconditions;

/**
 * Created by ttuo on 06/05/15.
 */
public class DataStreamNotification<T> {


    @NonNull
    final private Type type;
    final private T value;
    final private Throwable error;
    private DataStreamNotification(@NonNull Type type, T value, Throwable error) {
        Preconditions.checkNotNull(type, "Type cannot be null.");

        this.type = type;
        this.value = value;
        this.error = error;
    }

    @NonNull
    public static<T> DataStreamNotification<T> fetchingStart() {
        return new DataStreamNotification<>(Type.FETCHING_START, null, null);
    }

    @NonNull
    public static<T> DataStreamNotification<T> onNext(T value) {
        return new DataStreamNotification<>(Type.ON_NEXT, value, null);
    }

    public static <T> DataStreamNotification<T> noLoading() {
        return new DataStreamNotification<>(Type.NO_LOADING, null, null);
    }

    @NonNull
    public static<T> DataStreamNotification<T> fetchingError() {
        return new DataStreamNotification<>(Type.FETCHING_ERROR, null, null);
    }

    @Nullable
    public T getValue() {
        return value;
    }

    public boolean isFetchingStart() {
        return type.equals(Type.FETCHING_START);
    }

    public boolean isOnNext() {
        return type.equals(Type.ON_NEXT);
    }

    public boolean isNoLoading() {
        return type.equals(Type.NO_LOADING);
    }

    public boolean isFetchingError() {
        return type.equals(Type.FETCHING_ERROR);
    }

    @Nullable
    public Throwable getError() {
        return error;
    }

    @Override
    public String toString() {
        return "DataStreamNotification{" +
                "type=" + type +
                ", value=" + value +
                ", error=" + error +
                '}';
    }


    private enum Type {
        FETCHING_START, FETCHING_ERROR, ON_NEXT, NO_LOADING
    }
}

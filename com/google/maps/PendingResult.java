package com.google.maps;
/* loaded from: classes2.dex */
public interface PendingResult<T> {

    /* loaded from: classes2.dex */
    public interface Callback<T> {
        void onFailure(Throwable th);

        void onResult(T t2);
    }

    T await();

    T awaitIgnoreError();

    void cancel();

    void setCallback(Callback<T> callback);
}

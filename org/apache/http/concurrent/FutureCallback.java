package org.apache.http.concurrent;
/* loaded from: classes3.dex */
public interface FutureCallback<T> {
    void cancelled();

    void completed(T t2);

    void failed(Exception exc);
}

package org.apache.http.pool;
/* loaded from: classes3.dex */
public interface ConnPoolControl<T> {
    int getDefaultMaxPerRoute();

    int getMaxPerRoute(T t2);

    int getMaxTotal();

    PoolStats getStats(T t2);

    PoolStats getTotalStats();

    void setDefaultMaxPerRoute(int i2);

    void setMaxPerRoute(T t2, int i2);

    void setMaxTotal(int i2);
}

package com.google.api.client.http;

import com.google.api.client.util.Beta;
@Beta
@Deprecated
/* loaded from: classes2.dex */
public interface BackOffPolicy {
    public static final long STOP = -1;

    long getNextBackOffMillis();

    boolean isBackOffRequired(int i2);

    void reset();
}

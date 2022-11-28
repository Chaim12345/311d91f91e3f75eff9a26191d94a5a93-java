package io.opencensus.contrib.http;

import javax.annotation.Nullable;
/* loaded from: classes3.dex */
public abstract class HttpExtractor<Q, P> {
    @Nullable
    public abstract String getHost(Q q2);

    @Nullable
    public abstract String getMethod(Q q2);

    @Nullable
    public abstract String getPath(Q q2);

    @Nullable
    public abstract String getRoute(Q q2);

    public abstract int getStatusCode(@Nullable P p2);

    @Nullable
    public abstract String getUrl(Q q2);

    @Nullable
    public abstract String getUserAgent(Q q2);
}

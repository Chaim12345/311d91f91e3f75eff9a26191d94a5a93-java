package com.google.api.client.http;

import java.util.Arrays;
import java.util.logging.Logger;
/* loaded from: classes2.dex */
public abstract class HttpTransport {
    private static final String[] SUPPORTED_METHODS;

    /* renamed from: a  reason: collision with root package name */
    static final Logger f8050a = Logger.getLogger(HttpTransport.class.getName());

    static {
        String[] strArr = {"DELETE", "GET", "POST", "PUT"};
        SUPPORTED_METHODS = strArr;
        Arrays.sort(strArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpRequest a() {
        return new HttpRequest(this, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract LowLevelHttpRequest buildRequest(String str, String str2);

    public final HttpRequestFactory createRequestFactory() {
        return createRequestFactory(null);
    }

    public final HttpRequestFactory createRequestFactory(HttpRequestInitializer httpRequestInitializer) {
        return new HttpRequestFactory(this, httpRequestInitializer);
    }

    public void shutdown() {
    }

    public boolean supportsMethod(String str) {
        return Arrays.binarySearch(SUPPORTED_METHODS, str) >= 0;
    }
}

package org.apache.http;

import org.apache.http.protocol.HttpContext;
/* loaded from: classes3.dex */
public interface HttpRequestInterceptor {
    void process(HttpRequest httpRequest, HttpContext httpContext);
}

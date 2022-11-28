package org.apache.http;

import org.apache.http.protocol.HttpContext;
/* loaded from: classes3.dex */
public interface HttpResponseInterceptor {
    void process(HttpResponse httpResponse, HttpContext httpContext);
}

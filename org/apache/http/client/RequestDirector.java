package org.apache.http.client;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;
@Deprecated
/* loaded from: classes3.dex */
public interface RequestDirector {
    HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext);
}

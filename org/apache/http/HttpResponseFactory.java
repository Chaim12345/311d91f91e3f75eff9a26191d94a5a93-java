package org.apache.http;

import org.apache.http.protocol.HttpContext;
/* loaded from: classes3.dex */
public interface HttpResponseFactory {
    HttpResponse newHttpResponse(ProtocolVersion protocolVersion, int i2, HttpContext httpContext);

    HttpResponse newHttpResponse(StatusLine statusLine, HttpContext httpContext);
}

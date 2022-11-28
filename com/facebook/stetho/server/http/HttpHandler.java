package com.facebook.stetho.server.http;

import com.facebook.stetho.server.SocketLike;
/* loaded from: classes.dex */
public interface HttpHandler {
    boolean handleRequest(SocketLike socketLike, LightHttpRequest lightHttpRequest, LightHttpResponse lightHttpResponse);
}

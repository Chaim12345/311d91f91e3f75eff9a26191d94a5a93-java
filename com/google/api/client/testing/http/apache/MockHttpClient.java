package com.google.api.client.testing.http.apache;

import com.google.api.client.util.Beta;
import com.google.api.client.util.Preconditions;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.RequestDirector;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
@Beta
/* loaded from: classes2.dex */
public class MockHttpClient extends DefaultHttpClient {

    /* renamed from: a  reason: collision with root package name */
    int f8068a;

    @Override // org.apache.http.impl.client.AbstractHttpClient
    protected RequestDirector createClientRequestDirector(HttpRequestExecutor httpRequestExecutor, ClientConnectionManager clientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy, HttpRoutePlanner httpRoutePlanner, HttpProcessor httpProcessor, HttpRequestRetryHandler httpRequestRetryHandler, RedirectHandler redirectHandler, AuthenticationHandler authenticationHandler, AuthenticationHandler authenticationHandler2, UserTokenHandler userTokenHandler, HttpParams httpParams) {
        return new RequestDirector() { // from class: com.google.api.client.testing.http.apache.MockHttpClient.1
            @Override // org.apache.http.client.RequestDirector
            @Beta
            public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
                return new BasicHttpResponse(HttpVersion.HTTP_1_1, MockHttpClient.this.f8068a, (String) null);
            }
        };
    }

    public final int getResponseCode() {
        return this.f8068a;
    }

    public MockHttpClient setResponseCode(int i2) {
        Preconditions.checkArgument(i2 >= 0);
        this.f8068a = i2;
        return this;
    }
}

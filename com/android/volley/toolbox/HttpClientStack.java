package com.android.volley.toolbox;

import com.android.volley.Request;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
@Deprecated
/* loaded from: classes.dex */
public class HttpClientStack implements HttpStack {
    private static final String HEADER_CONTENT_TYPE = "Content-Type";

    /* renamed from: a  reason: collision with root package name */
    protected final HttpClient f4547a;

    /* loaded from: classes.dex */
    public static final class HttpPatch extends HttpEntityEnclosingRequestBase {
        public static final String METHOD_NAME = "PATCH";

        public HttpPatch() {
        }

        public HttpPatch(String str) {
            setURI(URI.create(str));
        }

        public HttpPatch(URI uri) {
            setURI(uri);
        }

        @Override // org.apache.http.client.methods.HttpRequestBase, org.apache.http.client.methods.HttpUriRequest
        public String getMethod() {
            return "PATCH";
        }
    }

    public HttpClientStack(HttpClient httpClient) {
        this.f4547a = httpClient;
    }

    static HttpUriRequest a(Request request, Map map) {
        switch (request.getMethod()) {
            case -1:
                byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    HttpPost httpPost = new HttpPost(request.getUrl());
                    httpPost.addHeader("Content-Type", request.getPostBodyContentType());
                    httpPost.setEntity(new ByteArrayEntity(postBody));
                    return httpPost;
                }
                return new HttpGet(request.getUrl());
            case 0:
                return new HttpGet(request.getUrl());
            case 1:
                HttpPost httpPost2 = new HttpPost(request.getUrl());
                httpPost2.addHeader("Content-Type", request.getBodyContentType());
                setEntityIfNonEmptyBody(httpPost2, request);
                return httpPost2;
            case 2:
                HttpPut httpPut = new HttpPut(request.getUrl());
                httpPut.addHeader("Content-Type", request.getBodyContentType());
                setEntityIfNonEmptyBody(httpPut, request);
                return httpPut;
            case 3:
                return new HttpDelete(request.getUrl());
            case 4:
                return new HttpHead(request.getUrl());
            case 5:
                return new HttpOptions(request.getUrl());
            case 6:
                return new HttpTrace(request.getUrl());
            case 7:
                HttpPatch httpPatch = new HttpPatch(request.getUrl());
                httpPatch.addHeader("Content-Type", request.getBodyContentType());
                setEntityIfNonEmptyBody(httpPatch, request);
                return httpPatch;
            default:
                throw new IllegalStateException("Unknown request method.");
        }
    }

    private static List<NameValuePair> getPostParameterPairs(Map<String, String> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (String str : map.keySet()) {
            arrayList.add(new BasicNameValuePair(str, map.get(str)));
        }
        return arrayList;
    }

    private static void setEntityIfNonEmptyBody(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, Request<?> request) {
        byte[] body = request.getBody();
        if (body != null) {
            httpEntityEnclosingRequestBase.setEntity(new ByteArrayEntity(body));
        }
    }

    private static void setHeaders(HttpUriRequest httpUriRequest, Map<String, String> map) {
        for (String str : map.keySet()) {
            httpUriRequest.setHeader(str, map.get(str));
        }
    }

    protected void b(HttpUriRequest httpUriRequest) {
    }

    @Override // com.android.volley.toolbox.HttpStack
    public org.apache.http.HttpResponse performRequest(Request<?> request, Map<String, String> map) {
        HttpUriRequest a2 = a(request, map);
        setHeaders(a2, map);
        setHeaders(a2, request.getHeaders());
        b(a2);
        HttpParams params = a2.getParams();
        int timeoutMs = request.getTimeoutMs();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, timeoutMs);
        return this.f4547a.execute(a2);
    }
}

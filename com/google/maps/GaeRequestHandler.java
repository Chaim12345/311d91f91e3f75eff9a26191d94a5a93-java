package com.google.maps;

import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.gson.FieldNamingPolicy;
import com.google.maps.GeoApiContext;
import com.google.maps.internal.ApiResponse;
import com.google.maps.internal.ExceptionsAllowedToRetry;
import com.google.maps.internal.GaePendingResult;
import com.google.maps.internal.HttpHeaders;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes2.dex */
public class GaeRequestHandler implements GeoApiContext.RequestHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GaeRequestHandler.class.getName());
    private final URLFetchService client = URLFetchServiceFactory.getURLFetchService();

    /* loaded from: classes2.dex */
    public static class Builder implements GeoApiContext.RequestHandler.Builder {
        @Override // com.google.maps.GeoApiContext.RequestHandler.Builder
        public GeoApiContext.RequestHandler build() {
            return new GaeRequestHandler();
        }

        @Override // com.google.maps.GeoApiContext.RequestHandler.Builder
        public Builder connectTimeout(long j2, TimeUnit timeUnit) {
            throw new RuntimeException("connectTimeout not implemented for Google App Engine");
        }

        @Override // com.google.maps.GeoApiContext.RequestHandler.Builder
        public Builder proxy(Proxy proxy) {
            throw new RuntimeException("setProxy not implemented for Google App Engine");
        }

        @Override // com.google.maps.GeoApiContext.RequestHandler.Builder
        public Builder proxyAuthentication(String str, String str2) {
            throw new RuntimeException("setProxyAuthentication not implemented for Google App Engine");
        }

        @Override // com.google.maps.GeoApiContext.RequestHandler.Builder
        public Builder queriesPerSecond(int i2) {
            throw new RuntimeException("queriesPerSecond not implemented for Google App Engine");
        }

        @Override // com.google.maps.GeoApiContext.RequestHandler.Builder
        public Builder readTimeout(long j2, TimeUnit timeUnit) {
            throw new RuntimeException("readTimeout not implemented for Google App Engine");
        }

        @Override // com.google.maps.GeoApiContext.RequestHandler.Builder
        public Builder writeTimeout(long j2, TimeUnit timeUnit) {
            throw new RuntimeException("writeTimeout not implemented for Google App Engine");
        }
    }

    GaeRequestHandler() {
    }

    @Override // com.google.maps.GeoApiContext.RequestHandler
    public <T, R extends ApiResponse<T>> PendingResult<T> handle(String str, String str2, String str3, String str4, Class<R> cls, FieldNamingPolicy fieldNamingPolicy, long j2, Integer num, ExceptionsAllowedToRetry exceptionsAllowedToRetry) {
        FetchOptions withDeadline = FetchOptions.Builder.withDeadline(10.0d);
        try {
            HTTPRequest hTTPRequest = new HTTPRequest(new URL(str + str2), HTTPMethod.POST, withDeadline);
            if (str4 != null) {
                hTTPRequest.setHeader(new HTTPHeader(HttpHeaders.X_GOOG_MAPS_EXPERIENCE_ID, str4));
            }
            return new GaePendingResult(hTTPRequest, this.client, cls, fieldNamingPolicy, j2, num, exceptionsAllowedToRetry);
        } catch (MalformedURLException e2) {
            LOG.error("Request: {}{}", str, str2, e2);
            throw new RuntimeException(e2);
        }
    }

    @Override // com.google.maps.GeoApiContext.RequestHandler
    public <T, R extends ApiResponse<T>> PendingResult<T> handlePost(String str, String str2, String str3, String str4, String str5, Class<R> cls, FieldNamingPolicy fieldNamingPolicy, long j2, Integer num, ExceptionsAllowedToRetry exceptionsAllowedToRetry) {
        FetchOptions withDeadline = FetchOptions.Builder.withDeadline(10.0d);
        try {
            HTTPRequest hTTPRequest = new HTTPRequest(new URL(str + str2), HTTPMethod.POST, withDeadline);
            hTTPRequest.setHeader(new HTTPHeader("Content-Type", "application/json; charset=utf-8"));
            if (str5 != null) {
                hTTPRequest.setHeader(new HTTPHeader(HttpHeaders.X_GOOG_MAPS_EXPERIENCE_ID, str5));
            }
            hTTPRequest.setPayload(str3.getBytes(StandardCharsets.UTF_8));
            return new GaePendingResult(hTTPRequest, this.client, cls, fieldNamingPolicy, j2, num, exceptionsAllowedToRetry);
        } catch (MalformedURLException e2) {
            LOG.error("Request: {}{}", str, str2, e2);
            throw new RuntimeException(e2);
        }
    }

    @Override // com.google.maps.GeoApiContext.RequestHandler
    public void shutdown() {
    }
}

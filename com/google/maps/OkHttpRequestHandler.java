package com.google.maps;

import com.google.gson.FieldNamingPolicy;
import com.google.maps.GeoApiContext;
import com.google.maps.internal.ApiResponse;
import com.google.maps.internal.ExceptionsAllowedToRetry;
import com.google.maps.internal.HttpHeaders;
import com.google.maps.internal.OkHttpPendingResult;
import com.google.maps.internal.RateLimitExecutorService;
import java.net.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Dispatcher;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
/* loaded from: classes2.dex */
public class OkHttpRequestHandler implements GeoApiContext.RequestHandler {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final OkHttpClient client;
    private final ExecutorService executorService;

    /* loaded from: classes2.dex */
    public static class Builder implements GeoApiContext.RequestHandler.Builder {
        private final OkHttpClient.Builder builder;
        private final Dispatcher dispatcher;
        private final RateLimitExecutorService rateLimitExecutorService;

        public Builder() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            this.builder = builder;
            RateLimitExecutorService rateLimitExecutorService = new RateLimitExecutorService();
            this.rateLimitExecutorService = rateLimitExecutorService;
            Dispatcher dispatcher = new Dispatcher(rateLimitExecutorService);
            this.dispatcher = dispatcher;
            builder.dispatcher(dispatcher);
        }

        @Override // com.google.maps.GeoApiContext.RequestHandler.Builder
        public GeoApiContext.RequestHandler build() {
            return new OkHttpRequestHandler(this.builder.build(), this.rateLimitExecutorService);
        }

        @Override // com.google.maps.GeoApiContext.RequestHandler.Builder
        public Builder connectTimeout(long j2, TimeUnit timeUnit) {
            this.builder.connectTimeout(j2, timeUnit);
            return this;
        }

        public OkHttpClient.Builder okHttpClientBuilder() {
            return this.builder;
        }

        @Override // com.google.maps.GeoApiContext.RequestHandler.Builder
        public Builder proxy(Proxy proxy) {
            this.builder.proxy(proxy);
            return this;
        }

        @Override // com.google.maps.GeoApiContext.RequestHandler.Builder
        public Builder proxyAuthentication(final String str, final String str2) {
            this.builder.proxyAuthenticator(new Authenticator(this) { // from class: com.google.maps.OkHttpRequestHandler.Builder.1
                @Override // okhttp3.Authenticator
                public Request authenticate(Route route, Response response) {
                    return response.request().newBuilder().header("Proxy-Authorization", Credentials.basic(str, str2)).build();
                }
            });
            return this;
        }

        @Override // com.google.maps.GeoApiContext.RequestHandler.Builder
        public Builder queriesPerSecond(int i2) {
            this.dispatcher.setMaxRequests(i2);
            this.dispatcher.setMaxRequestsPerHost(i2);
            this.rateLimitExecutorService.setQueriesPerSecond(i2);
            return this;
        }

        @Override // com.google.maps.GeoApiContext.RequestHandler.Builder
        public Builder readTimeout(long j2, TimeUnit timeUnit) {
            this.builder.readTimeout(j2, timeUnit);
            return this;
        }

        @Override // com.google.maps.GeoApiContext.RequestHandler.Builder
        public Builder writeTimeout(long j2, TimeUnit timeUnit) {
            this.builder.writeTimeout(j2, timeUnit);
            return this;
        }
    }

    OkHttpRequestHandler(OkHttpClient okHttpClient, ExecutorService executorService) {
        this.client = okHttpClient;
        this.executorService = executorService;
    }

    @Override // com.google.maps.GeoApiContext.RequestHandler
    public <T, R extends ApiResponse<T>> PendingResult<T> handle(String str, String str2, String str3, String str4, Class<R> cls, FieldNamingPolicy fieldNamingPolicy, long j2, Integer num, ExceptionsAllowedToRetry exceptionsAllowedToRetry) {
        Request.Builder header = new Request.Builder().get().header("User-Agent", str3);
        if (str4 != null) {
            header = header.header(HttpHeaders.X_GOOG_MAPS_EXPERIENCE_ID, str4);
        }
        return new OkHttpPendingResult(header.url(str + str2).build(), this.client, cls, fieldNamingPolicy, j2, num, exceptionsAllowedToRetry);
    }

    @Override // com.google.maps.GeoApiContext.RequestHandler
    public <T, R extends ApiResponse<T>> PendingResult<T> handlePost(String str, String str2, String str3, String str4, String str5, Class<R> cls, FieldNamingPolicy fieldNamingPolicy, long j2, Integer num, ExceptionsAllowedToRetry exceptionsAllowedToRetry) {
        Request.Builder header = new Request.Builder().post(RequestBody.create(JSON, str3)).header("User-Agent", str4);
        if (str5 != null) {
            header = header.header(HttpHeaders.X_GOOG_MAPS_EXPERIENCE_ID, str5);
        }
        return new OkHttpPendingResult(header.url(str + str2).build(), this.client, cls, fieldNamingPolicy, j2, num, exceptionsAllowedToRetry);
    }

    @Override // com.google.maps.GeoApiContext.RequestHandler
    public void shutdown() {
        this.executorService.shutdown();
        this.client.connectionPool().evictAll();
    }
}

package com.google.maps;

import com.google.gson.FieldNamingPolicy;
import com.google.maps.OkHttpRequestHandler;
import com.google.maps.errors.ApiException;
import com.google.maps.errors.OverQueryLimitException;
import com.google.maps.internal.ApiConfig;
import com.google.maps.internal.ApiResponse;
import com.google.maps.internal.ExceptionsAllowedToRetry;
import com.google.maps.internal.StringJoin;
import com.google.maps.internal.UrlSigner;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoConstants;
import java.io.UnsupportedEncodingException;
import java.net.Proxy;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.text.Typography;
/* loaded from: classes2.dex */
public class GeoApiContext {
    private static final int DEFAULT_BACKOFF_TIMEOUT_MILLIS = 60000;
    private static final String USER_AGENT = "GoogleGeoApiClientJava/0.11.0";
    private static final String VERSION = "0.11.0";
    private final String apiKey;
    private final String baseUrlOverride;
    private final String channel;
    private final String clientId;
    private final long errorTimeout;
    private final ExceptionsAllowedToRetry exceptionsAllowedToRetry;
    private String experienceIdHeaderValue;
    private final Integer maxRetries;
    private final RequestHandler requestHandler;
    private final UrlSigner urlSigner;

    /* loaded from: classes2.dex */
    public static class Builder {
        private String apiKey;
        private String baseUrlOverride;
        private RequestHandler.Builder builder;
        private String channel;
        private String clientId;
        private long errorTimeout = AutoConstants.UPDATE_INTERVAL_IN_MILLISECONDS;
        private ExceptionsAllowedToRetry exceptionsAllowedToRetry = new ExceptionsAllowedToRetry();
        private String[] experienceIdHeaderValue;
        private Integer maxRetries;
        private UrlSigner urlSigner;

        public Builder() {
            requestHandlerBuilder(new OkHttpRequestHandler.Builder());
        }

        public Builder(RequestHandler.Builder builder) {
            requestHandlerBuilder(builder);
        }

        public Builder apiKey(String str) {
            this.apiKey = str;
            return this;
        }

        public GeoApiContext build() {
            return new GeoApiContext(this.builder.build(), this.apiKey, this.baseUrlOverride, this.channel, this.clientId, this.errorTimeout, this.exceptionsAllowedToRetry, this.maxRetries, this.urlSigner, this.experienceIdHeaderValue);
        }

        public Builder channel(String str) {
            this.channel = str;
            return this;
        }

        public Builder connectTimeout(long j2, TimeUnit timeUnit) {
            this.builder.connectTimeout(j2, timeUnit);
            return this;
        }

        public Builder disableRetries() {
            maxRetries(0);
            retryTimeout(0L, TimeUnit.MILLISECONDS);
            return this;
        }

        public Builder enterpriseCredentials(String str, String str2) {
            this.clientId = str;
            try {
                this.urlSigner = new UrlSigner(str2);
                return this;
            } catch (InvalidKeyException | NoSuchAlgorithmException e2) {
                throw new IllegalStateException(e2);
            }
        }

        public Builder experienceId(String... strArr) {
            this.experienceIdHeaderValue = strArr;
            return this;
        }

        public Builder maxRetries(Integer num) {
            this.maxRetries = num;
            return this;
        }

        public Builder proxy(Proxy proxy) {
            RequestHandler.Builder builder = this.builder;
            if (proxy == null) {
                proxy = Proxy.NO_PROXY;
            }
            builder.proxy(proxy);
            return this;
        }

        public Builder proxyAuthentication(String str, String str2) {
            this.builder.proxyAuthentication(str, str2);
            return this;
        }

        public Builder queryRateLimit(int i2) {
            this.builder.queriesPerSecond(i2);
            return this;
        }

        public Builder readTimeout(long j2, TimeUnit timeUnit) {
            this.builder.readTimeout(j2, timeUnit);
            return this;
        }

        public Builder requestHandlerBuilder(RequestHandler.Builder builder) {
            this.builder = builder;
            this.exceptionsAllowedToRetry.add(OverQueryLimitException.class);
            return this;
        }

        public Builder retryTimeout(long j2, TimeUnit timeUnit) {
            this.errorTimeout = timeUnit.toMillis(j2);
            return this;
        }

        public Builder setIfExceptionIsAllowedToRetry(Class<? extends ApiException> cls, boolean z) {
            if (z) {
                this.exceptionsAllowedToRetry.add(cls);
            } else {
                this.exceptionsAllowedToRetry.remove(cls);
            }
            return this;
        }

        public Builder writeTimeout(long j2, TimeUnit timeUnit) {
            this.builder.writeTimeout(j2, timeUnit);
            return this;
        }
    }

    /* loaded from: classes2.dex */
    public interface RequestHandler {

        /* loaded from: classes2.dex */
        public interface Builder {
            RequestHandler build();

            Builder connectTimeout(long j2, TimeUnit timeUnit);

            Builder proxy(Proxy proxy);

            Builder proxyAuthentication(String str, String str2);

            Builder queriesPerSecond(int i2);

            Builder readTimeout(long j2, TimeUnit timeUnit);

            Builder writeTimeout(long j2, TimeUnit timeUnit);
        }

        <T, R extends ApiResponse<T>> PendingResult<T> handle(String str, String str2, String str3, String str4, Class<R> cls, FieldNamingPolicy fieldNamingPolicy, long j2, Integer num, ExceptionsAllowedToRetry exceptionsAllowedToRetry);

        <T, R extends ApiResponse<T>> PendingResult<T> handlePost(String str, String str2, String str3, String str4, String str5, Class<R> cls, FieldNamingPolicy fieldNamingPolicy, long j2, Integer num, ExceptionsAllowedToRetry exceptionsAllowedToRetry);

        void shutdown();
    }

    GeoApiContext(RequestHandler requestHandler, String str, String str2, String str3, String str4, long j2, ExceptionsAllowedToRetry exceptionsAllowedToRetry, Integer num, UrlSigner urlSigner, String... strArr) {
        this.requestHandler = requestHandler;
        this.apiKey = str;
        this.baseUrlOverride = str2;
        this.channel = str3;
        this.clientId = str4;
        this.errorTimeout = j2;
        this.exceptionsAllowedToRetry = exceptionsAllowedToRetry;
        this.maxRetries = num;
        this.urlSigner = urlSigner;
        setExperienceId(strArr);
    }

    private void checkContext(boolean z) {
        UrlSigner urlSigner = this.urlSigner;
        if (urlSigner == null && this.apiKey == null) {
            throw new IllegalStateException("Must provide either API key or Maps for Work credentials.");
        }
        if (!z && this.apiKey == null) {
            throw new IllegalStateException("API does not support client ID & secret - you must provide a key");
        }
        if (urlSigner == null && !this.apiKey.startsWith("AIza")) {
            throw new IllegalStateException("Invalid API key.");
        }
    }

    private <T, R extends ApiResponse<T>> PendingResult<T> getWithPath(Class<R> cls, FieldNamingPolicy fieldNamingPolicy, String str, String str2, boolean z, String str3) {
        String str4;
        UrlSigner urlSigner;
        checkContext(z);
        if (str3.startsWith("&")) {
            StringBuilder sb = new StringBuilder(str2);
            if (!z || this.clientId == null) {
                sb.append("?key=");
                str4 = this.apiKey;
            } else {
                sb.append("?client=");
                str4 = this.clientId;
            }
            sb.append(str4);
            sb.append(str3);
            if (z && (urlSigner = this.urlSigner) != null) {
                String signature = urlSigner.getSignature(sb.toString());
                sb.append("&signature=");
                sb.append(signature);
            }
            String str5 = this.baseUrlOverride;
            return this.requestHandler.handle(str5 != null ? str5 : str, sb.toString(), USER_AGENT, this.experienceIdHeaderValue, cls, fieldNamingPolicy, this.errorTimeout, this.maxRetries, this.exceptionsAllowedToRetry);
        }
        throw new IllegalArgumentException("encodedPath must start with &");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PendingResult a(ApiConfig apiConfig, Class cls, Map map) {
        String str = this.channel;
        if (str != null && !str.isEmpty() && !map.containsKey("channel")) {
            map.put("channel", Collections.singletonList(this.channel));
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            for (String str2 : (List) entry.getValue()) {
                sb.append(Typography.amp);
                sb.append((String) entry.getKey());
                sb.append("=");
                try {
                    sb.append(URLEncoder.encode(str2, "UTF-8"));
                } catch (UnsupportedEncodingException e2) {
                    throw new IllegalStateException(e2);
                }
            }
        }
        return getWithPath(cls, apiConfig.fieldNamingPolicy, apiConfig.hostName, apiConfig.path, apiConfig.supportsClientId, sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PendingResult b(ApiConfig apiConfig, Class cls, String... strArr) {
        String str;
        if (strArr.length % 2 == 0) {
            StringBuilder sb = new StringBuilder();
            boolean z = false;
            for (int i2 = 0; i2 < strArr.length; i2 += 2) {
                if (strArr[i2].equals("channel")) {
                    z = true;
                }
                sb.append(Typography.amp);
                sb.append(strArr[i2]);
                sb.append('=');
                try {
                    sb.append(URLEncoder.encode(strArr[i2 + 1], "UTF-8"));
                } catch (UnsupportedEncodingException e2) {
                    throw new IllegalStateException(e2);
                }
            }
            if (!z && (str = this.channel) != null && !str.isEmpty()) {
                sb.append("&channel=");
                sb.append(this.channel);
            }
            return getWithPath(cls, apiConfig.fieldNamingPolicy, apiConfig.hostName, apiConfig.path, apiConfig.supportsClientId, sb.toString());
        }
        throw new IllegalArgumentException("Params must be matching key/value pairs.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PendingResult c(ApiConfig apiConfig, Class cls, Map map) {
        String str;
        UrlSigner urlSigner;
        checkContext(apiConfig.supportsClientId);
        StringBuilder sb = new StringBuilder(apiConfig.path);
        if (!apiConfig.supportsClientId || this.clientId == null) {
            sb.append("?key=");
            str = this.apiKey;
        } else {
            sb.append("?client=");
            str = this.clientId;
        }
        sb.append(str);
        if (apiConfig.supportsClientId && (urlSigner = this.urlSigner) != null) {
            String signature = urlSigner.getSignature(sb.toString());
            sb.append("&signature=");
            sb.append(signature);
        }
        String str2 = apiConfig.hostName;
        String str3 = this.baseUrlOverride;
        return this.requestHandler.handlePost(str3 != null ? str3 : str2, sb.toString(), (String) ((List) map.get("_payload")).get(0), USER_AGENT, this.experienceIdHeaderValue, cls, apiConfig.fieldNamingPolicy, this.errorTimeout, this.maxRetries, this.exceptionsAllowedToRetry);
    }

    public void clearExperienceId() {
        this.experienceIdHeaderValue = null;
    }

    public String getExperienceId() {
        return this.experienceIdHeaderValue;
    }

    public void setExperienceId(String... strArr) {
        this.experienceIdHeaderValue = (strArr == null || strArr.length == 0) ? null : StringJoin.join((CharSequence) ",", strArr);
    }

    public void shutdown() {
        this.requestHandler.shutdown();
    }
}

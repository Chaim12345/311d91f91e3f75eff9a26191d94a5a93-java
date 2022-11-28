package com.google.api.client.googleapis.services;

import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Strings;
import java.util.logging.Logger;
/* loaded from: classes2.dex */
public abstract class AbstractGoogleClient {
    private static final Logger logger = Logger.getLogger(AbstractGoogleClient.class.getName());
    private final String applicationName;
    private final String batchPath;
    private final GoogleClientRequestInitializer googleClientRequestInitializer;
    private final ObjectParser objectParser;
    private final HttpRequestFactory requestFactory;
    private final String rootUrl;
    private final String servicePath;
    private final boolean suppressPatternChecks;
    private final boolean suppressRequiredParameterChecks;

    /* loaded from: classes2.dex */
    public static abstract class Builder {

        /* renamed from: a  reason: collision with root package name */
        final HttpTransport f8012a;

        /* renamed from: b  reason: collision with root package name */
        GoogleClientRequestInitializer f8013b;

        /* renamed from: c  reason: collision with root package name */
        HttpRequestInitializer f8014c;

        /* renamed from: d  reason: collision with root package name */
        final ObjectParser f8015d;

        /* renamed from: e  reason: collision with root package name */
        String f8016e;

        /* renamed from: f  reason: collision with root package name */
        String f8017f;

        /* renamed from: g  reason: collision with root package name */
        String f8018g;

        /* renamed from: h  reason: collision with root package name */
        String f8019h;

        /* renamed from: i  reason: collision with root package name */
        boolean f8020i;

        /* renamed from: j  reason: collision with root package name */
        boolean f8021j;

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder(HttpTransport httpTransport, String str, String str2, ObjectParser objectParser, HttpRequestInitializer httpRequestInitializer) {
            this.f8012a = (HttpTransport) Preconditions.checkNotNull(httpTransport);
            this.f8015d = objectParser;
            setRootUrl(str);
            setServicePath(str2);
            this.f8014c = httpRequestInitializer;
        }

        public abstract AbstractGoogleClient build();

        public final String getApplicationName() {
            return this.f8019h;
        }

        public final GoogleClientRequestInitializer getGoogleClientRequestInitializer() {
            return this.f8013b;
        }

        public final HttpRequestInitializer getHttpRequestInitializer() {
            return this.f8014c;
        }

        public ObjectParser getObjectParser() {
            return this.f8015d;
        }

        public final String getRootUrl() {
            return this.f8016e;
        }

        public final String getServicePath() {
            return this.f8017f;
        }

        public final boolean getSuppressPatternChecks() {
            return this.f8020i;
        }

        public final boolean getSuppressRequiredParameterChecks() {
            return this.f8021j;
        }

        public final HttpTransport getTransport() {
            return this.f8012a;
        }

        public Builder setApplicationName(String str) {
            this.f8019h = str;
            return this;
        }

        public Builder setBatchPath(String str) {
            this.f8018g = str;
            return this;
        }

        public Builder setGoogleClientRequestInitializer(GoogleClientRequestInitializer googleClientRequestInitializer) {
            this.f8013b = googleClientRequestInitializer;
            return this;
        }

        public Builder setHttpRequestInitializer(HttpRequestInitializer httpRequestInitializer) {
            this.f8014c = httpRequestInitializer;
            return this;
        }

        public Builder setRootUrl(String str) {
            this.f8016e = AbstractGoogleClient.a(str);
            return this;
        }

        public Builder setServicePath(String str) {
            this.f8017f = AbstractGoogleClient.b(str);
            return this;
        }

        public Builder setSuppressAllChecks(boolean z) {
            return setSuppressPatternChecks(true).setSuppressRequiredParameterChecks(true);
        }

        public Builder setSuppressPatternChecks(boolean z) {
            this.f8020i = z;
            return this;
        }

        public Builder setSuppressRequiredParameterChecks(boolean z) {
            this.f8021j = z;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractGoogleClient(Builder builder) {
        this.googleClientRequestInitializer = builder.f8013b;
        this.rootUrl = a(builder.f8016e);
        this.servicePath = b(builder.f8017f);
        this.batchPath = builder.f8018g;
        if (Strings.isNullOrEmpty(builder.f8019h)) {
            logger.warning("Application name is not set. Call Builder#setApplicationName.");
        }
        this.applicationName = builder.f8019h;
        HttpRequestInitializer httpRequestInitializer = builder.f8014c;
        this.requestFactory = httpRequestInitializer == null ? builder.f8012a.createRequestFactory() : builder.f8012a.createRequestFactory(httpRequestInitializer);
        this.objectParser = builder.f8015d;
        this.suppressPatternChecks = builder.f8020i;
        this.suppressRequiredParameterChecks = builder.f8021j;
    }

    static String a(String str) {
        Preconditions.checkNotNull(str, "root URL cannot be null.");
        if (str.endsWith("/")) {
            return str;
        }
        return str + "/";
    }

    static String b(String str) {
        Preconditions.checkNotNull(str, "service path cannot be null");
        if (str.length() == 1) {
            Preconditions.checkArgument("/".equals(str), "service path must equal \"/\" if it is of length 1.");
            return "";
        } else if (str.length() > 0) {
            if (!str.endsWith("/")) {
                str = str + "/";
            }
            return str.startsWith("/") ? str.substring(1) : str;
        } else {
            return str;
        }
    }

    public final BatchRequest batch() {
        return batch(null);
    }

    public final BatchRequest batch(HttpRequestInitializer httpRequestInitializer) {
        GenericUrl genericUrl;
        BatchRequest batchRequest = new BatchRequest(getRequestFactory().getTransport(), httpRequestInitializer);
        if (Strings.isNullOrEmpty(this.batchPath)) {
            genericUrl = new GenericUrl(getRootUrl() + "batch");
        } else {
            genericUrl = new GenericUrl(getRootUrl() + this.batchPath);
        }
        batchRequest.setBatchUrl(genericUrl);
        return batchRequest;
    }

    public final String getApplicationName() {
        return this.applicationName;
    }

    public final String getBaseUrl() {
        return this.rootUrl + this.servicePath;
    }

    public final GoogleClientRequestInitializer getGoogleClientRequestInitializer() {
        return this.googleClientRequestInitializer;
    }

    public ObjectParser getObjectParser() {
        return this.objectParser;
    }

    public final HttpRequestFactory getRequestFactory() {
        return this.requestFactory;
    }

    public final String getRootUrl() {
        return this.rootUrl;
    }

    public final String getServicePath() {
        return this.servicePath;
    }

    public final boolean getSuppressPatternChecks() {
        return this.suppressPatternChecks;
    }

    public final boolean getSuppressRequiredParameterChecks() {
        return this.suppressRequiredParameterChecks;
    }
}

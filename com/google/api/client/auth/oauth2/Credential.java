package com.google.api.client.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Objects;
import com.google.api.client.util.Preconditions;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes2.dex */
public class Credential implements HttpExecuteInterceptor, HttpRequestInitializer, HttpUnsuccessfulResponseHandler {

    /* renamed from: a  reason: collision with root package name */
    static final Logger f7964a = Logger.getLogger(Credential.class.getName());
    private String accessToken;
    private final HttpExecuteInterceptor clientAuthentication;
    private final Clock clock;
    private Long expirationTimeMilliseconds;
    private final JsonFactory jsonFactory;
    private final Lock lock;
    private final AccessMethod method;
    private final Collection<CredentialRefreshListener> refreshListeners;
    private String refreshToken;
    private final HttpRequestInitializer requestInitializer;
    private final String tokenServerEncodedUrl;
    private final HttpTransport transport;

    /* loaded from: classes2.dex */
    public interface AccessMethod {
        String getAccessTokenFromRequest(HttpRequest httpRequest);

        void intercept(HttpRequest httpRequest, String str);
    }

    /* loaded from: classes2.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        final AccessMethod f7965a;

        /* renamed from: b  reason: collision with root package name */
        HttpTransport f7966b;

        /* renamed from: c  reason: collision with root package name */
        JsonFactory f7967c;

        /* renamed from: d  reason: collision with root package name */
        GenericUrl f7968d;

        /* renamed from: f  reason: collision with root package name */
        HttpExecuteInterceptor f7970f;

        /* renamed from: g  reason: collision with root package name */
        HttpRequestInitializer f7971g;

        /* renamed from: e  reason: collision with root package name */
        Clock f7969e = Clock.SYSTEM;

        /* renamed from: h  reason: collision with root package name */
        Collection f7972h = Lists.newArrayList();

        public Builder(AccessMethod accessMethod) {
            this.f7965a = (AccessMethod) Preconditions.checkNotNull(accessMethod);
        }

        public Builder addRefreshListener(CredentialRefreshListener credentialRefreshListener) {
            this.f7972h.add(Preconditions.checkNotNull(credentialRefreshListener));
            return this;
        }

        public Credential build() {
            return new Credential(this);
        }

        public final HttpExecuteInterceptor getClientAuthentication() {
            return this.f7970f;
        }

        public final Clock getClock() {
            return this.f7969e;
        }

        public final JsonFactory getJsonFactory() {
            return this.f7967c;
        }

        public final AccessMethod getMethod() {
            return this.f7965a;
        }

        public final Collection<CredentialRefreshListener> getRefreshListeners() {
            return this.f7972h;
        }

        public final HttpRequestInitializer getRequestInitializer() {
            return this.f7971g;
        }

        public final GenericUrl getTokenServerUrl() {
            return this.f7968d;
        }

        public final HttpTransport getTransport() {
            return this.f7966b;
        }

        public Builder setClientAuthentication(HttpExecuteInterceptor httpExecuteInterceptor) {
            this.f7970f = httpExecuteInterceptor;
            return this;
        }

        public Builder setClock(Clock clock) {
            this.f7969e = (Clock) Preconditions.checkNotNull(clock);
            return this;
        }

        public Builder setJsonFactory(JsonFactory jsonFactory) {
            this.f7967c = jsonFactory;
            return this;
        }

        public Builder setRefreshListeners(Collection<CredentialRefreshListener> collection) {
            this.f7972h = (Collection) Preconditions.checkNotNull(collection);
            return this;
        }

        public Builder setRequestInitializer(HttpRequestInitializer httpRequestInitializer) {
            this.f7971g = httpRequestInitializer;
            return this;
        }

        public Builder setTokenServerEncodedUrl(String str) {
            this.f7968d = str == null ? null : new GenericUrl(str);
            return this;
        }

        public Builder setTokenServerUrl(GenericUrl genericUrl) {
            this.f7968d = genericUrl;
            return this;
        }

        public Builder setTransport(HttpTransport httpTransport) {
            this.f7966b = httpTransport;
            return this;
        }
    }

    public Credential(AccessMethod accessMethod) {
        this(new Builder(accessMethod));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Credential(Builder builder) {
        this.lock = new ReentrantLock();
        this.method = (AccessMethod) Preconditions.checkNotNull(builder.f7965a);
        this.transport = builder.f7966b;
        this.jsonFactory = builder.f7967c;
        GenericUrl genericUrl = builder.f7968d;
        this.tokenServerEncodedUrl = genericUrl == null ? null : genericUrl.build();
        this.clientAuthentication = builder.f7970f;
        this.requestInitializer = builder.f7971g;
        this.refreshListeners = Collections.unmodifiableCollection(builder.f7972h);
        this.clock = (Clock) Preconditions.checkNotNull(builder.f7969e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TokenResponse a() {
        if (this.refreshToken == null) {
            return null;
        }
        return new RefreshTokenRequest(this.transport, this.jsonFactory, new GenericUrl(this.tokenServerEncodedUrl), this.refreshToken).setClientAuthentication(this.clientAuthentication).setRequestInitializer(this.requestInitializer).execute();
    }

    public final String getAccessToken() {
        this.lock.lock();
        try {
            return this.accessToken;
        } finally {
            this.lock.unlock();
        }
    }

    public final HttpExecuteInterceptor getClientAuthentication() {
        return this.clientAuthentication;
    }

    public final Clock getClock() {
        return this.clock;
    }

    public final Long getExpirationTimeMilliseconds() {
        this.lock.lock();
        try {
            return this.expirationTimeMilliseconds;
        } finally {
            this.lock.unlock();
        }
    }

    public final Long getExpiresInSeconds() {
        this.lock.lock();
        try {
            Long l2 = this.expirationTimeMilliseconds;
            return l2 == null ? null : Long.valueOf((l2.longValue() - this.clock.currentTimeMillis()) / 1000);
        } finally {
            this.lock.unlock();
        }
    }

    public final JsonFactory getJsonFactory() {
        return this.jsonFactory;
    }

    public final AccessMethod getMethod() {
        return this.method;
    }

    public final Collection<CredentialRefreshListener> getRefreshListeners() {
        return this.refreshListeners;
    }

    public final String getRefreshToken() {
        this.lock.lock();
        try {
            return this.refreshToken;
        } finally {
            this.lock.unlock();
        }
    }

    public final HttpRequestInitializer getRequestInitializer() {
        return this.requestInitializer;
    }

    public final String getTokenServerEncodedUrl() {
        return this.tokenServerEncodedUrl;
    }

    public final HttpTransport getTransport() {
        return this.transport;
    }

    @Override // com.google.api.client.http.HttpUnsuccessfulResponseHandler
    public boolean handleResponse(HttpRequest httpRequest, HttpResponse httpResponse, boolean z) {
        boolean z2;
        boolean z3;
        List<String> authenticateAsList = httpResponse.getHeaders().getAuthenticateAsList();
        boolean z4 = true;
        if (authenticateAsList != null) {
            for (String str : authenticateAsList) {
                if (str.startsWith("Bearer ")) {
                    z2 = BearerToken.f7963a.matcher(str).find();
                    z3 = true;
                    break;
                }
            }
        }
        z2 = false;
        z3 = false;
        if (!z3) {
            z2 = httpResponse.getStatusCode() == 401;
        }
        if (z2) {
            try {
                this.lock.lock();
                if (Objects.equal(this.accessToken, this.method.getAccessTokenFromRequest(httpRequest)) && !refreshToken()) {
                    z4 = false;
                }
                this.lock.unlock();
                return z4;
            } catch (IOException e2) {
                f7964a.log(Level.SEVERE, "unable to refresh token", (Throwable) e2);
            }
        }
        return false;
    }

    @Override // com.google.api.client.http.HttpRequestInitializer
    public void initialize(HttpRequest httpRequest) {
        httpRequest.setInterceptor(this);
        httpRequest.setUnsuccessfulResponseHandler(this);
    }

    @Override // com.google.api.client.http.HttpExecuteInterceptor
    public void intercept(HttpRequest httpRequest) {
        this.lock.lock();
        try {
            Long expiresInSeconds = getExpiresInSeconds();
            if (this.accessToken == null || (expiresInSeconds != null && expiresInSeconds.longValue() <= 60)) {
                refreshToken();
                if (this.accessToken == null) {
                    return;
                }
            }
            this.method.intercept(httpRequest, this.accessToken);
        } finally {
            this.lock.unlock();
        }
    }

    public final boolean refreshToken() {
        this.lock.lock();
        boolean z = true;
        try {
            try {
                TokenResponse a2 = a();
                if (a2 != null) {
                    setFromTokenResponse(a2);
                    for (CredentialRefreshListener credentialRefreshListener : this.refreshListeners) {
                        credentialRefreshListener.onTokenResponse(this, a2);
                    }
                    return true;
                }
            } catch (TokenResponseException e2) {
                if (400 > e2.getStatusCode() || e2.getStatusCode() >= 500) {
                    z = false;
                }
                if (e2.getDetails() != null && z) {
                    setAccessToken(null);
                    setExpiresInSeconds(null);
                }
                for (CredentialRefreshListener credentialRefreshListener2 : this.refreshListeners) {
                    credentialRefreshListener2.onTokenErrorResponse(this, e2.getDetails());
                }
                if (z) {
                    throw e2;
                }
            }
            return false;
        } finally {
            this.lock.unlock();
        }
    }

    public Credential setAccessToken(String str) {
        this.lock.lock();
        try {
            this.accessToken = str;
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    public Credential setExpirationTimeMilliseconds(Long l2) {
        this.lock.lock();
        try {
            this.expirationTimeMilliseconds = l2;
            return this;
        } finally {
            this.lock.unlock();
        }
    }

    public Credential setExpiresInSeconds(Long l2) {
        return setExpirationTimeMilliseconds(l2 == null ? null : Long.valueOf(this.clock.currentTimeMillis() + (l2.longValue() * 1000)));
    }

    public Credential setFromTokenResponse(TokenResponse tokenResponse) {
        setAccessToken(tokenResponse.getAccessToken());
        if (tokenResponse.getRefreshToken() != null) {
            setRefreshToken(tokenResponse.getRefreshToken());
        }
        setExpiresInSeconds(tokenResponse.getExpiresInSeconds());
        return this;
    }

    public Credential setRefreshToken(String str) {
        this.lock.lock();
        if (str != null) {
            try {
                Preconditions.checkArgument((this.jsonFactory == null || this.transport == null || this.clientAuthentication == null || this.tokenServerEncodedUrl == null) ? false : true, "Please use the Builder and call setJsonFactory, setTransport, setClientAuthentication and setTokenServerUrl/setTokenServerEncodedUrl");
            } finally {
                this.lock.unlock();
            }
        }
        this.refreshToken = str;
        return this;
    }
}

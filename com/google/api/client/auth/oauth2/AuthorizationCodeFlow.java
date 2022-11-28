package com.google.api.client.auth.oauth2;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.Beta;
import com.google.api.client.util.Clock;
import com.google.api.client.util.Data;
import com.google.api.client.util.Joiner;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Strings;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Collections;
import org.apache.http.message.TokenParser;
/* loaded from: classes2.dex */
public class AuthorizationCodeFlow {
    private final String authorizationServerEncodedUrl;
    private final HttpExecuteInterceptor clientAuthentication;
    private final String clientId;
    private final Clock clock;
    private final CredentialCreatedListener credentialCreatedListener;
    @Beta
    private final DataStore<StoredCredential> credentialDataStore;
    @Beta
    @Deprecated
    private final CredentialStore credentialStore;
    private final JsonFactory jsonFactory;
    private final Credential.AccessMethod method;
    private final PKCE pkce;
    private final Collection<CredentialRefreshListener> refreshListeners;
    private final HttpRequestInitializer requestInitializer;
    private final Collection<String> scopes;
    private final String tokenServerEncodedUrl;
    private final HttpTransport transport;

    /* loaded from: classes2.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        Credential.AccessMethod f7946a;

        /* renamed from: b  reason: collision with root package name */
        HttpTransport f7947b;

        /* renamed from: c  reason: collision with root package name */
        JsonFactory f7948c;

        /* renamed from: d  reason: collision with root package name */
        GenericUrl f7949d;

        /* renamed from: e  reason: collision with root package name */
        HttpExecuteInterceptor f7950e;

        /* renamed from: f  reason: collision with root package name */
        String f7951f;

        /* renamed from: g  reason: collision with root package name */
        String f7952g;

        /* renamed from: h  reason: collision with root package name */
        PKCE f7953h;
        @Beta
        @Deprecated

        /* renamed from: i  reason: collision with root package name */
        CredentialStore f7954i;
        @Beta

        /* renamed from: j  reason: collision with root package name */
        DataStore f7955j;

        /* renamed from: k  reason: collision with root package name */
        HttpRequestInitializer f7956k;

        /* renamed from: n  reason: collision with root package name */
        CredentialCreatedListener f7959n;

        /* renamed from: l  reason: collision with root package name */
        Collection f7957l = Lists.newArrayList();

        /* renamed from: m  reason: collision with root package name */
        Clock f7958m = Clock.SYSTEM;

        /* renamed from: o  reason: collision with root package name */
        Collection f7960o = Lists.newArrayList();

        public Builder(Credential.AccessMethod accessMethod, HttpTransport httpTransport, JsonFactory jsonFactory, GenericUrl genericUrl, HttpExecuteInterceptor httpExecuteInterceptor, String str, String str2) {
            setMethod(accessMethod);
            setTransport(httpTransport);
            setJsonFactory(jsonFactory);
            setTokenServerUrl(genericUrl);
            setClientAuthentication(httpExecuteInterceptor);
            setClientId(str);
            setAuthorizationServerEncodedUrl(str2);
        }

        public Builder addRefreshListener(CredentialRefreshListener credentialRefreshListener) {
            this.f7960o.add(Preconditions.checkNotNull(credentialRefreshListener));
            return this;
        }

        public AuthorizationCodeFlow build() {
            return new AuthorizationCodeFlow(this);
        }

        @Beta
        public Builder enablePKCE() {
            this.f7953h = new PKCE();
            return this;
        }

        public final String getAuthorizationServerEncodedUrl() {
            return this.f7952g;
        }

        public final HttpExecuteInterceptor getClientAuthentication() {
            return this.f7950e;
        }

        public final String getClientId() {
            return this.f7951f;
        }

        public final Clock getClock() {
            return this.f7958m;
        }

        public final CredentialCreatedListener getCredentialCreatedListener() {
            return this.f7959n;
        }

        @Beta
        public final DataStore<StoredCredential> getCredentialDataStore() {
            return this.f7955j;
        }

        @Beta
        @Deprecated
        public final CredentialStore getCredentialStore() {
            return this.f7954i;
        }

        public final JsonFactory getJsonFactory() {
            return this.f7948c;
        }

        public final Credential.AccessMethod getMethod() {
            return this.f7946a;
        }

        public final Collection<CredentialRefreshListener> getRefreshListeners() {
            return this.f7960o;
        }

        public final HttpRequestInitializer getRequestInitializer() {
            return this.f7956k;
        }

        public final Collection<String> getScopes() {
            return this.f7957l;
        }

        public final GenericUrl getTokenServerUrl() {
            return this.f7949d;
        }

        public final HttpTransport getTransport() {
            return this.f7947b;
        }

        public Builder setAuthorizationServerEncodedUrl(String str) {
            this.f7952g = (String) Preconditions.checkNotNull(str);
            return this;
        }

        public Builder setClientAuthentication(HttpExecuteInterceptor httpExecuteInterceptor) {
            this.f7950e = httpExecuteInterceptor;
            return this;
        }

        public Builder setClientId(String str) {
            this.f7951f = (String) Preconditions.checkNotNull(str);
            return this;
        }

        public Builder setClock(Clock clock) {
            this.f7958m = (Clock) Preconditions.checkNotNull(clock);
            return this;
        }

        public Builder setCredentialCreatedListener(CredentialCreatedListener credentialCreatedListener) {
            this.f7959n = credentialCreatedListener;
            return this;
        }

        @Beta
        public Builder setCredentialDataStore(DataStore<StoredCredential> dataStore) {
            Preconditions.checkArgument(this.f7954i == null);
            this.f7955j = dataStore;
            return this;
        }

        @Beta
        @Deprecated
        public Builder setCredentialStore(CredentialStore credentialStore) {
            Preconditions.checkArgument(this.f7955j == null);
            this.f7954i = credentialStore;
            return this;
        }

        @Beta
        public Builder setDataStoreFactory(DataStoreFactory dataStoreFactory) {
            return setCredentialDataStore(StoredCredential.getDefaultDataStore(dataStoreFactory));
        }

        public Builder setJsonFactory(JsonFactory jsonFactory) {
            this.f7948c = (JsonFactory) Preconditions.checkNotNull(jsonFactory);
            return this;
        }

        public Builder setMethod(Credential.AccessMethod accessMethod) {
            this.f7946a = (Credential.AccessMethod) Preconditions.checkNotNull(accessMethod);
            return this;
        }

        public Builder setRefreshListeners(Collection<CredentialRefreshListener> collection) {
            this.f7960o = (Collection) Preconditions.checkNotNull(collection);
            return this;
        }

        public Builder setRequestInitializer(HttpRequestInitializer httpRequestInitializer) {
            this.f7956k = httpRequestInitializer;
            return this;
        }

        public Builder setScopes(Collection<String> collection) {
            this.f7957l = (Collection) Preconditions.checkNotNull(collection);
            return this;
        }

        public Builder setTokenServerUrl(GenericUrl genericUrl) {
            this.f7949d = (GenericUrl) Preconditions.checkNotNull(genericUrl);
            return this;
        }

        public Builder setTransport(HttpTransport httpTransport) {
            this.f7947b = (HttpTransport) Preconditions.checkNotNull(httpTransport);
            return this;
        }
    }

    /* loaded from: classes2.dex */
    public interface CredentialCreatedListener {
        void onCredentialCreated(Credential credential, TokenResponse tokenResponse);
    }

    /* loaded from: classes2.dex */
    private static class PKCE {
        private String challenge;
        private String challengeMethod;
        private final String verifier;

        public PKCE() {
            String generateVerifier = generateVerifier();
            this.verifier = generateVerifier;
            generateChallenge(generateVerifier);
        }

        private void generateChallenge(String str) {
            try {
                byte[] bytes = str.getBytes();
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(bytes, 0, bytes.length);
                this.challenge = Base64.encodeBase64URLSafeString(messageDigest.digest());
                this.challengeMethod = "S256";
            } catch (NoSuchAlgorithmException unused) {
                this.challenge = str;
                this.challengeMethod = "plain";
            }
        }

        private static String generateVerifier() {
            byte[] bArr = new byte[32];
            new SecureRandom().nextBytes(bArr);
            return Base64.encodeBase64URLSafeString(bArr);
        }

        public String getChallenge() {
            return this.challenge;
        }

        public String getChallengeMethod() {
            return this.challengeMethod;
        }

        public String getVerifier() {
            return this.verifier;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AuthorizationCodeFlow(Builder builder) {
        this.method = (Credential.AccessMethod) Preconditions.checkNotNull(builder.f7946a);
        this.transport = (HttpTransport) Preconditions.checkNotNull(builder.f7947b);
        this.jsonFactory = (JsonFactory) Preconditions.checkNotNull(builder.f7948c);
        this.tokenServerEncodedUrl = ((GenericUrl) Preconditions.checkNotNull(builder.f7949d)).build();
        this.clientAuthentication = builder.f7950e;
        this.clientId = (String) Preconditions.checkNotNull(builder.f7951f);
        this.authorizationServerEncodedUrl = (String) Preconditions.checkNotNull(builder.f7952g);
        this.requestInitializer = builder.f7956k;
        this.credentialStore = builder.f7954i;
        this.credentialDataStore = builder.f7955j;
        this.scopes = Collections.unmodifiableCollection(builder.f7957l);
        this.clock = (Clock) Preconditions.checkNotNull(builder.f7958m);
        this.credentialCreatedListener = builder.f7959n;
        this.refreshListeners = Collections.unmodifiableCollection(builder.f7960o);
        this.pkce = builder.f7953h;
    }

    public AuthorizationCodeFlow(Credential.AccessMethod accessMethod, HttpTransport httpTransport, JsonFactory jsonFactory, GenericUrl genericUrl, HttpExecuteInterceptor httpExecuteInterceptor, String str, String str2) {
        this(new Builder(accessMethod, httpTransport, jsonFactory, genericUrl, httpExecuteInterceptor, str, str2));
    }

    private Credential newCredential(String str) {
        CredentialRefreshListener credentialStoreRefreshListener;
        Credential.Builder clock = new Credential.Builder(this.method).setTransport(this.transport).setJsonFactory(this.jsonFactory).setTokenServerEncodedUrl(this.tokenServerEncodedUrl).setClientAuthentication(this.clientAuthentication).setRequestInitializer(this.requestInitializer).setClock(this.clock);
        DataStore<StoredCredential> dataStore = this.credentialDataStore;
        if (dataStore == null) {
            CredentialStore credentialStore = this.credentialStore;
            if (credentialStore != null) {
                credentialStoreRefreshListener = new CredentialStoreRefreshListener(str, credentialStore);
            }
            clock.getRefreshListeners().addAll(this.refreshListeners);
            return clock.build();
        }
        credentialStoreRefreshListener = new DataStoreCredentialRefreshListener(str, dataStore);
        clock.addRefreshListener(credentialStoreRefreshListener);
        clock.getRefreshListeners().addAll(this.refreshListeners);
        return clock.build();
    }

    public Credential createAndStoreCredential(TokenResponse tokenResponse, String str) {
        Credential fromTokenResponse = newCredential(str).setFromTokenResponse(tokenResponse);
        CredentialStore credentialStore = this.credentialStore;
        if (credentialStore != null) {
            credentialStore.store(str, fromTokenResponse);
        }
        DataStore<StoredCredential> dataStore = this.credentialDataStore;
        if (dataStore != null) {
            dataStore.set(str, new StoredCredential(fromTokenResponse));
        }
        CredentialCreatedListener credentialCreatedListener = this.credentialCreatedListener;
        if (credentialCreatedListener != null) {
            credentialCreatedListener.onCredentialCreated(fromTokenResponse, tokenResponse);
        }
        return fromTokenResponse;
    }

    public final String getAuthorizationServerEncodedUrl() {
        return this.authorizationServerEncodedUrl;
    }

    public final HttpExecuteInterceptor getClientAuthentication() {
        return this.clientAuthentication;
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final Clock getClock() {
        return this.clock;
    }

    @Beta
    public final DataStore<StoredCredential> getCredentialDataStore() {
        return this.credentialDataStore;
    }

    @Beta
    @Deprecated
    public final CredentialStore getCredentialStore() {
        return this.credentialStore;
    }

    public final JsonFactory getJsonFactory() {
        return this.jsonFactory;
    }

    public final Credential.AccessMethod getMethod() {
        return this.method;
    }

    public final Collection<CredentialRefreshListener> getRefreshListeners() {
        return this.refreshListeners;
    }

    public final HttpRequestInitializer getRequestInitializer() {
        return this.requestInitializer;
    }

    public final Collection<String> getScopes() {
        return this.scopes;
    }

    public final String getScopesAsString() {
        return Joiner.on(TokenParser.SP).join(this.scopes);
    }

    public final String getTokenServerEncodedUrl() {
        return this.tokenServerEncodedUrl;
    }

    public final HttpTransport getTransport() {
        return this.transport;
    }

    public Credential loadCredential(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return null;
        }
        if (this.credentialDataStore == null && this.credentialStore == null) {
            return null;
        }
        Credential newCredential = newCredential(str);
        DataStore<StoredCredential> dataStore = this.credentialDataStore;
        if (dataStore != null) {
            StoredCredential storedCredential = dataStore.get(str);
            if (storedCredential == null) {
                return null;
            }
            newCredential.setAccessToken(storedCredential.getAccessToken());
            newCredential.setRefreshToken(storedCredential.getRefreshToken());
            newCredential.setExpirationTimeMilliseconds(storedCredential.getExpirationTimeMilliseconds());
        } else if (!this.credentialStore.load(str, newCredential)) {
            return null;
        }
        return newCredential;
    }

    public AuthorizationCodeRequestUrl newAuthorizationUrl() {
        AuthorizationCodeRequestUrl authorizationCodeRequestUrl = new AuthorizationCodeRequestUrl(this.authorizationServerEncodedUrl, this.clientId);
        authorizationCodeRequestUrl.setScopes(this.scopes);
        PKCE pkce = this.pkce;
        if (pkce != null) {
            authorizationCodeRequestUrl.setCodeChallenge(pkce.getChallenge());
            authorizationCodeRequestUrl.setCodeChallengeMethod(this.pkce.getChallengeMethod());
        }
        return authorizationCodeRequestUrl;
    }

    public AuthorizationCodeTokenRequest newTokenRequest(String str) {
        return new AuthorizationCodeTokenRequest(this.transport, this.jsonFactory, new GenericUrl(this.tokenServerEncodedUrl), str).setClientAuthentication(new HttpExecuteInterceptor() { // from class: com.google.api.client.auth.oauth2.AuthorizationCodeFlow.1
            @Override // com.google.api.client.http.HttpExecuteInterceptor
            public void intercept(HttpRequest httpRequest) {
                AuthorizationCodeFlow.this.clientAuthentication.intercept(httpRequest);
                if (AuthorizationCodeFlow.this.pkce != null) {
                    Data.mapOf(UrlEncodedContent.getContent(httpRequest).getData()).put("code_verifier", AuthorizationCodeFlow.this.pkce.getVerifier());
                }
            }
        }).setRequestInitializer(this.requestInitializer).setScopes(this.scopes);
    }
}

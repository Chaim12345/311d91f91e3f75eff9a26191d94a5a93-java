package com.google.api.client.auth.oauth;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedParser;
import com.google.api.client.util.Beta;
@Beta
/* loaded from: classes2.dex */
public abstract class AbstractOAuthGetToken extends GenericUrl {

    /* renamed from: c  reason: collision with root package name */
    protected boolean f7944c;
    public String consumerKey;
    public OAuthSigner signer;
    public HttpTransport transport;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractOAuthGetToken(String str) {
        super(str);
    }

    public OAuthParameters createParameters() {
        OAuthParameters oAuthParameters = new OAuthParameters();
        oAuthParameters.consumerKey = this.consumerKey;
        oAuthParameters.signer = this.signer;
        return oAuthParameters;
    }

    public final OAuthCredentialsResponse execute() {
        HttpRequest buildRequest = this.transport.createRequestFactory().buildRequest(this.f7944c ? "POST" : "GET", this, null);
        createParameters().intercept(buildRequest);
        HttpResponse execute = buildRequest.execute();
        execute.setContentLoggingLimit(0);
        OAuthCredentialsResponse oAuthCredentialsResponse = new OAuthCredentialsResponse();
        UrlEncodedParser.parse(execute.parseAsString(), oAuthCredentialsResponse);
        return oAuthCredentialsResponse;
    }
}

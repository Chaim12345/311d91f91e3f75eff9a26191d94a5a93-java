package com.google.api.client.auth.oauth2;

import com.google.api.client.util.Key;
import java.util.Collection;
import java.util.Collections;
/* loaded from: classes2.dex */
public class AuthorizationCodeRequestUrl extends AuthorizationRequestUrl {
    @Key("code_challenge")

    /* renamed from: c  reason: collision with root package name */
    String f7961c;
    @Key("code_challenge_method")

    /* renamed from: d  reason: collision with root package name */
    String f7962d;

    public AuthorizationCodeRequestUrl(String str, String str2) {
        super(str, str2, Collections.singleton("code"));
    }

    @Override // com.google.api.client.auth.oauth2.AuthorizationRequestUrl, com.google.api.client.http.GenericUrl, com.google.api.client.util.GenericData, java.util.AbstractMap
    public AuthorizationCodeRequestUrl clone() {
        return (AuthorizationCodeRequestUrl) super.clone();
    }

    public String getCodeChallenge() {
        return this.f7961c;
    }

    public String getCodeChallengeMethod() {
        return this.f7962d;
    }

    @Override // com.google.api.client.auth.oauth2.AuthorizationRequestUrl, com.google.api.client.http.GenericUrl, com.google.api.client.util.GenericData
    public AuthorizationCodeRequestUrl set(String str, Object obj) {
        return (AuthorizationCodeRequestUrl) super.set(str, obj);
    }

    @Override // com.google.api.client.auth.oauth2.AuthorizationRequestUrl
    public AuthorizationCodeRequestUrl setClientId(String str) {
        return (AuthorizationCodeRequestUrl) super.setClientId(str);
    }

    public void setCodeChallenge(String str) {
        this.f7961c = str;
    }

    public void setCodeChallengeMethod(String str) {
        this.f7962d = str;
    }

    @Override // com.google.api.client.auth.oauth2.AuthorizationRequestUrl
    public AuthorizationCodeRequestUrl setRedirectUri(String str) {
        return (AuthorizationCodeRequestUrl) super.setRedirectUri(str);
    }

    @Override // com.google.api.client.auth.oauth2.AuthorizationRequestUrl
    public AuthorizationCodeRequestUrl setResponseTypes(Collection<String> collection) {
        return (AuthorizationCodeRequestUrl) super.setResponseTypes(collection);
    }

    @Override // com.google.api.client.auth.oauth2.AuthorizationRequestUrl
    public /* bridge */ /* synthetic */ AuthorizationRequestUrl setResponseTypes(Collection collection) {
        return setResponseTypes((Collection<String>) collection);
    }

    @Override // com.google.api.client.auth.oauth2.AuthorizationRequestUrl
    public AuthorizationCodeRequestUrl setScopes(Collection<String> collection) {
        return (AuthorizationCodeRequestUrl) super.setScopes(collection);
    }

    @Override // com.google.api.client.auth.oauth2.AuthorizationRequestUrl
    public /* bridge */ /* synthetic */ AuthorizationRequestUrl setScopes(Collection collection) {
        return setScopes((Collection<String>) collection);
    }

    @Override // com.google.api.client.auth.oauth2.AuthorizationRequestUrl
    public AuthorizationCodeRequestUrl setState(String str) {
        return (AuthorizationCodeRequestUrl) super.setState(str);
    }
}

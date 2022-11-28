package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
/* loaded from: classes2.dex */
public final class BasicAuthentication implements HttpRequestInitializer, HttpExecuteInterceptor {
    private final String password;
    private final String username;

    public BasicAuthentication(String str, String str2) {
        this.username = (String) Preconditions.checkNotNull(str);
        this.password = (String) Preconditions.checkNotNull(str2);
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    @Override // com.google.api.client.http.HttpRequestInitializer
    public void initialize(HttpRequest httpRequest) {
        httpRequest.setInterceptor(this);
    }

    @Override // com.google.api.client.http.HttpExecuteInterceptor
    public void intercept(HttpRequest httpRequest) {
        httpRequest.getHeaders().setBasicAuthentication(this.username, this.password);
    }
}

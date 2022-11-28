package com.psa.mym.mycitroenconnect.api.retrofit;

import com.psa.mym.mycitroenconnect.api.retrofit.ProfileOkHttpClientInstance;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
/* loaded from: classes.dex */
public final /* synthetic */ class c implements HostnameVerifier {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ c f10361a = new c();

    private /* synthetic */ c() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        boolean m48getUnsafeOkHttpClient$lambda3;
        m48getUnsafeOkHttpClient$lambda3 = ProfileOkHttpClientInstance.Builder.m48getUnsafeOkHttpClient$lambda3(str, sSLSession);
        return m48getUnsafeOkHttpClient$lambda3;
    }
}

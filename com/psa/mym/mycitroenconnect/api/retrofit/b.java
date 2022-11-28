package com.psa.mym.mycitroenconnect.api.retrofit;

import com.psa.mym.mycitroenconnect.api.retrofit.OkHttpClientInstance;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
/* loaded from: classes.dex */
public final /* synthetic */ class b implements HostnameVerifier {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ b f10360a = new b();

    private /* synthetic */ b() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        boolean m46getUnsafeOkHttpClient$lambda3;
        m46getUnsafeOkHttpClient$lambda3 = OkHttpClientInstance.Builder.m46getUnsafeOkHttpClient$lambda3(str, sSLSession);
        return m46getUnsafeOkHttpClient$lambda3;
    }
}

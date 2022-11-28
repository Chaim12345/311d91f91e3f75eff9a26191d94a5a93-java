package com.psa.mym.mycitroenconnect.api.retrofit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
/* loaded from: classes.dex */
public final /* synthetic */ class a implements HostnameVerifier {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ a f10359a = new a();

    private /* synthetic */ a() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        boolean m44getUnsafeOkHttpClient$lambda0;
        m44getUnsafeOkHttpClient$lambda0 = ApiClient.m44getUnsafeOkHttpClient$lambda0(str, sSLSession);
        return m44getUnsafeOkHttpClient$lambda0;
    }
}

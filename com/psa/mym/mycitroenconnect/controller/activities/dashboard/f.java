package com.psa.mym.mycitroenconnect.controller.activities.dashboard;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
/* loaded from: classes2.dex */
public final /* synthetic */ class f implements HostnameVerifier {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ f f10395a = new f();

    private /* synthetic */ f() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        boolean m89getUnsafeOkHttpClient$lambda6;
        m89getUnsafeOkHttpClient$lambda6 = LocateCarActivity.m89getUnsafeOkHttpClient$lambda6(str, sSLSession);
        return m89getUnsafeOkHttpClient$lambda6;
    }
}

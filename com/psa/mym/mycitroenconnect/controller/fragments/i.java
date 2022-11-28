package com.psa.mym.mycitroenconnect.controller.fragments;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
/* loaded from: classes3.dex */
public final /* synthetic */ class i implements HostnameVerifier {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ i f10560a = new i();

    private /* synthetic */ i() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        boolean m132getUnsafeOkHttpClient$lambda22;
        m132getUnsafeOkHttpClient$lambda22 = HomeFragment.m132getUnsafeOkHttpClient$lambda22(str, sSLSession);
        return m132getUnsafeOkHttpClient$lambda22;
    }
}

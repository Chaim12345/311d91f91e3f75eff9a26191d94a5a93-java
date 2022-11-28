package com.google.android.gms.measurement.internal;

import android.os.Bundle;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zziy implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Bundle f6897a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zziw f6898b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zziw f6899c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ long f6900d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzje f6901e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zziy(zzje zzjeVar, Bundle bundle, zziw zziwVar, zziw zziwVar2, long j2) {
        this.f6901e = zzjeVar;
        this.f6897a = bundle;
        this.f6898b = zziwVar;
        this.f6899c = zziwVar2;
        this.f6900d = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzje.h(this.f6901e, this.f6897a, this.f6898b, this.f6899c, this.f6900d);
    }
}

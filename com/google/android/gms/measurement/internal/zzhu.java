package com.google.android.gms.measurement.internal;

import android.os.Bundle;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzhu implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f6824a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6825b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ long f6826c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Bundle f6827d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ boolean f6828e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ boolean f6829f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ boolean f6830g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ String f6831h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ zzip f6832i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhu(zzip zzipVar, String str, String str2, long j2, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        this.f6832i = zzipVar;
        this.f6824a = str;
        this.f6825b = str2;
        this.f6826c = j2;
        this.f6827d = bundle;
        this.f6828e = z;
        this.f6829f = z2;
        this.f6830g = z3;
        this.f6831h = str3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6832i.h(this.f6824a, this.f6825b, this.f6826c, this.f6827d, this.f6828e, this.f6829f, this.f6830g, this.f6831h);
    }
}

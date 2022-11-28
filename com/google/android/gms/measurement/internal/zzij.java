package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzps;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzij implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzai f6871a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ long f6872b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ int f6873c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ long f6874d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ boolean f6875e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzai f6876f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ zzip f6877g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzij(zzip zzipVar, zzai zzaiVar, long j2, int i2, long j3, boolean z, zzai zzaiVar2) {
        this.f6877g = zzipVar;
        this.f6871a = zzaiVar;
        this.f6872b = j2;
        this.f6873c = i2;
        this.f6874d = j3;
        this.f6875e = z;
        this.f6876f = zzaiVar2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6877g.m(this.f6871a);
        this.f6877g.i(this.f6872b, false);
        zzip.p(this.f6877g, this.f6871a, this.f6873c, this.f6874d, true, this.f6875e);
        zzps.zzc();
        if (this.f6877g.f6809a.zzf().zzs(null, zzen.zzaI)) {
            zzip.o(this.f6877g, this.f6871a, this.f6876f);
        }
    }
}

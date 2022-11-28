package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzps;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzik implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzai f6878a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ int f6879b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ long f6880c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ boolean f6881d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzai f6882e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzip f6883f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzik(zzip zzipVar, zzai zzaiVar, int i2, long j2, boolean z, zzai zzaiVar2) {
        this.f6883f = zzipVar;
        this.f6878a = zzaiVar;
        this.f6879b = i2;
        this.f6880c = j2;
        this.f6881d = z;
        this.f6882e = zzaiVar2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6883f.m(this.f6878a);
        zzip.p(this.f6883f, this.f6878a, this.f6879b, this.f6880c, false, this.f6881d);
        zzps.zzc();
        if (this.f6883f.f6809a.zzf().zzs(null, zzen.zzaI)) {
            zzip.o(this.f6883f, this.f6878a, this.f6882e);
        }
    }
}

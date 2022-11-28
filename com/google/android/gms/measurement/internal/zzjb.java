package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjb implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ long f6912a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzje f6913b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjb(zzje zzjeVar, long j2) {
        this.f6913b = zzjeVar;
        this.f6912a = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6913b.f6809a.zzd().zzf(this.f6912a);
        this.f6913b.f6918b = null;
    }
}

package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzjc implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zziw f6914a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ long f6915b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzje f6916c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjc(zzje zzjeVar, zziw zziwVar, long j2) {
        this.f6916c = zzjeVar;
        this.f6914a = zziwVar;
        this.f6915b = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6916c.zzC(this.f6914a, false, this.f6915b);
        zzje zzjeVar = this.f6916c;
        zzjeVar.f6918b = null;
        zzjeVar.f6809a.zzt().h(null);
    }
}

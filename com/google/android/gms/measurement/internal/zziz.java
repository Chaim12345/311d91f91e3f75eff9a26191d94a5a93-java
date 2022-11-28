package com.google.android.gms.measurement.internal;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zziz implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zziw f6902a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zziw f6903b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ long f6904c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ boolean f6905d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzje f6906e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zziz(zzje zzjeVar, zziw zziwVar, zziw zziwVar2, long j2, boolean z) {
        this.f6906e = zzjeVar;
        this.f6902a = zziwVar;
        this.f6903b = zziwVar2;
        this.f6904c = j2;
        this.f6905d = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6906e.zzB(this.f6902a, this.f6903b, this.f6904c, this.f6905d, null);
    }
}

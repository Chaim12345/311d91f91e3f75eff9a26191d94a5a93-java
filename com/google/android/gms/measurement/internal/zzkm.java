package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzkm implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ long f7001a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzku f7002b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkm(zzku zzkuVar, long j2) {
        this.f7002b = zzkuVar;
        this.f7001a = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzku.g(this.f7002b, this.f7001a);
    }
}

package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzkn implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ long f7003a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzku f7004b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkn(zzku zzkuVar, long j2) {
        this.f7004b = zzkuVar;
        this.f7003a = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzku.f(this.f7004b, this.f7003a);
    }
}

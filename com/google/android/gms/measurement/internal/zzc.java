package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzc implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ long f6714a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzd f6715b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(zzd zzdVar, long j2) {
        this.f6715b = zzdVar;
        this.f6714a = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6715b.zzj(this.f6714a);
    }
}

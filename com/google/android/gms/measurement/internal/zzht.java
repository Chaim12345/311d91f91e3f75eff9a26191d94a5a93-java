package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzht implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ long f6822a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzip f6823b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzht(zzip zzipVar, long j2) {
        this.f6823b = zzipVar;
        this.f6822a = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6823b.f6809a.zzm().zzf.zzb(this.f6822a);
        this.f6823b.f6809a.zzay().zzc().zzb("Session timeout duration set", Long.valueOf(this.f6822a));
    }
}

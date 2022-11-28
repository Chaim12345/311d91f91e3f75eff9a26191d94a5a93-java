package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzgv implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzaw f6788a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzq f6789b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzhc f6790c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgv(zzhc zzhcVar, zzaw zzawVar, zzq zzqVar) {
        this.f6790c = zzhcVar;
        this.f6788a = zzawVar;
        this.f6789b = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f6790c.d(this.f6790c.b(this.f6788a, this.f6789b), this.f6789b);
    }
}

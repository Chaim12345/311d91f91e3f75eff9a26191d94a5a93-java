package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzfh implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ boolean f6729a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzfi f6730b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfh(zzfi zzfiVar, boolean z) {
        this.f6730b = zzfiVar;
        this.f6729a = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzll zzllVar;
        zzllVar = this.f6730b.zzb;
        zzllVar.i(this.f6729a);
    }
}

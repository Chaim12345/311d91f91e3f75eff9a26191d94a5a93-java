package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzha implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzq f6802a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzhc f6803b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzha(zzhc zzhcVar, zzq zzqVar) {
        this.f6803b = zzhcVar;
        this.f6802a = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzll zzllVar;
        zzll zzllVar2;
        zzllVar = this.f6803b.zza;
        zzllVar.a();
        zzllVar2 = this.f6803b.zza;
        zzllVar2.k(this.f6802a);
    }
}

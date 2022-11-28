package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzgs implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzq f6782a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzhc f6783b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgs(zzhc zzhcVar, zzq zzqVar) {
        this.f6783b = zzhcVar;
        this.f6782a = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzll zzllVar;
        zzll zzllVar2;
        zzllVar = this.f6783b.zza;
        zzllVar.a();
        zzllVar2 = this.f6783b.zza;
        zzllVar2.p(this.f6782a);
    }
}

package com.google.android.gms.measurement.internal;
/* loaded from: classes2.dex */
final class zzgm implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzac f6761a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzq f6762b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzhc f6763c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgm(zzhc zzhcVar, zzac zzacVar, zzq zzqVar) {
        this.f6763c = zzhcVar;
        this.f6761a = zzacVar;
        this.f6762b = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzll zzllVar;
        zzll zzllVar2;
        zzll zzllVar3;
        zzllVar = this.f6763c.zza;
        zzllVar.a();
        if (this.f6761a.zzc.zza() == null) {
            zzllVar3 = this.f6763c.zza;
            zzllVar3.n(this.f6761a, this.f6762b);
            return;
        }
        zzllVar2 = this.f6763c.zza;
        zzllVar2.s(this.f6761a, this.f6762b);
    }
}

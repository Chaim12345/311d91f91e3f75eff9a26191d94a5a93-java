package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
/* loaded from: classes2.dex */
final class zzgu implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzq f6786a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzhc f6787b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgu(zzhc zzhcVar, zzq zzqVar) {
        this.f6787b = zzhcVar;
        this.f6786a = zzqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzll zzllVar;
        zzll zzllVar2;
        zzllVar = this.f6787b.zza;
        zzllVar.a();
        zzllVar2 = this.f6787b.zza;
        zzq zzqVar = this.f6786a;
        zzllVar2.zzaz().zzg();
        zzllVar2.b();
        Preconditions.checkNotEmpty(zzqVar.zza);
        zzai zzb = zzai.zzb(zzqVar.zzv);
        zzai C = zzllVar2.C(zzqVar.zza);
        zzllVar2.zzay().zzj().zzc("Setting consent, package, consent", zzqVar.zza, zzb);
        zzllVar2.t(zzqVar.zza, zzb);
        if (zzb.zzk(C)) {
            zzllVar2.p(zzqVar);
        }
    }
}

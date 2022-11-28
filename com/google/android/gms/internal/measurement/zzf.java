package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
public final class zzf {

    /* renamed from: a  reason: collision with root package name */
    final zzax f6053a;

    /* renamed from: b  reason: collision with root package name */
    final zzg f6054b;

    /* renamed from: c  reason: collision with root package name */
    final zzg f6055c;

    /* renamed from: d  reason: collision with root package name */
    final zzj f6056d;

    public zzf() {
        zzax zzaxVar = new zzax();
        this.f6053a = zzaxVar;
        zzg zzgVar = new zzg(null, zzaxVar);
        this.f6055c = zzgVar;
        this.f6054b = zzgVar.zza();
        zzj zzjVar = new zzj();
        this.f6056d = zzjVar;
        zzgVar.zzg("require", new zzw(zzjVar));
        zzjVar.zza("internal.platform", zze.zza);
        zzgVar.zzg("runtime.counter", new zzah(Double.valueOf(0.0d)));
    }

    public final zzap zza(zzg zzgVar, zzgx... zzgxVarArr) {
        zzap zzapVar = zzap.zzf;
        for (zzgx zzgxVar : zzgxVarArr) {
            zzapVar = zzi.zza(zzgxVar);
            zzh.zzc(this.f6055c);
            if ((zzapVar instanceof zzaq) || (zzapVar instanceof zzao)) {
                zzapVar = this.f6053a.zza(zzgVar, zzapVar);
            }
        }
        return zzapVar;
    }
}

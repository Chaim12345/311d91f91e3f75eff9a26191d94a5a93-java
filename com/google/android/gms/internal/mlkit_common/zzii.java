package com.google.android.gms.internal.mlkit_common;

import kotlin.jvm.internal.LongCompanionObject;
/* loaded from: classes.dex */
public final class zzii {
    private zziu zza;
    private Long zzb;
    private zzid zzc;
    private Long zzd;
    private zzij zze;
    private Long zzf;

    public static /* bridge */ /* synthetic */ zzid a(zzii zziiVar) {
        return zziiVar.zzc;
    }

    public static /* bridge */ /* synthetic */ zzij b(zzii zziiVar) {
        return zziiVar.zze;
    }

    public static /* bridge */ /* synthetic */ zziu c(zzii zziiVar) {
        return zziiVar.zza;
    }

    public static /* bridge */ /* synthetic */ Long d(zzii zziiVar) {
        return zziiVar.zzf;
    }

    public static /* bridge */ /* synthetic */ Long e(zzii zziiVar) {
        return zziiVar.zzd;
    }

    public static /* bridge */ /* synthetic */ Long f(zzii zziiVar) {
        return zziiVar.zzb;
    }

    public final zzii zzb(Long l2) {
        this.zzf = l2;
        return this;
    }

    public final zzii zzc(zzij zzijVar) {
        this.zze = zzijVar;
        return this;
    }

    public final zzii zzd(zzid zzidVar) {
        this.zzc = zzidVar;
        return this;
    }

    public final zzii zze(Long l2) {
        this.zzd = Long.valueOf(l2.longValue() & LongCompanionObject.MAX_VALUE);
        return this;
    }

    public final zzii zzf(zziu zziuVar) {
        this.zza = zziuVar;
        return this;
    }

    public final zzii zzg(Long l2) {
        this.zzb = Long.valueOf(l2.longValue() & LongCompanionObject.MAX_VALUE);
        return this;
    }

    public final zzil zzi() {
        return new zzil(this, null);
    }
}

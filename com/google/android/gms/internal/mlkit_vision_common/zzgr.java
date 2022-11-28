package com.google.android.gms.internal.mlkit_vision_common;

import kotlin.jvm.internal.LongCompanionObject;
/* loaded from: classes2.dex */
public final class zzgr {
    private Long zza;
    private zzgs zzb;
    private zzgn zzc;
    private Integer zzd;
    private Integer zze;
    private Integer zzf;
    private Integer zzg;

    public final zzgr zzb(Long l2) {
        this.zza = Long.valueOf(l2.longValue() & LongCompanionObject.MAX_VALUE);
        return this;
    }

    public final zzgr zzc(Integer num) {
        this.zzd = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzgr zzd(zzgn zzgnVar) {
        this.zzc = zzgnVar;
        return this;
    }

    public final zzgr zze(Integer num) {
        this.zzf = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzgr zzf(zzgs zzgsVar) {
        this.zzb = zzgsVar;
        return this;
    }

    public final zzgr zzg(Integer num) {
        this.zze = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzgr zzh(Integer num) {
        this.zzg = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzgu zzj() {
        return new zzgu(this, null);
    }
}

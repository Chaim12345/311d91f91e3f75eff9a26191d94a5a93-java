package com.google.android.gms.internal.mlkit_vision_barcode;

import kotlin.jvm.internal.LongCompanionObject;
/* loaded from: classes2.dex */
public final class zzjq {
    private Long zza;
    private Long zzb;
    private Long zzc;
    private Long zzd;
    private Long zze;
    private Long zzf;

    public final zzjq zza(Long l2) {
        this.zzc = Long.valueOf(l2.longValue() & LongCompanionObject.MAX_VALUE);
        return this;
    }

    public final zzjq zzb(Long l2) {
        this.zzd = Long.valueOf(l2.longValue() & LongCompanionObject.MAX_VALUE);
        return this;
    }

    public final zzjq zzc(Long l2) {
        this.zza = Long.valueOf(l2.longValue() & LongCompanionObject.MAX_VALUE);
        return this;
    }

    public final zzjq zzd(Long l2) {
        this.zze = Long.valueOf(l2.longValue() & LongCompanionObject.MAX_VALUE);
        return this;
    }

    public final zzjq zze(Long l2) {
        this.zzb = Long.valueOf(l2.longValue() & LongCompanionObject.MAX_VALUE);
        return this;
    }

    public final zzjq zzf(Long l2) {
        this.zzf = Long.valueOf(l2.longValue() & LongCompanionObject.MAX_VALUE);
        return this;
    }

    public final zzjs zzg() {
        return new zzjs(this, null);
    }
}

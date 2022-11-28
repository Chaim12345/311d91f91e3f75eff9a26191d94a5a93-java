package com.google.android.gms.internal.mlkit_vision_barcode;

import kotlin.jvm.internal.LongCompanionObject;
/* loaded from: classes2.dex */
public final class zzka {
    private Long zza;
    private zzkj zzb;
    private Boolean zzc;
    private Boolean zzd;
    private Boolean zze;

    public final zzka zza(Boolean bool) {
        this.zzd = bool;
        return this;
    }

    public final zzka zzb(Boolean bool) {
        this.zze = bool;
        return this;
    }

    public final zzka zzc(Long l2) {
        this.zza = Long.valueOf(l2.longValue() & LongCompanionObject.MAX_VALUE);
        return this;
    }

    public final zzka zzd(zzkj zzkjVar) {
        this.zzb = zzkjVar;
        return this;
    }

    public final zzka zze(Boolean bool) {
        this.zzc = bool;
        return this;
    }

    public final zzkc zzf() {
        return new zzkc(this, null);
    }
}

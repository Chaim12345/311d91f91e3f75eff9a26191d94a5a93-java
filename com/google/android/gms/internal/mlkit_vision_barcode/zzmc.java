package com.google.android.gms.internal.mlkit_vision_barcode;
/* loaded from: classes2.dex */
public final class zzmc {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private zzcc zze;
    private String zzf;
    private Boolean zzg;
    private Boolean zzh;
    private Boolean zzi;
    private Integer zzj;
    private Integer zzk;

    public final zzmc zzb(String str) {
        this.zza = str;
        return this;
    }

    public final zzmc zzc(String str) {
        this.zzb = str;
        return this;
    }

    public final zzmc zzd(Integer num) {
        this.zzj = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzmc zze(Boolean bool) {
        this.zzg = bool;
        return this;
    }

    public final zzmc zzf(Boolean bool) {
        this.zzi = bool;
        return this;
    }

    public final zzmc zzg(Boolean bool) {
        this.zzh = bool;
        return this;
    }

    public final zzmc zzh(zzcc zzccVar) {
        this.zze = zzccVar;
        return this;
    }

    public final zzmc zzi(String str) {
        this.zzf = str;
        return this;
    }

    public final zzmc zzj(String str) {
        this.zzc = str;
        return this;
    }

    public final zzmc zzk(Integer num) {
        this.zzk = num;
        return this;
    }

    public final zzmc zzl(String str) {
        this.zzd = str;
        return this;
    }

    public final zzme zzm() {
        return new zzme(this, null);
    }
}

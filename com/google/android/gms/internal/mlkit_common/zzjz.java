package com.google.android.gms.internal.mlkit_common;
/* loaded from: classes.dex */
public final class zzjz {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private zzam zze;
    private String zzf;
    private Boolean zzg;
    private Boolean zzh;
    private Boolean zzi;
    private Integer zzj;
    private Integer zzk;

    public final zzjz zzb(String str) {
        this.zza = str;
        return this;
    }

    public final zzjz zzc(String str) {
        this.zzb = str;
        return this;
    }

    public final zzjz zzd(Integer num) {
        this.zzj = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzjz zze(Boolean bool) {
        this.zzg = bool;
        return this;
    }

    public final zzjz zzf(Boolean bool) {
        this.zzi = bool;
        return this;
    }

    public final zzjz zzg(Boolean bool) {
        this.zzh = bool;
        return this;
    }

    public final zzjz zzh(zzam zzamVar) {
        this.zze = zzamVar;
        return this;
    }

    public final zzjz zzi(String str) {
        this.zzf = str;
        return this;
    }

    public final zzjz zzj(String str) {
        this.zzc = str;
        return this;
    }

    public final zzjz zzk(Integer num) {
        this.zzk = num;
        return this;
    }

    public final zzjz zzl(String str) {
        this.zzd = str;
        return this;
    }

    public final zzkb zzm() {
        return new zzkb(this, null);
    }
}

package com.google.android.gms.internal.mlkit_vision_common;
/* loaded from: classes2.dex */
public final class zzil {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private zzp zze;
    private String zzf;
    private Boolean zzg;
    private Boolean zzh;
    private Boolean zzi;
    private Integer zzj;
    private Integer zzk;

    public final zzil zzb(String str) {
        this.zza = str;
        return this;
    }

    public final zzil zzc(String str) {
        this.zzb = str;
        return this;
    }

    public final zzil zzd(Integer num) {
        this.zzj = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzil zze(Boolean bool) {
        this.zzg = bool;
        return this;
    }

    public final zzil zzf(Boolean bool) {
        this.zzi = bool;
        return this;
    }

    public final zzil zzg(Boolean bool) {
        this.zzh = bool;
        return this;
    }

    public final zzil zzh(zzp zzpVar) {
        this.zze = zzpVar;
        return this;
    }

    public final zzil zzi(String str) {
        this.zzf = str;
        return this;
    }

    public final zzil zzj(String str) {
        this.zzc = str;
        return this;
    }

    public final zzil zzk(Integer num) {
        this.zzk = num;
        return this;
    }

    public final zzil zzl(String str) {
        this.zzd = str;
        return this;
    }

    public final zzin zzm() {
        return new zzin(this, null);
    }
}

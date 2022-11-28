package com.google.android.gms.internal.mlkit_common;
/* loaded from: classes.dex */
public final class zzim {
    private String zza;
    private zzio zzb;
    private String zzc;
    private zzin zzd;

    public final zzim zza(String str) {
        this.zzc = str;
        return this;
    }

    public final zzim zzb(zzin zzinVar) {
        this.zzd = zzinVar;
        return this;
    }

    public final zzim zzc(String str) {
        this.zza = str;
        return this;
    }

    public final zzim zzd(zzio zzioVar) {
        this.zzb = zzioVar;
        return this;
    }

    public final zziq zzg() {
        return new zziq(this, null);
    }
}

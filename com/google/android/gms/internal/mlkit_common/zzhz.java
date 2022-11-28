package com.google.android.gms.internal.mlkit_common;
/* loaded from: classes.dex */
public enum zzhz implements zzbg {
    UNKNOWN(0),
    TRANSLATE(1);
    
    private final int zzd;

    zzhz(int i2) {
        this.zzd = i2;
    }

    public static zzhz zzb(int i2) {
        zzhz[] values;
        for (zzhz zzhzVar : values()) {
            if (zzhzVar.zzd == i2) {
                return zzhzVar;
            }
        }
        return UNKNOWN;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbg
    public final int zza() {
        return this.zzd;
    }
}

package com.google.android.gms.internal.mlkit_common;
/* loaded from: classes.dex */
public enum zzio implements zzbg {
    SOURCE_UNKNOWN(0),
    APP_ASSET(1),
    LOCAL(2),
    CLOUD(3),
    SDK_BUILT_IN(4),
    URI(5);
    
    private final int zzh;

    zzio(int i2) {
        this.zzh = i2;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbg
    public final int zza() {
        return this.zzh;
    }
}

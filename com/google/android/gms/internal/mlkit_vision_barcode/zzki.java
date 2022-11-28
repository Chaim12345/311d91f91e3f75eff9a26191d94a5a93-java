package com.google.android.gms.internal.mlkit_vision_barcode;
/* loaded from: classes2.dex */
public enum zzki implements zzdh {
    TYPE_UNKNOWN(0),
    TYPE_THIN(1),
    TYPE_THICK(2),
    TYPE_GMV(3);
    
    private final int zzf;

    zzki(int i2) {
        this.zzf = i2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzdh
    public final int zza() {
        return this.zzf;
    }
}

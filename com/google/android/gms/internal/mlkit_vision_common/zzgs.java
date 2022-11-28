package com.google.android.gms.internal.mlkit_vision_common;
/* loaded from: classes2.dex */
public enum zzgs implements zzaf {
    SOURCE_UNKNOWN(0),
    BITMAP(1),
    BYTEARRAY(2),
    BYTEBUFFER(3),
    FILEPATH(4),
    ANDROID_MEDIA_IMAGE(5);
    
    private final int zzh;

    zzgs(int i2) {
        this.zzh = i2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzaf
    public final int zza() {
        return this.zzh;
    }
}

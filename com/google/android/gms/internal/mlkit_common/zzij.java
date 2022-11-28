package com.google.android.gms.internal.mlkit_common;
/* loaded from: classes.dex */
public enum zzij implements zzbg {
    UNKNOWN_STATUS(0),
    EXPLICITLY_REQUESTED(1),
    IMPLICITLY_REQUESTED(2),
    MODEL_INFO_RETRIEVAL_SUCCEEDED(3),
    MODEL_INFO_RETRIEVAL_FAILED(4),
    SCHEDULED(5),
    DOWNLOADING(6),
    SUCCEEDED(7),
    FAILED(8),
    LIVE(9),
    UPDATE_AVAILABLE(10),
    DOWNLOADED(11),
    STARTED(12);
    
    private final int zzo;

    zzij(int i2) {
        this.zzo = i2;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbg
    public final int zza() {
        return this.zzo;
    }
}

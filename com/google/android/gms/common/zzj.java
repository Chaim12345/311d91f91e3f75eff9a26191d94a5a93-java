package com.google.android.gms.common;

import java.util.Arrays;
/* loaded from: classes.dex */
final class zzj extends zzi {
    private final byte[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzj(byte[] bArr) {
        super(Arrays.copyOfRange(bArr, 0, 25));
        this.zza = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.common.zzi
    public final byte[] c() {
        return this.zza;
    }
}

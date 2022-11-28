package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public final class zzny {
    @Nullable
    private static zzny zza;

    private zzny() {
    }

    public static synchronized zzny zza() {
        zzny zznyVar;
        synchronized (zzny.class) {
            if (zza == null) {
                zza = new zzny();
            }
            zznyVar = zza;
        }
        return zznyVar;
    }
}

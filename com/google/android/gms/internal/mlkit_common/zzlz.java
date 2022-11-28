package com.google.android.gms.internal.mlkit_common;

import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public final class zzlz {
    @Nullable
    private static zzlz zza;

    private zzlz() {
    }

    public static synchronized zzlz zza() {
        zzlz zzlzVar;
        synchronized (zzlz.class) {
            if (zza == null) {
                zza = new zzlz();
            }
            zzlzVar = zza;
        }
        return zzlzVar;
    }

    public static void zzb() {
        zzly.a();
    }
}

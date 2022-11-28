package com.google.android.gms.internal.mlkit_vision_common;

import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public final class zzkg {
    @Nullable
    private static zzkg zza;

    private zzkg() {
    }

    public static synchronized zzkg zza() {
        zzkg zzkgVar;
        synchronized (zzkg.class) {
            if (zza == null) {
                zza = new zzkg();
            }
            zzkgVar = zza;
        }
        return zzkgVar;
    }

    public static final boolean zzb() {
        return zzkf.a("mlkit-dev-profiling");
    }
}

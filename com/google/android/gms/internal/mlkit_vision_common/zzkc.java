package com.google.android.gms.internal.mlkit_vision_common;

import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public final class zzkc {
    @Nullable
    private static zzkb zza;

    public static synchronized zzjt zza(zzjn zzjnVar) {
        zzjt zzjtVar;
        synchronized (zzkc.class) {
            if (zza == null) {
                zza = new zzkb(null);
            }
            zzjtVar = (zzjt) zza.get(zzjnVar);
        }
        return zzjtVar;
    }

    public static synchronized zzjt zzb(String str) {
        zzjt zza2;
        synchronized (zzkc.class) {
            zza2 = zza(zzjn.zzd("vision-common").zzd());
        }
        return zza2;
    }
}

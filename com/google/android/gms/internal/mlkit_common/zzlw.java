package com.google.android.gms.internal.mlkit_common;

import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public final class zzlw {
    @Nullable
    private static zzlv zza;

    public static synchronized zzll zza(zzle zzleVar) {
        zzll zzllVar;
        synchronized (zzlw.class) {
            if (zza == null) {
                zza = new zzlv(null);
            }
            zzllVar = (zzll) zza.get(zzleVar);
        }
        return zzllVar;
    }

    public static synchronized zzll zzb(String str) {
        zzll zza2;
        synchronized (zzlw.class) {
            zza2 = zza(zzle.zzd("common").zzd());
        }
        return zza2;
    }
}

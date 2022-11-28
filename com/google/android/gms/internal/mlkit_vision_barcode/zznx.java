package com.google.android.gms.internal.mlkit_vision_barcode;

import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public final class zznx {
    @Nullable
    private static zznw zza;

    public static synchronized zznm zza(zzne zzneVar) {
        zznm zznmVar;
        synchronized (zznx.class) {
            if (zza == null) {
                zza = new zznw(null);
            }
            zznmVar = (zznm) zza.get(zzneVar);
        }
        return zznmVar;
    }

    public static synchronized zznm zzb(String str) {
        zznm zza2;
        synchronized (zznx.class) {
            zza2 = zza(zzne.zzd(str).zzd());
        }
        return zza2;
    }
}

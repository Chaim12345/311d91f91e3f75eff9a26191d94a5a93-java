package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzfu {
    private static final zzfu zza = new zzfu();
    private final ConcurrentMap zzc = new ConcurrentHashMap();
    private final zzgc zzb = new zzfd();

    private zzfu() {
    }

    public static zzfu zza() {
        return zza;
    }

    public final zzgb zzb(Class cls) {
        zzel.c(cls, "messageType");
        zzgb zzgbVar = (zzgb) this.zzc.get(cls);
        if (zzgbVar == null) {
            zzgbVar = this.zzb.zza(cls);
            zzel.c(cls, "messageType");
            zzel.c(zzgbVar, "schema");
            zzgb zzgbVar2 = (zzgb) this.zzc.putIfAbsent(cls, zzgbVar);
            if (zzgbVar2 != null) {
                return zzgbVar2;
            }
        }
        return zzgbVar;
    }
}

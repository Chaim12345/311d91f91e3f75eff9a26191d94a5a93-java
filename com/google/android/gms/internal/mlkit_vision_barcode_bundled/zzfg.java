package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Iterator;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzfg {
    public static final int zza(int i2, Object obj, Object obj2) {
        zzff zzffVar = (zzff) obj;
        zzfe zzfeVar = (zzfe) obj2;
        if (zzffVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzffVar.entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entry.getKey();
            entry.getValue();
            throw null;
        }
        return 0;
    }

    public static final Object zzb(Object obj, Object obj2) {
        zzff zzffVar = (zzff) obj;
        zzff zzffVar2 = (zzff) obj2;
        if (!zzffVar2.isEmpty()) {
            if (!zzffVar.zze()) {
                zzffVar = zzffVar.zzb();
            }
            zzffVar.zzd(zzffVar2);
        }
        return zzffVar;
    }
}

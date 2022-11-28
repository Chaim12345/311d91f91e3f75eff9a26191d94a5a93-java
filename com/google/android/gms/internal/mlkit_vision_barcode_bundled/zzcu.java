package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Comparator;
/* loaded from: classes2.dex */
final class zzcu implements Comparator {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzdb zzdbVar = (zzdb) obj;
        zzdb zzdbVar2 = (zzdb) obj2;
        zzcx it = zzdbVar.iterator();
        zzcx it2 = zzdbVar2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            int zza = zzct.zza(it.zza() & 255, it2.zza() & 255);
            if (zza != 0) {
                return zza;
            }
        }
        return zzct.zza(zzdbVar.zzd(), zzdbVar2.zzd());
    }
}

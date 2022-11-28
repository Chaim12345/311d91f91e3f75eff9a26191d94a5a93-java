package com.google.android.libraries.places.internal;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzjh extends zzjk {
    private final Map zza;
    private final Map zzb;
    private final zzjj zzc;
    private final zzji zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzjh(zzjg zzjgVar, zzjd zzjdVar) {
        HashMap hashMap = new HashMap();
        this.zza = hashMap;
        HashMap hashMap2 = new HashMap();
        this.zzb = hashMap2;
        hashMap.putAll(zzjg.zze(zzjgVar));
        hashMap2.putAll(zzjg.zzf(zzjgVar));
        this.zzc = zzjg.zzc(zzjgVar);
        this.zzd = zzjg.zzb(zzjgVar);
    }
}

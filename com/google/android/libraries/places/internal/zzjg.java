package com.google.android.libraries.places.internal;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public final class zzjg {
    private static final zzjj zza = new zzje();
    private static final zzji zzb = new zzjf();
    private final zzjj zze;
    private final Map zzc = new HashMap();
    private final Map zzd = new HashMap();
    private zzji zzf = null;

    public final zzjg zza(zzji zzjiVar) {
        this.zzf = zzjiVar;
        return this;
    }

    public final zzjk zzd() {
        return new zzjh(this, null);
    }

    public final void zzg(zzix zzixVar) {
        Object obj;
        Map map;
        zzkt.zza(zzixVar, "key");
        if (zzixVar.zzb()) {
            obj = zzb;
            zzkt.zza(zzixVar, "key");
            if (!zzixVar.zzb()) {
                throw new IllegalArgumentException("key must be repeating");
            }
            this.zzc.remove(zzixVar);
            map = this.zzd;
        } else {
            obj = zza;
            zzkt.zza(zzixVar, "key");
            this.zzd.remove(zzixVar);
            map = this.zzc;
        }
        map.put(zzixVar, obj);
    }
}

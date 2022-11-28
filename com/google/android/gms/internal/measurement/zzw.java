package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
/* loaded from: classes.dex */
public final class zzw extends zzai {

    /* renamed from: c  reason: collision with root package name */
    final Map f6117c;
    private final zzj zzb;

    public zzw(zzj zzjVar) {
        super("require");
        this.f6117c = new HashMap();
        this.zzb = zzjVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzgVar, List list) {
        zzap zzapVar;
        zzh.zzh("require", 1, list);
        String zzi = zzgVar.zzb((zzap) list.get(0)).zzi();
        if (this.f6117c.containsKey(zzi)) {
            return (zzap) this.f6117c.get(zzi);
        }
        zzj zzjVar = this.zzb;
        if (zzjVar.f6094a.containsKey(zzi)) {
            try {
                zzapVar = (zzap) ((Callable) zzjVar.f6094a.get(zzi)).call();
            } catch (Exception unused) {
                throw new IllegalStateException("Failed to create API implementation: ".concat(String.valueOf(zzi)));
            }
        } else {
            zzapVar = zzap.zzf;
        }
        if (zzapVar instanceof zzai) {
            this.f6117c.put(zzi, (zzai) zzapVar);
        }
        return zzapVar;
    }
}

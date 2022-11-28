package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class zzax {

    /* renamed from: a  reason: collision with root package name */
    final Map f5934a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    final zzbj f5935b = new zzbj();

    public zzax() {
        a(new zzav());
        a(new zzay());
        a(new zzaz());
        a(new zzbc());
        a(new zzbh());
        a(new zzbi());
        a(new zzbk());
    }

    final void a(zzaw zzawVar) {
        for (zzbl zzblVar : zzawVar.f5933a) {
            this.f5934a.put(zzblVar.zzb().toString(), zzawVar);
        }
    }

    public final zzap zza(zzg zzgVar, zzap zzapVar) {
        zzh.zzc(zzgVar);
        if (zzapVar instanceof zzaq) {
            zzaq zzaqVar = (zzaq) zzapVar;
            ArrayList zzc = zzaqVar.zzc();
            String zzb = zzaqVar.zzb();
            return (this.f5934a.containsKey(zzb) ? (zzaw) this.f5934a.get(zzb) : this.f5935b).zza(zzb, zzgVar, zzc);
        }
        return zzapVar;
    }
}

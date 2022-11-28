package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class zzam implements zzap, zzal {

    /* renamed from: a  reason: collision with root package name */
    final Map f5927a = new HashMap();

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzam) {
            return this.f5927a.equals(((zzam) obj).f5927a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f5927a.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (!this.f5927a.isEmpty()) {
            for (String str : this.f5927a.keySet()) {
                sb.append(String.format("%s: %s,", str, this.f5927a.get(str)));
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        sb.append("}");
        return sb.toString();
    }

    public final List zzb() {
        return new ArrayList(this.f5927a.keySet());
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public zzap zzbQ(String str, zzg zzgVar, List list) {
        return "toString".equals(str) ? new zzat(toString()) : zzaj.zza(this, new zzat(str), zzgVar, list);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        Map map;
        String str;
        zzap zzd;
        zzam zzamVar = new zzam();
        for (Map.Entry entry : this.f5927a.entrySet()) {
            if (entry.getValue() instanceof zzal) {
                map = zzamVar.f5927a;
                str = (String) entry.getKey();
                zzd = (zzap) entry.getValue();
            } else {
                map = zzamVar.f5927a;
                str = (String) entry.getKey();
                zzd = ((zzap) entry.getValue()).zzd();
            }
            map.put(str, zzd);
        }
        return zzamVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzal
    public final zzap zzf(String str) {
        return this.f5927a.containsKey(str) ? (zzap) this.f5927a.get(str) : zzap.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        return Boolean.TRUE;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        return Double.valueOf(Double.NaN);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        return "[object Object]";
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Iterator zzl() {
        return zzaj.zzb(this.f5927a);
    }

    @Override // com.google.android.gms.internal.measurement.zzal
    public final void zzr(String str, zzap zzapVar) {
        if (zzapVar == null) {
            this.f5927a.remove(str);
        } else {
            this.f5927a.put(str, zzapVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzal
    public final boolean zzt(String str) {
        return this.f5927a.containsKey(str);
    }
}

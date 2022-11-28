package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class zzai implements zzap, zzal {

    /* renamed from: a  reason: collision with root package name */
    protected final String f5924a;

    /* renamed from: b  reason: collision with root package name */
    protected final Map f5925b = new HashMap();

    public zzai(String str) {
        this.f5924a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzai) {
            zzai zzaiVar = (zzai) obj;
            String str = this.f5924a;
            if (str != null) {
                return str.equals(zzaiVar.f5924a);
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        String str = this.f5924a;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public abstract zzap zza(zzg zzgVar, List list);

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzbQ(String str, zzg zzgVar, List list) {
        return "toString".equals(str) ? new zzat(this.f5924a) : zzaj.zza(this, new zzat(str), zzgVar, list);
    }

    public final String zzc() {
        return this.f5924a;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public zzap zzd() {
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zzal
    public final zzap zzf(String str) {
        return this.f5925b.containsKey(str) ? (zzap) this.f5925b.get(str) : zzap.zzf;
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
        return this.f5924a;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Iterator zzl() {
        return zzaj.zzb(this.f5925b);
    }

    @Override // com.google.android.gms.internal.measurement.zzal
    public final void zzr(String str, zzap zzapVar) {
        if (zzapVar == null) {
            this.f5925b.remove(str);
        } else {
            this.f5925b.put(str, zzapVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzal
    public final boolean zzt(String str) {
        return this.f5925b.containsKey(str);
    }
}

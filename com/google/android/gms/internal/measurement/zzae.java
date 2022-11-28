package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
/* loaded from: classes.dex */
public final class zzae implements Iterable, zzap, zzal {

    /* renamed from: a  reason: collision with root package name */
    final SortedMap f5922a;

    /* renamed from: b  reason: collision with root package name */
    final Map f5923b;

    public zzae() {
        this.f5922a = new TreeMap();
        this.f5923b = new TreeMap();
    }

    public zzae(List list) {
        this();
        if (list != null) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                zzq(i2, (zzap) list.get(i2));
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzae) {
            zzae zzaeVar = (zzae) obj;
            if (zzc() != zzaeVar.zzc()) {
                return false;
            }
            if (this.f5922a.isEmpty()) {
                return zzaeVar.f5922a.isEmpty();
            }
            for (int intValue = ((Integer) this.f5922a.firstKey()).intValue(); intValue <= ((Integer) this.f5922a.lastKey()).intValue(); intValue++) {
                if (!zze(intValue).equals(zzaeVar.zze(intValue))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f5922a.hashCode() * 31;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new zzad(this);
    }

    public final String toString() {
        return zzj(",");
    }

    public final int zzb() {
        return this.f5922a.size();
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzbQ(String str, zzg zzgVar, List list) {
        return ("concat".equals(str) || "every".equals(str) || "filter".equals(str) || "forEach".equals(str) || "indexOf".equals(str) || "join".equals(str) || "lastIndexOf".equals(str) || "map".equals(str) || "pop".equals(str) || "push".equals(str) || "reduce".equals(str) || "reduceRight".equals(str) || "reverse".equals(str) || "shift".equals(str) || "slice".equals(str) || "some".equals(str) || "sort".equals(str) || "splice".equals(str) || "toString".equals(str) || "unshift".equals(str)) ? zzbb.zza(str, this, zzgVar, list) : zzaj.zza(this, new zzat(str), zzgVar, list);
    }

    public final int zzc() {
        if (this.f5922a.isEmpty()) {
            return 0;
        }
        return ((Integer) this.f5922a.lastKey()).intValue() + 1;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        SortedMap sortedMap;
        Integer num;
        zzap zzd;
        zzae zzaeVar = new zzae();
        for (Map.Entry entry : this.f5922a.entrySet()) {
            if (entry.getValue() instanceof zzal) {
                sortedMap = zzaeVar.f5922a;
                num = (Integer) entry.getKey();
                zzd = (zzap) entry.getValue();
            } else {
                sortedMap = zzaeVar.f5922a;
                num = (Integer) entry.getKey();
                zzd = ((zzap) entry.getValue()).zzd();
            }
            sortedMap.put(num, zzd);
        }
        return zzaeVar;
    }

    public final zzap zze(int i2) {
        zzap zzapVar;
        if (i2 < zzc()) {
            return (!zzs(i2) || (zzapVar = (zzap) this.f5922a.get(Integer.valueOf(i2))) == null) ? zzap.zzf : zzapVar;
        }
        throw new IndexOutOfBoundsException("Attempting to get element outside of current array");
    }

    @Override // com.google.android.gms.internal.measurement.zzal
    public final zzap zzf(String str) {
        zzap zzapVar;
        return "length".equals(str) ? new zzah(Double.valueOf(zzc())) : (!zzt(str) || (zzapVar = (zzap) this.f5923b.get(str)) == null) ? zzap.zzf : zzapVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Boolean zzg() {
        return Boolean.TRUE;
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Double zzh() {
        return this.f5922a.size() == 1 ? zze(0).zzh() : this.f5922a.size() <= 0 ? Double.valueOf(0.0d) : Double.valueOf(Double.NaN);
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final String zzi() {
        return zzj(",");
    }

    public final String zzj(String str) {
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        if (!this.f5922a.isEmpty()) {
            for (int i2 = 0; i2 < zzc(); i2++) {
                zzap zze = zze(i2);
                sb.append(str);
                if (!(zze instanceof zzau) && !(zze instanceof zzan)) {
                    sb.append(zze.zzi());
                }
            }
            sb.delete(0, str.length());
        }
        return sb.toString();
    }

    public final Iterator zzk() {
        return this.f5922a.keySet().iterator();
    }

    @Override // com.google.android.gms.internal.measurement.zzap
    public final Iterator zzl() {
        return new zzac(this, this.f5922a.keySet().iterator(), this.f5923b.keySet().iterator());
    }

    public final List zzm() {
        ArrayList arrayList = new ArrayList(zzc());
        for (int i2 = 0; i2 < zzc(); i2++) {
            arrayList.add(zze(i2));
        }
        return arrayList;
    }

    public final void zzn() {
        this.f5922a.clear();
    }

    public final void zzo(int i2, zzap zzapVar) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Invalid value index: " + i2);
        } else if (i2 >= zzc()) {
            zzq(i2, zzapVar);
        } else {
            for (int intValue = ((Integer) this.f5922a.lastKey()).intValue(); intValue >= i2; intValue--) {
                SortedMap sortedMap = this.f5922a;
                Integer valueOf = Integer.valueOf(intValue);
                zzap zzapVar2 = (zzap) sortedMap.get(valueOf);
                if (zzapVar2 != null) {
                    zzq(intValue + 1, zzapVar2);
                    this.f5922a.remove(valueOf);
                }
            }
            zzq(i2, zzapVar);
        }
    }

    public final void zzp(int i2) {
        int intValue = ((Integer) this.f5922a.lastKey()).intValue();
        if (i2 > intValue || i2 < 0) {
            return;
        }
        this.f5922a.remove(Integer.valueOf(i2));
        if (i2 == intValue) {
            SortedMap sortedMap = this.f5922a;
            int i3 = i2 - 1;
            Integer valueOf = Integer.valueOf(i3);
            if (sortedMap.containsKey(valueOf) || i3 < 0) {
                return;
            }
            this.f5922a.put(valueOf, zzap.zzf);
            return;
        }
        while (true) {
            i2++;
            if (i2 > ((Integer) this.f5922a.lastKey()).intValue()) {
                return;
            }
            SortedMap sortedMap2 = this.f5922a;
            Integer valueOf2 = Integer.valueOf(i2);
            zzap zzapVar = (zzap) sortedMap2.get(valueOf2);
            if (zzapVar != null) {
                this.f5922a.put(Integer.valueOf(i2 - 1), zzapVar);
                this.f5922a.remove(valueOf2);
            }
        }
    }

    @RequiresNonNull({"elements"})
    public final void zzq(int i2, zzap zzapVar) {
        if (i2 > 32468) {
            throw new IllegalStateException("Array too large");
        }
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("Out of bounds index: " + i2);
        } else if (zzapVar == null) {
            this.f5922a.remove(Integer.valueOf(i2));
        } else {
            this.f5922a.put(Integer.valueOf(i2), zzapVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzal
    public final void zzr(String str, zzap zzapVar) {
        if (zzapVar == null) {
            this.f5923b.remove(str);
        } else {
            this.f5923b.put(str, zzapVar);
        }
    }

    public final boolean zzs(int i2) {
        if (i2 < 0 || i2 > ((Integer) this.f5922a.lastKey()).intValue()) {
            throw new IndexOutOfBoundsException("Out of bounds index: " + i2);
        }
        return this.f5922a.containsKey(Integer.valueOf(i2));
    }

    @Override // com.google.android.gms.internal.measurement.zzal
    public final boolean zzt(String str) {
        return "length".equals(str) || this.f5923b.containsKey(str);
    }
}

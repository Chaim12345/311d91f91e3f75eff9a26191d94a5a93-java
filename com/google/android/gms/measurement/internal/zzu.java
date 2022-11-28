package com.google.android.gms.measurement.internal;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzoi;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzu {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzaa f7055a;
    private String zzb;
    private boolean zzc;
    private com.google.android.gms.internal.measurement.zzgh zzd;
    private BitSet zze;
    private BitSet zzf;
    private Map zzg;
    private Map zzh;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzu(zzaa zzaaVar, String str, com.google.android.gms.internal.measurement.zzgh zzghVar, BitSet bitSet, BitSet bitSet2, Map map, Map map2, zzt zztVar) {
        this.f7055a = zzaaVar;
        this.zzb = str;
        this.zze = bitSet;
        this.zzf = bitSet2;
        this.zzg = map;
        this.zzh = new ArrayMap();
        for (Integer num : map2.keySet()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add((Long) map2.get(num));
            this.zzh.put(num, arrayList);
        }
        this.zzc = false;
        this.zzd = zzghVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzu(zzaa zzaaVar, String str, zzt zztVar) {
        this.f7055a = zzaaVar;
        this.zzb = str;
        this.zzc = true;
        this.zze = new BitSet();
        this.zzf = new BitSet();
        this.zzg = new ArrayMap();
        this.zzh = new ArrayMap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ BitSet b(zzu zzuVar) {
        return zzuVar.zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public final com.google.android.gms.internal.measurement.zzfo a(int i2) {
        ArrayList arrayList;
        List list;
        com.google.android.gms.internal.measurement.zzfn zzb = com.google.android.gms.internal.measurement.zzfo.zzb();
        zzb.zza(i2);
        zzb.zzc(this.zzc);
        com.google.android.gms.internal.measurement.zzgh zzghVar = this.zzd;
        if (zzghVar != null) {
            zzb.zzd(zzghVar);
        }
        com.google.android.gms.internal.measurement.zzgg zzf = com.google.android.gms.internal.measurement.zzgh.zzf();
        zzf.zzb(zzln.r(this.zze));
        zzf.zzd(zzln.r(this.zzf));
        Map map = this.zzg;
        if (map == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList(map.size());
            for (Integer num : this.zzg.keySet()) {
                int intValue = num.intValue();
                Long l2 = (Long) this.zzg.get(Integer.valueOf(intValue));
                if (l2 != null) {
                    com.google.android.gms.internal.measurement.zzfp zzc = com.google.android.gms.internal.measurement.zzfq.zzc();
                    zzc.zzb(intValue);
                    zzc.zza(l2.longValue());
                    arrayList2.add((com.google.android.gms.internal.measurement.zzfq) zzc.zzaE());
                }
            }
            arrayList = arrayList2;
        }
        if (arrayList != null) {
            zzf.zza(arrayList);
        }
        Map map2 = this.zzh;
        if (map2 == null) {
            list = Collections.emptyList();
        } else {
            ArrayList arrayList3 = new ArrayList(map2.size());
            for (Integer num2 : this.zzh.keySet()) {
                com.google.android.gms.internal.measurement.zzgi zzd = com.google.android.gms.internal.measurement.zzgj.zzd();
                zzd.zzb(num2.intValue());
                List list2 = (List) this.zzh.get(num2);
                if (list2 != null) {
                    Collections.sort(list2);
                    zzd.zza(list2);
                }
                arrayList3.add((com.google.android.gms.internal.measurement.zzgj) zzd.zzaE());
            }
            list = arrayList3;
        }
        zzf.zzc(list);
        zzb.zzb(zzf);
        return (com.google.android.gms.internal.measurement.zzfo) zzb.zzaE();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(@NonNull zzy zzyVar) {
        int a2 = zzyVar.a();
        Boolean bool = zzyVar.f7060c;
        if (bool != null) {
            this.zzf.set(a2, bool.booleanValue());
        }
        Boolean bool2 = zzyVar.f7061d;
        if (bool2 != null) {
            this.zze.set(a2, bool2.booleanValue());
        }
        if (zzyVar.f7062e != null) {
            Map map = this.zzg;
            Integer valueOf = Integer.valueOf(a2);
            Long l2 = (Long) map.get(valueOf);
            long longValue = zzyVar.f7062e.longValue() / 1000;
            if (l2 == null || longValue > l2.longValue()) {
                this.zzg.put(valueOf, Long.valueOf(longValue));
            }
        }
        if (zzyVar.f7063f != null) {
            Map map2 = this.zzh;
            Integer valueOf2 = Integer.valueOf(a2);
            List list = (List) map2.get(valueOf2);
            if (list == null) {
                list = new ArrayList();
                this.zzh.put(valueOf2, list);
            }
            if (zzyVar.c()) {
                list.clear();
            }
            zzoi.zzc();
            zzag zzf = this.f7055a.f6809a.zzf();
            String str = this.zzb;
            zzem zzemVar = zzen.zzX;
            if (zzf.zzs(str, zzemVar) && zzyVar.b()) {
                list.clear();
            }
            zzoi.zzc();
            boolean zzs = this.f7055a.f6809a.zzf().zzs(this.zzb, zzemVar);
            Long valueOf3 = Long.valueOf(zzyVar.f7063f.longValue() / 1000);
            if (!zzs) {
                list.add(valueOf3);
            } else if (list.contains(valueOf3)) {
            } else {
                list.add(valueOf3);
            }
        }
    }
}

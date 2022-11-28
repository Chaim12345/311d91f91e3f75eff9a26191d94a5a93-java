package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzs extends zzai {

    /* renamed from: c  reason: collision with root package name */
    final boolean f6114c;

    /* renamed from: d  reason: collision with root package name */
    final boolean f6115d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzt f6116e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzs(zzt zztVar, boolean z, boolean z2) {
        super("log");
        this.f6116e = zztVar;
        this.f6114c = z;
        this.f6115d = z2;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0081  */
    @Override // com.google.android.gms.internal.measurement.zzai
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final zzap zza(zzg zzgVar, List list) {
        int i2;
        List arrayList;
        zzr zzrVar;
        zzr zzrVar2;
        zzh.zzi("log", 1, list);
        if (list.size() == 1) {
            zzrVar2 = this.f6116e.zza;
            zzrVar2.zza(3, zzgVar.zzb((zzap) list.get(0)).zzi(), Collections.emptyList(), this.f6114c, this.f6115d);
        } else {
            int zzb = zzh.zzb(zzgVar.zzb((zzap) list.get(0)).zzh().doubleValue());
            int i3 = 3;
            if (zzb != 2) {
                if (zzb == 3) {
                    i2 = 1;
                } else if (zzb == 5) {
                    i2 = 5;
                } else if (zzb == 6) {
                    i2 = 2;
                }
                String zzi = zzgVar.zzb((zzap) list.get(1)).zzi();
                if (list.size() != 2) {
                    zzrVar = this.f6116e.zza;
                    arrayList = Collections.emptyList();
                } else {
                    arrayList = new ArrayList();
                    for (int i4 = 2; i4 < Math.min(list.size(), 5); i4++) {
                        arrayList.add(zzgVar.zzb((zzap) list.get(i4)).zzi());
                    }
                    zzrVar = this.f6116e.zza;
                }
                zzrVar.zza(i2, zzi, arrayList, this.f6114c, this.f6115d);
            } else {
                i3 = 4;
            }
            i2 = i3;
            String zzi2 = zzgVar.zzb((zzap) list.get(1)).zzi();
            if (list.size() != 2) {
            }
            zzrVar.zza(i2, zzi2, arrayList, this.f6114c, this.f6115d);
        }
        return zzap.zzf;
    }
}

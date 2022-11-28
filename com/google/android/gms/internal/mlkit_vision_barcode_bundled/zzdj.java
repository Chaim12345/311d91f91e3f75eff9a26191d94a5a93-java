package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.List;
/* loaded from: classes2.dex */
final class zzdj {
    private final zzdi zza;

    private zzdj(zzdi zzdiVar) {
        zzel.c(zzdiVar, "output");
        this.zza = zzdiVar;
        zzdiVar.f6413a = this;
    }

    public static zzdj zza(zzdi zzdiVar) {
        zzdj zzdjVar = zzdiVar.f6413a;
        return zzdjVar != null ? zzdjVar : new zzdj(zzdiVar);
    }

    public final void zzA(int i2, List list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.zza.zzh(i2, ((Long) list.get(i3)).longValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            ((Long) list.get(i5)).longValue();
            i4 += 8;
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzi(((Long) list.get(i3)).longValue());
            i3++;
        }
    }

    public final void zzB(int i2, int i3) {
        this.zza.zzp(i2, (i3 >> 31) ^ (i3 + i3));
    }

    public final void zzC(int i2, List list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                zzdi zzdiVar = this.zza;
                int intValue = ((Integer) list.get(i3)).intValue();
                zzdiVar.zzp(i2, (intValue >> 31) ^ (intValue + intValue));
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            int intValue2 = ((Integer) list.get(i5)).intValue();
            i4 += zzdi.zzD((intValue2 >> 31) ^ (intValue2 + intValue2));
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            zzdi zzdiVar2 = this.zza;
            int intValue3 = ((Integer) list.get(i3)).intValue();
            zzdiVar2.zzq((intValue3 >> 31) ^ (intValue3 + intValue3));
            i3++;
        }
    }

    public final void zzD(int i2, long j2) {
        this.zza.zzr(i2, (j2 >> 63) ^ (j2 + j2));
    }

    public final void zzE(int i2, List list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                zzdi zzdiVar = this.zza;
                long longValue = ((Long) list.get(i3)).longValue();
                zzdiVar.zzr(i2, (longValue >> 63) ^ (longValue + longValue));
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            long longValue2 = ((Long) list.get(i5)).longValue();
            i4 += zzdi.zzE((longValue2 >> 63) ^ (longValue2 + longValue2));
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            zzdi zzdiVar2 = this.zza;
            long longValue3 = ((Long) list.get(i3)).longValue();
            zzdiVar2.zzs((longValue3 >> 63) ^ (longValue3 + longValue3));
            i3++;
        }
    }

    @Deprecated
    public final void zzF(int i2) {
        this.zza.zzo(i2, 3);
    }

    public final void zzG(int i2, String str) {
        this.zza.zzm(i2, str);
    }

    public final void zzH(int i2, List list) {
        int i3 = 0;
        if (!(list instanceof zzev)) {
            while (i3 < list.size()) {
                this.zza.zzm(i2, (String) list.get(i3));
                i3++;
            }
            return;
        }
        zzev zzevVar = (zzev) list;
        while (i3 < list.size()) {
            Object zzf = zzevVar.zzf(i3);
            if (zzf instanceof String) {
                this.zza.zzm(i2, (String) zzf);
            } else {
                this.zza.zze(i2, (zzdb) zzf);
            }
            i3++;
        }
    }

    public final void zzI(int i2, int i3) {
        this.zza.zzp(i2, i3);
    }

    public final void zzJ(int i2, List list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.zza.zzp(i2, ((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += zzdi.zzD(((Integer) list.get(i5)).intValue());
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzq(((Integer) list.get(i3)).intValue());
            i3++;
        }
    }

    public final void zzK(int i2, long j2) {
        this.zza.zzr(i2, j2);
    }

    public final void zzL(int i2, List list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.zza.zzr(i2, ((Long) list.get(i3)).longValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += zzdi.zzE(((Long) list.get(i5)).longValue());
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzs(((Long) list.get(i3)).longValue());
            i3++;
        }
    }

    public final void zzb(int i2, boolean z) {
        this.zza.zzd(i2, z);
    }

    public final void zzc(int i2, List list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.zza.zzd(i2, ((Boolean) list.get(i3)).booleanValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            ((Boolean) list.get(i5)).booleanValue();
            i4++;
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzb(((Boolean) list.get(i3)).booleanValue() ? (byte) 1 : (byte) 0);
            i3++;
        }
    }

    public final void zzd(int i2, zzdb zzdbVar) {
        this.zza.zze(i2, zzdbVar);
    }

    public final void zze(int i2, List list) {
        for (int i3 = 0; i3 < list.size(); i3++) {
            this.zza.zze(i2, (zzdb) list.get(i3));
        }
    }

    public final void zzf(int i2, double d2) {
        this.zza.zzh(i2, Double.doubleToRawLongBits(d2));
    }

    public final void zzg(int i2, List list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.zza.zzh(i2, Double.doubleToRawLongBits(((Double) list.get(i3)).doubleValue()));
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            ((Double) list.get(i5)).doubleValue();
            i4 += 8;
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzi(Double.doubleToRawLongBits(((Double) list.get(i3)).doubleValue()));
            i3++;
        }
    }

    @Deprecated
    public final void zzh(int i2) {
        this.zza.zzo(i2, 4);
    }

    public final void zzi(int i2, int i3) {
        this.zza.zzj(i2, i3);
    }

    public final void zzj(int i2, List list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.zza.zzj(i2, ((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += zzdi.zzx(((Integer) list.get(i5)).intValue());
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzk(((Integer) list.get(i3)).intValue());
            i3++;
        }
    }

    public final void zzk(int i2, int i3) {
        this.zza.zzf(i2, i3);
    }

    public final void zzl(int i2, List list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.zza.zzf(i2, ((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            ((Integer) list.get(i5)).intValue();
            i4 += 4;
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzg(((Integer) list.get(i3)).intValue());
            i3++;
        }
    }

    public final void zzm(int i2, long j2) {
        this.zza.zzh(i2, j2);
    }

    public final void zzn(int i2, List list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.zza.zzh(i2, ((Long) list.get(i3)).longValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            ((Long) list.get(i5)).longValue();
            i4 += 8;
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzi(((Long) list.get(i3)).longValue());
            i3++;
        }
    }

    public final void zzo(int i2, float f2) {
        this.zza.zzf(i2, Float.floatToRawIntBits(f2));
    }

    public final void zzp(int i2, List list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.zza.zzf(i2, Float.floatToRawIntBits(((Float) list.get(i3)).floatValue()));
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            ((Float) list.get(i5)).floatValue();
            i4 += 4;
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzg(Float.floatToRawIntBits(((Float) list.get(i3)).floatValue()));
            i3++;
        }
    }

    public final void zzq(int i2, Object obj, zzgb zzgbVar) {
        zzdi zzdiVar = this.zza;
        zzdiVar.zzo(i2, 3);
        zzgbVar.zzm((zzfl) obj, zzdiVar.f6413a);
        zzdiVar.zzo(i2, 4);
    }

    public final void zzr(int i2, int i3) {
        this.zza.zzj(i2, i3);
    }

    public final void zzs(int i2, List list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.zza.zzj(i2, ((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += zzdi.zzx(((Integer) list.get(i5)).intValue());
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzk(((Integer) list.get(i3)).intValue());
            i3++;
        }
    }

    public final void zzt(int i2, long j2) {
        this.zza.zzr(i2, j2);
    }

    public final void zzu(int i2, List list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.zza.zzr(i2, ((Long) list.get(i3)).longValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            i4 += zzdi.zzE(((Long) list.get(i5)).longValue());
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzs(((Long) list.get(i3)).longValue());
            i3++;
        }
    }

    public final void zzv(int i2, Object obj, zzgb zzgbVar) {
        Object obj2 = (zzfl) obj;
        zzdg zzdgVar = (zzdg) this.zza;
        zzdgVar.zzq((i2 << 3) | 2);
        zzck zzckVar = (zzck) obj2;
        int a2 = zzckVar.a();
        if (a2 == -1) {
            a2 = zzgbVar.zza(zzckVar);
            zzckVar.b(a2);
        }
        zzdgVar.zzq(a2);
        zzgbVar.zzm(obj2, zzdgVar.f6413a);
    }

    public final void zzw(int i2, Object obj) {
        if (obj instanceof zzdb) {
            zzdg zzdgVar = (zzdg) this.zza;
            zzdgVar.zzq(11);
            zzdgVar.zzp(2, i2);
            zzdgVar.zze(3, (zzdb) obj);
            zzdgVar.zzq(12);
            return;
        }
        zzdi zzdiVar = this.zza;
        zzfl zzflVar = (zzfl) obj;
        zzdg zzdgVar2 = (zzdg) zzdiVar;
        zzdgVar2.zzq(11);
        zzdgVar2.zzp(2, i2);
        zzdgVar2.zzq(26);
        zzdgVar2.zzq(zzflVar.zzE());
        zzflVar.zzW(zzdiVar);
        zzdgVar2.zzq(12);
    }

    public final void zzx(int i2, int i3) {
        this.zza.zzf(i2, i3);
    }

    public final void zzy(int i2, List list, boolean z) {
        int i3 = 0;
        if (!z) {
            while (i3 < list.size()) {
                this.zza.zzf(i2, ((Integer) list.get(i3)).intValue());
                i3++;
            }
            return;
        }
        this.zza.zzo(i2, 2);
        int i4 = 0;
        for (int i5 = 0; i5 < list.size(); i5++) {
            ((Integer) list.get(i5)).intValue();
            i4 += 4;
        }
        this.zza.zzq(i4);
        while (i3 < list.size()) {
            this.zza.zzg(((Integer) list.get(i3)).intValue());
            i3++;
        }
    }

    public final void zzz(int i2, long j2) {
        this.zza.zzh(i2, j2);
    }
}

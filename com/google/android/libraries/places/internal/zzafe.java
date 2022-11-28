package com.google.android.libraries.places.internal;

import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzafe {
    private static final Class zza;
    private static final zzaft zzb;
    private static final zzaft zzc;
    private static final zzaft zzd;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zza = cls;
        zzb = zzZ(false);
        zzc = zzZ(true);
        zzd = new zzafv();
    }

    public static zzaft zzA() {
        return zzc;
    }

    public static zzaft zzB() {
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzC(zzada zzadaVar, Object obj, Object obj2) {
        zzadaVar.zza(obj2);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzD(zzaft zzaftVar, Object obj, Object obj2) {
        zzaftVar.zzf(obj, zzaftVar.zzd(zzaftVar.zzc(obj), zzaftVar.zzc(obj2)));
    }

    public static void zzE(Class cls) {
        Class cls2;
        if (!zzadk.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzF(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzG(zzaem zzaemVar, Object obj, Object obj2, long j2) {
        zzael zzaelVar = (zzael) zzagd.zzf(obj, j2);
        zzael zzaelVar2 = (zzael) zzagd.zzf(obj2, j2);
        if (!zzaelVar2.isEmpty()) {
            if (!zzaelVar.zzd()) {
                zzaelVar = zzaelVar.zza();
            }
            zzaelVar.zzc(zzaelVar2);
        }
        zzagd.zzs(obj, j2, zzaelVar);
    }

    public static void zzH(int i2, List list, zzacy zzacyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacyVar.zzc(i2, list, z);
    }

    public static void zzI(int i2, List list, zzacy zzacyVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacyVar.zze(i2, list);
    }

    public static void zzJ(int i2, List list, zzacy zzacyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacyVar.zzg(i2, list, z);
    }

    public static void zzK(int i2, List list, zzacy zzacyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacyVar.zzi(i2, list, z);
    }

    public static void zzL(int i2, List list, zzacy zzacyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacyVar.zzk(i2, list, z);
    }

    public static void zzM(int i2, List list, zzacy zzacyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacyVar.zzm(i2, list, z);
    }

    public static void zzN(int i2, List list, zzacy zzacyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacyVar.zzo(i2, list, z);
    }

    public static void zzO(int i2, List list, zzacy zzacyVar, zzafc zzafcVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            zzacyVar.zzp(i2, list.get(i3), zzafcVar);
        }
    }

    public static void zzP(int i2, List list, zzacy zzacyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacyVar.zzr(i2, list, z);
    }

    public static void zzQ(int i2, List list, zzacy zzacyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacyVar.zzt(i2, list, z);
    }

    public static void zzR(int i2, List list, zzacy zzacyVar, zzafc zzafcVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            zzacyVar.zzu(i2, list.get(i3), zzafcVar);
        }
    }

    public static void zzS(int i2, List list, zzacy zzacyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacyVar.zzw(i2, list, z);
    }

    public static void zzT(int i2, List list, zzacy zzacyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacyVar.zzy(i2, list, z);
    }

    public static void zzU(int i2, List list, zzacy zzacyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacyVar.zzA(i2, list, z);
    }

    public static void zzV(int i2, List list, zzacy zzacyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacyVar.zzC(i2, list, z);
    }

    public static void zzW(int i2, List list, zzacy zzacyVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacyVar.zzE(i2, list);
    }

    public static void zzX(int i2, List list, zzacy zzacyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacyVar.zzG(i2, list, z);
    }

    public static void zzY(int i2, List list, zzacy zzacyVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzacyVar.zzI(i2, list, z);
    }

    private static zzaft zzZ(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (zzaft) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzacx.zzA(i2 << 3) + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(List list) {
        return list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int i2, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzz = size * zzacx.zzz(i2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            zzz += zzacx.zzt((zzacp) list.get(i3));
        }
        return zzz;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzacx.zzz(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadl) {
            zzadl zzadlVar = (zzadl) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zzacx.zzv(zzadlVar.zzd(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zzacx.zzv(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzacx.zzA(i2 << 3) + 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(List list) {
        return list.size() * 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzacx.zzA(i2 << 3) + 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(List list) {
        return list.size() * 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(int i2, List list, zzafc zzafcVar) {
        int size = list.size();
        if (size != 0) {
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                i3 += zzacx.zzu(i2, (zzaer) list.get(i4), zzafcVar);
            }
            return i3;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzk(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzl(list) + (size * zzacx.zzz(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzl(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadl) {
            zzadl zzadlVar = (zzadl) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zzacx.zzv(zzadlVar.zzd(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zzacx.zzv(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzm(int i2, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzn(list) + (list.size() * zzacx.zzz(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzn(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzaeg) {
            zzaeg zzaegVar = (zzaeg) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zzacx.zzB(zzaegVar.zzd(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zzacx.zzB(((Long) list.get(i3)).longValue());
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzo(int i2, Object obj, zzafc zzafcVar) {
        if (obj instanceof zzadx) {
            int zzA = zzacx.zzA(i2 << 3);
            int zza2 = ((zzadx) obj).zza();
            return zzA + zzacx.zzA(zza2) + zza2;
        }
        return zzacx.zzA(i2 << 3) + zzacx.zzx((zzaer) obj, zzafcVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzp(int i2, List list, zzafc zzafcVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzz = zzacx.zzz(i2) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            zzz += obj instanceof zzadx ? zzacx.zzw((zzadx) obj) : zzacx.zzx((zzaer) obj, zzafcVar);
        }
        return zzz;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzq(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzr(list) + (size * zzacx.zzz(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzr(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadl) {
            zzadl zzadlVar = (zzadl) list;
            i2 = 0;
            while (i3 < size) {
                int zzd2 = zzadlVar.zzd(i3);
                i2 += zzacx.zzA((zzd2 >> 31) ^ (zzd2 + zzd2));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                int intValue = ((Integer) list.get(i3)).intValue();
                i2 += zzacx.zzA((intValue >> 31) ^ (intValue + intValue));
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzs(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzt(list) + (size * zzacx.zzz(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzt(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzaeg) {
            zzaeg zzaegVar = (zzaeg) list;
            i2 = 0;
            while (i3 < size) {
                long zzd2 = zzaegVar.zzd(i3);
                i2 += zzacx.zzB((zzd2 >> 63) ^ (zzd2 + zzd2));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                long longValue = ((Long) list.get(i3)).longValue();
                i2 += zzacx.zzB((longValue >> 63) ^ (longValue + longValue));
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzu(int i2, List list) {
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        int zzz = zzacx.zzz(i2) * size;
        if (list instanceof zzadz) {
            zzadz zzadzVar = (zzadz) list;
            while (i3 < size) {
                Object zze = zzadzVar.zze(i3);
                zzz += zze instanceof zzacp ? zzacx.zzt((zzacp) zze) : zzacx.zzy((String) zze);
                i3++;
            }
        } else {
            while (i3 < size) {
                Object obj = list.get(i3);
                zzz += obj instanceof zzacp ? zzacx.zzt((zzacp) obj) : zzacx.zzy((String) obj);
                i3++;
            }
        }
        return zzz;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzv(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzacx.zzz(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzw(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadl) {
            zzadl zzadlVar = (zzadl) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zzacx.zzA(zzadlVar.zzd(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zzacx.zzA(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzx(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzy(list) + (size * zzacx.zzz(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzy(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzaeg) {
            zzaeg zzaegVar = (zzaeg) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zzacx.zzB(zzaegVar.zzd(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zzacx.zzB(((Long) list.get(i3)).longValue());
                i3++;
            }
        }
        return i2;
    }

    public static zzaft zzz() {
        return zzb;
    }
}

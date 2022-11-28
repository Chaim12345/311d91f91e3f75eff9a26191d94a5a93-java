package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzgd {
    private static final Class zza;
    private static final zzgp zzb;
    private static final zzgp zzc;
    private static final zzgp zzd;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zza = cls;
        zzb = zzab(false);
        zzc = zzab(true);
        zzd = new zzgr();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int A(int i2, List list) {
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        int zzC = zzdi.zzC(i2) * size;
        if (list instanceof zzev) {
            zzev zzevVar = (zzev) list;
            while (i3 < size) {
                Object zzf = zzevVar.zzf(i3);
                zzC += zzf instanceof zzdb ? zzdi.zzu((zzdb) zzf) : zzdi.zzB((String) zzf);
                i3++;
            }
        } else {
            while (i3 < size) {
                Object obj = list.get(i3);
                zzC += obj instanceof zzdb ? zzdi.zzu((zzdb) obj) : zzdi.zzB((String) obj);
                i3++;
            }
        }
        return zzC;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int B(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return C(list) + (size * zzdi.zzC(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int C(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzed) {
            zzed zzedVar = (zzed) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zzdi.zzD(zzedVar.zze(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zzdi.zzD(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int D(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return E(list) + (size * zzdi.zzC(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int E(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfa) {
            zzfa zzfaVar = (zzfa) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zzdi.zzE(zzfaVar.zze(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zzdi.zzE(((Long) list.get(i3)).longValue());
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object a(int i2, List list, zzeg zzegVar, Object obj, zzgp zzgpVar) {
        if (zzegVar == null) {
            return obj;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                int intValue = ((Integer) list.get(i4)).intValue();
                if (zzegVar.zza(intValue)) {
                    if (i4 != i3) {
                        list.set(i3, Integer.valueOf(intValue));
                    }
                    i3++;
                } else {
                    obj = b(i2, intValue, obj, zzgpVar);
                }
            }
            if (i3 != size) {
                list.subList(i3, size).clear();
                return obj;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!zzegVar.zza(intValue2)) {
                    obj = b(i2, intValue2, obj, zzgpVar);
                    it.remove();
                }
            }
        }
        return obj;
    }

    static Object b(int i2, int i3, Object obj, zzgp zzgpVar) {
        if (obj == null) {
            obj = zzgpVar.e();
        }
        zzgpVar.f(obj, i2, i3);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void c(zzdo zzdoVar, Object obj, Object obj2) {
        zzds b2 = zzdoVar.b(obj2);
        if (b2.f6415a.isEmpty()) {
            return;
        }
        zzdoVar.c(obj).zzh(b2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(zzgp zzgpVar, Object obj, Object obj2) {
        zzgpVar.h(obj, zzgpVar.d(zzgpVar.c(obj), zzgpVar.c(obj2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean e(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void f(zzfg zzfgVar, Object obj, Object obj2, long j2) {
        zzgz.s(obj, j2, zzfg.zzb(zzgz.f(obj, j2), zzgz.f(obj2, j2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int g(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzdi.zzD(i2 << 3) + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int h(List list) {
        return list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int i(int i2, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzC = size * zzdi.zzC(i2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            zzC += zzdi.zzu((zzdb) list.get(i3));
        }
        return zzC;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int j(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return k(list) + (size * zzdi.zzC(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int k(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzed) {
            zzed zzedVar = (zzed) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zzdi.zzx(zzedVar.zze(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zzdi.zzx(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int l(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzdi.zzD(i2 << 3) + 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int m(List list) {
        return list.size() * 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int n(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzdi.zzD(i2 << 3) + 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int o(List list) {
        return list.size() * 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int p(int i2, List list, zzgb zzgbVar) {
        int size = list.size();
        if (size != 0) {
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                i3 += zzdi.d(i2, (zzfl) list.get(i4), zzgbVar);
            }
            return i3;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int q(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return r(list) + (size * zzdi.zzC(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int r(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzed) {
            zzed zzedVar = (zzed) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zzdi.zzx(zzedVar.zze(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zzdi.zzx(((Integer) list.get(i3)).intValue());
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int s(int i2, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return t(list) + (list.size() * zzdi.zzC(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int t(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfa) {
            zzfa zzfaVar = (zzfa) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zzdi.zzE(zzfaVar.zze(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zzdi.zzE(((Long) list.get(i3)).longValue());
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int u(int i2, Object obj, zzgb zzgbVar) {
        if (obj instanceof zzet) {
            int zzD = zzdi.zzD(i2 << 3);
            int zza2 = ((zzet) obj).zza();
            return zzD + zzdi.zzD(zza2) + zza2;
        }
        return zzdi.zzD(i2 << 3) + zzdi.a((zzfl) obj, zzgbVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int v(int i2, List list, zzgb zzgbVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzC = zzdi.zzC(i2) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            zzC += obj instanceof zzet ? zzdi.zzy((zzet) obj) : zzdi.a((zzfl) obj, zzgbVar);
        }
        return zzC;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int w(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return x(list) + (size * zzdi.zzC(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int x(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzed) {
            zzed zzedVar = (zzed) list;
            i2 = 0;
            while (i3 < size) {
                int zze = zzedVar.zze(i3);
                i2 += zzdi.zzD((zze >> 31) ^ (zze + zze));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                int intValue = ((Integer) list.get(i3)).intValue();
                i2 += zzdi.zzD((intValue >> 31) ^ (intValue + intValue));
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int y(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return z(list) + (size * zzdi.zzC(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int z(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfa) {
            zzfa zzfaVar = (zzfa) list;
            i2 = 0;
            while (i3 < size) {
                long zze = zzfaVar.zze(i3);
                i2 += zzdi.zzE((zze >> 63) ^ (zze + zze));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                long longValue = ((Long) list.get(i3)).longValue();
                i2 += zzdi.zzE((longValue >> 63) ^ (longValue + longValue));
                i3++;
            }
        }
        return i2;
    }

    public static zzgp zzA() {
        return zzc;
    }

    public static zzgp zzB() {
        return zzd;
    }

    public static void zzG(Class cls) {
        Class cls2;
        if (!zzec.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzJ(int i2, List list, zzdj zzdjVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzc(i2, list, z);
    }

    public static void zzK(int i2, List list, zzdj zzdjVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zze(i2, list);
    }

    public static void zzL(int i2, List list, zzdj zzdjVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzg(i2, list, z);
    }

    public static void zzM(int i2, List list, zzdj zzdjVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzj(i2, list, z);
    }

    public static void zzN(int i2, List list, zzdj zzdjVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzl(i2, list, z);
    }

    public static void zzO(int i2, List list, zzdj zzdjVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzn(i2, list, z);
    }

    public static void zzP(int i2, List list, zzdj zzdjVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzp(i2, list, z);
    }

    public static void zzQ(int i2, List list, zzdj zzdjVar, zzgb zzgbVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            zzdjVar.zzq(i2, list.get(i3), zzgbVar);
        }
    }

    public static void zzR(int i2, List list, zzdj zzdjVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzs(i2, list, z);
    }

    public static void zzS(int i2, List list, zzdj zzdjVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzu(i2, list, z);
    }

    public static void zzT(int i2, List list, zzdj zzdjVar, zzgb zzgbVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            zzdjVar.zzv(i2, list.get(i3), zzgbVar);
        }
    }

    public static void zzU(int i2, List list, zzdj zzdjVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzy(i2, list, z);
    }

    public static void zzV(int i2, List list, zzdj zzdjVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzA(i2, list, z);
    }

    public static void zzW(int i2, List list, zzdj zzdjVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzC(i2, list, z);
    }

    public static void zzX(int i2, List list, zzdj zzdjVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzE(i2, list, z);
    }

    public static void zzY(int i2, List list, zzdj zzdjVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzH(i2, list);
    }

    public static void zzZ(int i2, List list, zzdj zzdjVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzJ(i2, list, z);
    }

    public static void zzaa(int i2, List list, zzdj zzdjVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzdjVar.zzL(i2, list, z);
    }

    private static zzgp zzab(boolean z) {
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
            return (zzgp) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused2) {
            return null;
        }
    }

    public static zzgp zzz() {
        return zzb;
    }
}

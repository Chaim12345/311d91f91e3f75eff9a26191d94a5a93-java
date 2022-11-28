package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzlw {
    private static final Class zza;
    private static final zzml zzb;
    private static final zzml zzc;
    private static final zzml zzd;

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
        zzd = new zzmn();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int A(int i2, List list) {
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        int zzz = zzjj.zzz(i2) * size;
        if (list instanceof zzkr) {
            zzkr zzkrVar = (zzkr) list;
            while (i3 < size) {
                Object zzf = zzkrVar.zzf(i3);
                zzz += zzf instanceof zzjb ? zzjj.zzt((zzjb) zzf) : zzjj.zzy((String) zzf);
                i3++;
            }
        } else {
            while (i3 < size) {
                Object obj = list.get(i3);
                zzz += obj instanceof zzjb ? zzjj.zzt((zzjb) obj) : zzjj.zzy((String) obj);
                i3++;
            }
        }
        return zzz;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int B(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return C(list) + (size * zzjj.zzz(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int C(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkd) {
            zzkd zzkdVar = (zzkd) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zzjj.zzA(zzkdVar.zze(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zzjj.zzA(((Integer) list.get(i3)).intValue());
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
        return E(list) + (size * zzjj.zzz(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int E(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzky) {
            zzky zzkyVar = (zzky) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zzjj.zzB(zzkyVar.zza(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zzjj.zzB(((Long) list.get(i3)).longValue());
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object a(int i2, List list, zzkg zzkgVar, Object obj, zzml zzmlVar) {
        if (zzkgVar == null) {
            return obj;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                int intValue = ((Integer) list.get(i4)).intValue();
                if (zzkgVar.zza(intValue)) {
                    if (i4 != i3) {
                        list.set(i3, Integer.valueOf(intValue));
                    }
                    i3++;
                } else {
                    obj = b(i2, intValue, obj, zzmlVar);
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
                if (!zzkgVar.zza(intValue2)) {
                    obj = b(i2, intValue2, obj, zzmlVar);
                    it.remove();
                }
            }
        }
        return obj;
    }

    static Object b(int i2, int i3, Object obj, zzml zzmlVar) {
        if (obj == null) {
            obj = zzmlVar.e();
        }
        zzmlVar.f(obj, i2, i3);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void c(zzjp zzjpVar, Object obj, Object obj2) {
        zzjpVar.a(obj2);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(zzml zzmlVar, Object obj, Object obj2) {
        zzmlVar.h(obj, zzmlVar.d(zzmlVar.c(obj), zzmlVar.c(obj2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean e(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int f(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzjj.zzA(i2 << 3) + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void g(zzle zzleVar, Object obj, Object obj2, long j2) {
        zzmv.s(obj, j2, zzle.zzb(zzmv.f(obj, j2), zzmv.f(obj2, j2)));
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
        int zzz = size * zzjj.zzz(i2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            zzz += zzjj.zzt((zzjb) list.get(i3));
        }
        return zzz;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int j(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return k(list) + (size * zzjj.zzz(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int k(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkd) {
            zzkd zzkdVar = (zzkd) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zzjj.zzv(zzkdVar.zze(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zzjj.zzv(((Integer) list.get(i3)).intValue());
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
        return size * (zzjj.zzA(i2 << 3) + 4);
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
        return size * (zzjj.zzA(i2 << 3) + 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int o(List list) {
        return list.size() * 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int p(int i2, List list, zzlu zzluVar) {
        int size = list.size();
        if (size != 0) {
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                i3 += zzjj.c(i2, (zzlj) list.get(i4), zzluVar);
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
        return r(list) + (size * zzjj.zzz(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int r(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkd) {
            zzkd zzkdVar = (zzkd) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zzjj.zzv(zzkdVar.zze(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zzjj.zzv(((Integer) list.get(i3)).intValue());
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
        return t(list) + (list.size() * zzjj.zzz(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int t(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzky) {
            zzky zzkyVar = (zzky) list;
            i2 = 0;
            while (i3 < size) {
                i2 += zzjj.zzB(zzkyVar.zza(i3));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                i2 += zzjj.zzB(((Long) list.get(i3)).longValue());
                i3++;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int u(int i2, Object obj, zzlu zzluVar) {
        if (obj instanceof zzkp) {
            int zzA = zzjj.zzA(i2 << 3);
            int zza2 = ((zzkp) obj).zza();
            return zzA + zzjj.zzA(zza2) + zza2;
        }
        return zzjj.zzA(i2 << 3) + zzjj.d((zzlj) obj, zzluVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int v(int i2, List list, zzlu zzluVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzz = zzjj.zzz(i2) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            zzz += obj instanceof zzkp ? zzjj.zzw((zzkp) obj) : zzjj.d((zzlj) obj, zzluVar);
        }
        return zzz;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int w(int i2, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return x(list) + (size * zzjj.zzz(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int x(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkd) {
            zzkd zzkdVar = (zzkd) list;
            i2 = 0;
            while (i3 < size) {
                int zze = zzkdVar.zze(i3);
                i2 += zzjj.zzA((zze >> 31) ^ (zze + zze));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                int intValue = ((Integer) list.get(i3)).intValue();
                i2 += zzjj.zzA((intValue >> 31) ^ (intValue + intValue));
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
        return z(list) + (size * zzjj.zzz(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int z(List list) {
        int i2;
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzky) {
            zzky zzkyVar = (zzky) list;
            i2 = 0;
            while (i3 < size) {
                long zza2 = zzkyVar.zza(i3);
                i2 += zzjj.zzB((zza2 >> 63) ^ (zza2 + zza2));
                i3++;
            }
        } else {
            i2 = 0;
            while (i3 < size) {
                long longValue = ((Long) list.get(i3)).longValue();
                i2 += zzjj.zzB((longValue >> 63) ^ (longValue + longValue));
                i3++;
            }
        }
        return i2;
    }

    public static zzml zzA() {
        return zzc;
    }

    public static zzml zzB() {
        return zzd;
    }

    public static void zzG(Class cls) {
        Class cls2;
        if (!zzkc.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzH(int i2, List list, zznd zzndVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzc(i2, list, z);
    }

    public static void zzI(int i2, List list, zznd zzndVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zze(i2, list);
    }

    public static void zzJ(int i2, List list, zznd zzndVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzg(i2, list, z);
    }

    public static void zzK(int i2, List list, zznd zzndVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzj(i2, list, z);
    }

    public static void zzL(int i2, List list, zznd zzndVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzl(i2, list, z);
    }

    public static void zzM(int i2, List list, zznd zzndVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzn(i2, list, z);
    }

    public static void zzN(int i2, List list, zznd zzndVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzp(i2, list, z);
    }

    public static void zzO(int i2, List list, zznd zzndVar, zzlu zzluVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            ((zzjk) zzndVar).zzq(i2, list.get(i3), zzluVar);
        }
    }

    public static void zzP(int i2, List list, zznd zzndVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzs(i2, list, z);
    }

    public static void zzQ(int i2, List list, zznd zzndVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzu(i2, list, z);
    }

    public static void zzR(int i2, List list, zznd zzndVar, zzlu zzluVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            ((zzjk) zzndVar).zzv(i2, list.get(i3), zzluVar);
        }
    }

    public static void zzS(int i2, List list, zznd zzndVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzx(i2, list, z);
    }

    public static void zzT(int i2, List list, zznd zzndVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzz(i2, list, z);
    }

    public static void zzU(int i2, List list, zznd zzndVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzB(i2, list, z);
    }

    public static void zzV(int i2, List list, zznd zzndVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzD(i2, list, z);
    }

    public static void zzW(int i2, List list, zznd zzndVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzG(i2, list);
    }

    public static void zzX(int i2, List list, zznd zzndVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzI(i2, list, z);
    }

    public static void zzY(int i2, List list, zznd zzndVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzK(i2, list, z);
    }

    private static zzml zzab(boolean z) {
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
            return (zzml) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused2) {
            return null;
        }
    }

    public static zzml zzz() {
        return zzb;
    }
}

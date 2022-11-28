package com.google.android.gms.internal.mlkit_vision_barcode;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzbs extends AbstractMap implements Serializable {
    private static final Object zzd = new Object();
    @CheckForNull

    /* renamed from: a  reason: collision with root package name */
    transient int[] f6276a;
    @CheckForNull

    /* renamed from: b  reason: collision with root package name */
    transient Object[] f6277b;
    @CheckForNull

    /* renamed from: c  reason: collision with root package name */
    transient Object[] f6278c;
    @CheckForNull
    private transient Object zze;
    private transient int zzf;
    private transient int zzg;
    @CheckForNull
    private transient Set zzh;
    @CheckForNull
    private transient Set zzi;
    @CheckForNull
    private transient Collection zzj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbs(int i2) {
        o(12);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int b(zzbs zzbsVar) {
        int i2 = zzbsVar.zzg;
        zzbsVar.zzg = i2 - 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object g(zzbs zzbsVar, int i2) {
        return zzbsVar.zzA()[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object j(zzbs zzbsVar, int i2) {
        return zzbsVar.zzB()[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object k(zzbs zzbsVar) {
        Object obj = zzbsVar.zze;
        obj.getClass();
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void m(zzbs zzbsVar, int i2, Object obj) {
        zzbsVar.zzB()[i2] = obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object[] zzA() {
        Object[] objArr = this.f6277b;
        objArr.getClass();
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object[] zzB() {
        Object[] objArr = this.f6278c;
        objArr.getClass();
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zzu() {
        return (1 << (this.zzf & 31)) - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zzv(@CheckForNull Object obj) {
        if (q()) {
            return -1;
        }
        int a2 = zzbu.a(obj);
        int zzu = zzu();
        Object obj2 = this.zze;
        obj2.getClass();
        int c2 = zzbt.c(obj2, a2 & zzu);
        if (c2 != 0) {
            int i2 = ~zzu;
            int i3 = a2 & i2;
            do {
                int i4 = c2 - 1;
                int i5 = zzz()[i4];
                if ((i5 & i2) == i3 && zzam.zza(obj, zzA()[i4])) {
                    return i4;
                }
                c2 = i5 & zzu;
            } while (c2 != 0);
            return -1;
        }
        return -1;
    }

    private final int zzw(int i2, int i3, int i4, int i5) {
        Object d2 = zzbt.d(i3);
        int i6 = i3 - 1;
        if (i5 != 0) {
            zzbt.e(d2, i4 & i6, i5 + 1);
        }
        Object obj = this.zze;
        obj.getClass();
        int[] zzz = zzz();
        for (int i7 = 0; i7 <= i2; i7++) {
            int c2 = zzbt.c(obj, i7);
            while (c2 != 0) {
                int i8 = c2 - 1;
                int i9 = zzz[i8];
                int i10 = ((~i2) & i9) | i7;
                int i11 = i10 & i6;
                int c3 = zzbt.c(d2, i11);
                zzbt.e(d2, i11, c2);
                zzz[i8] = ((~i6) & i10) | (c3 & i6);
                c2 = i9 & i2;
            }
        }
        this.zze = d2;
        zzy(i6);
        return i6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object zzx(@CheckForNull Object obj) {
        if (q()) {
            return zzd;
        }
        int zzu = zzu();
        Object obj2 = this.zze;
        obj2.getClass();
        int b2 = zzbt.b(obj, null, zzu, obj2, zzz(), zzA(), null);
        if (b2 == -1) {
            return zzd;
        }
        Object obj3 = zzB()[b2];
        p(b2, zzu);
        this.zzg--;
        n();
        return obj3;
    }

    private final void zzy(int i2) {
        this.zzf = ((32 - Integer.numberOfLeadingZeros(i2)) & 31) | (this.zzf & (-32));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int[] zzz() {
        int[] iArr = this.f6276a;
        iArr.getClass();
        return iArr;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        if (q()) {
            return;
        }
        n();
        Map l2 = l();
        if (l2 != null) {
            this.zzf = zzdc.zza(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
            l2.clear();
            this.zze = null;
        } else {
            Arrays.fill(zzA(), 0, this.zzg, (Object) null);
            Arrays.fill(zzB(), 0, this.zzg, (Object) null);
            Object obj = this.zze;
            obj.getClass();
            if (obj instanceof byte[]) {
                Arrays.fill((byte[]) obj, (byte) 0);
            } else if (obj instanceof short[]) {
                Arrays.fill((short[]) obj, (short) 0);
            } else {
                Arrays.fill((int[]) obj, 0);
            }
            Arrays.fill(zzz(), 0, this.zzg, 0);
        }
        this.zzg = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(@CheckForNull Object obj) {
        Map l2 = l();
        return l2 != null ? l2.containsKey(obj) : zzv(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(@CheckForNull Object obj) {
        Map l2 = l();
        if (l2 == null) {
            for (int i2 = 0; i2 < this.zzg; i2++) {
                if (zzam.zza(obj, zzB()[i2])) {
                    return true;
                }
            }
            return false;
        }
        return l2.containsValue(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int e() {
        return isEmpty() ? -1 : 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        Set set = this.zzi;
        if (set == null) {
            zzbn zzbnVar = new zzbn(this);
            this.zzi = zzbnVar;
            return zzbnVar;
        }
        return set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int f(int i2) {
        int i3 = i2 + 1;
        if (i3 < this.zzg) {
            return i3;
        }
        return -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final Object get(@CheckForNull Object obj) {
        Map l2 = l();
        if (l2 != null) {
            return l2.get(obj);
        }
        int zzv = zzv(obj);
        if (zzv == -1) {
            return null;
        }
        return zzB()[zzv];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        Set set = this.zzh;
        if (set == null) {
            zzbp zzbpVar = new zzbp(this);
            this.zzh = zzbpVar;
            return zzbpVar;
        }
        return set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CheckForNull
    public final Map l() {
        Object obj = this.zze;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void n() {
        this.zzf += 32;
    }

    final void o(int i2) {
        this.zzf = zzdc.zza(12, 1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void p(int i2, int i3) {
        Object obj = this.zze;
        obj.getClass();
        int[] zzz = zzz();
        Object[] zzA = zzA();
        Object[] zzB = zzB();
        int size = size() - 1;
        if (i2 >= size) {
            zzA[i2] = null;
            zzB[i2] = null;
            zzz[i2] = 0;
            return;
        }
        Object obj2 = zzA[size];
        zzA[i2] = obj2;
        zzB[i2] = zzB[size];
        zzA[size] = null;
        zzB[size] = null;
        zzz[i2] = zzz[size];
        zzz[size] = 0;
        int a2 = zzbu.a(obj2) & i3;
        int c2 = zzbt.c(obj, a2);
        int i4 = size + 1;
        if (c2 == i4) {
            zzbt.e(obj, a2, i2 + 1);
            return;
        }
        while (true) {
            int i5 = c2 - 1;
            int i6 = zzz[i5];
            int i7 = i6 & i3;
            if (i7 == i4) {
                zzz[i5] = ((i2 + 1) & i3) | (i6 & (~i3));
                return;
            }
            c2 = i7;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final Object put(Object obj, Object obj2) {
        int length;
        int min;
        if (q()) {
            zzaq.zzd(q(), "Arrays already allocated");
            int i2 = this.zzf;
            int max = Math.max(i2 + 1, 2);
            int highestOneBit = Integer.highestOneBit(max);
            if (max > highestOneBit && (highestOneBit = highestOneBit + highestOneBit) <= 0) {
                highestOneBit = 1073741824;
            }
            int max2 = Math.max(4, highestOneBit);
            this.zze = zzbt.d(max2);
            zzy(max2 - 1);
            this.f6276a = new int[i2];
            this.f6277b = new Object[i2];
            this.f6278c = new Object[i2];
        }
        Map l2 = l();
        if (l2 != null) {
            return l2.put(obj, obj2);
        }
        int[] zzz = zzz();
        Object[] zzA = zzA();
        Object[] zzB = zzB();
        int i3 = this.zzg;
        int i4 = i3 + 1;
        int a2 = zzbu.a(obj);
        int zzu = zzu();
        int i5 = a2 & zzu;
        Object obj3 = this.zze;
        obj3.getClass();
        int c2 = zzbt.c(obj3, i5);
        if (c2 == 0) {
            if (i4 <= zzu) {
                Object obj4 = this.zze;
                obj4.getClass();
                zzbt.e(obj4, i5, i4);
                length = zzz().length;
                if (i4 > length && (min = Math.min((int) LockFreeTaskQueueCore.MAX_CAPACITY_MASK, (Math.max(1, length >>> 1) + length) | 1)) != length) {
                    this.f6276a = Arrays.copyOf(zzz(), min);
                    this.f6277b = Arrays.copyOf(zzA(), min);
                    this.f6278c = Arrays.copyOf(zzB(), min);
                }
                zzz()[i3] = (~zzu) & a2;
                zzA()[i3] = obj;
                zzB()[i3] = obj2;
                this.zzg = i4;
                n();
                return null;
            }
            zzu = zzw(zzu, zzbt.a(zzu), a2, i3);
            length = zzz().length;
            if (i4 > length) {
                this.f6276a = Arrays.copyOf(zzz(), min);
                this.f6277b = Arrays.copyOf(zzA(), min);
                this.f6278c = Arrays.copyOf(zzB(), min);
            }
            zzz()[i3] = (~zzu) & a2;
            zzA()[i3] = obj;
            zzB()[i3] = obj2;
            this.zzg = i4;
            n();
            return null;
        }
        int i6 = ~zzu;
        int i7 = a2 & i6;
        int i8 = 0;
        while (true) {
            int i9 = c2 - 1;
            int i10 = zzz[i9];
            int i11 = i10 & i6;
            if (i11 == i7 && zzam.zza(obj, zzA[i9])) {
                Object obj5 = zzB[i9];
                zzB[i9] = obj2;
                return obj5;
            }
            int i12 = i10 & zzu;
            i8++;
            if (i12 != 0) {
                c2 = i12;
            } else if (i8 >= 9) {
                LinkedHashMap linkedHashMap = new LinkedHashMap(zzu() + 1, 1.0f);
                int e2 = e();
                while (e2 >= 0) {
                    linkedHashMap.put(zzA()[e2], zzB()[e2]);
                    e2 = f(e2);
                }
                this.zze = linkedHashMap;
                this.f6276a = null;
                this.f6277b = null;
                this.f6278c = null;
                n();
                return linkedHashMap.put(obj, obj2);
            } else if (i4 <= zzu) {
                zzz[i9] = (i4 & zzu) | i11;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean q() {
        return this.zze == null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final Object remove(@CheckForNull Object obj) {
        Map l2 = l();
        if (l2 != null) {
            return l2.remove(obj);
        }
        Object zzx = zzx(obj);
        if (zzx == zzd) {
            return null;
        }
        return zzx;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        Map l2 = l();
        return l2 != null ? l2.size() : this.zzg;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection collection = this.zzj;
        if (collection == null) {
            zzbr zzbrVar = new zzbr(this);
            this.zzj = zzbrVar;
            return zzbrVar;
        }
        return collection;
    }
}

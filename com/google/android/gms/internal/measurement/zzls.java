package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;
/* loaded from: classes.dex */
final class zzls extends zzim implements RandomAccess {
    private static final zzls zza;
    private Object[] zzb;
    private int zzc;

    static {
        zzls zzlsVar = new zzls(new Object[0], 0);
        zza = zzlsVar;
        zzlsVar.zzb();
    }

    private zzls(Object[] objArr, int i2) {
        this.zzb = objArr;
        this.zzc = i2;
    }

    public static zzls zze() {
        return zza;
    }

    private final String zzf(int i2) {
        int i3 = this.zzc;
        return "Index:" + i2 + ", Size:" + i3;
    }

    private final void zzg(int i2) {
        if (i2 < 0 || i2 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzf(i2));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim, java.util.AbstractList, java.util.List
    public final void add(int i2, Object obj) {
        int i3;
        a();
        if (i2 < 0 || i2 > (i3 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzf(i2));
        }
        Object[] objArr = this.zzb;
        if (i3 < objArr.length) {
            System.arraycopy(objArr, i2, objArr, i2 + 1, i3 - i2);
        } else {
            Object[] objArr2 = new Object[((i3 * 3) / 2) + 1];
            System.arraycopy(objArr, 0, objArr2, 0, i2);
            System.arraycopy(this.zzb, i2, objArr2, i2 + 1, this.zzc - i2);
            this.zzb = objArr2;
        }
        this.zzb[i2] = obj;
        this.zzc++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzim, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        a();
        int i2 = this.zzc;
        Object[] objArr = this.zzb;
        if (i2 == objArr.length) {
            this.zzb = Arrays.copyOf(objArr, ((i2 * 3) / 2) + 1);
        }
        Object[] objArr2 = this.zzb;
        int i3 = this.zzc;
        this.zzc = i3 + 1;
        objArr2[i3] = obj;
        ((AbstractList) this).modCount++;
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i2) {
        zzg(i2);
        return this.zzb[i2];
    }

    @Override // com.google.android.gms.internal.measurement.zzim, java.util.AbstractList, java.util.List
    public final Object remove(int i2) {
        int i3;
        a();
        zzg(i2);
        Object[] objArr = this.zzb;
        Object obj = objArr[i2];
        if (i2 < this.zzc - 1) {
            System.arraycopy(objArr, i2 + 1, objArr, i2, (i3 - i2) - 1);
        }
        this.zzc--;
        ((AbstractList) this).modCount++;
        return obj;
    }

    @Override // com.google.android.gms.internal.measurement.zzim, java.util.AbstractList, java.util.List
    public final Object set(int i2, Object obj) {
        a();
        zzg(i2);
        Object[] objArr = this.zzb;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        ((AbstractList) this).modCount++;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzkj
    public final /* bridge */ /* synthetic */ zzkj zzd(int i2) {
        if (i2 >= this.zzc) {
            return new zzls(Arrays.copyOf(this.zzb, i2), this.zzc);
        }
        throw new IllegalArgumentException();
    }
}

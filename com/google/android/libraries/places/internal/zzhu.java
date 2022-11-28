package com.google.android.libraries.places.internal;

import java.util.Arrays;
/* loaded from: classes2.dex */
public final class zzhu {
    Object[] zza = new Object[8];
    int zzb = 0;
    zzht zzc;

    public final zzhu zza(Object obj, Object obj2) {
        int i2 = this.zzb + 1;
        int i3 = i2 + i2;
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (i3 > length) {
            int i4 = length + (length >> 1) + 1;
            if (i4 < i3) {
                int highestOneBit = Integer.highestOneBit(i3 - 1);
                i4 = highestOneBit + highestOneBit;
            }
            if (i4 < 0) {
                i4 = Integer.MAX_VALUE;
            }
            this.zza = Arrays.copyOf(objArr, i4);
        }
        zzhh.zza(obj, obj2);
        Object[] objArr2 = this.zza;
        int i5 = this.zzb;
        int i6 = i5 + i5;
        objArr2[i6] = obj;
        objArr2[i6 + 1] = obj2;
        this.zzb = i5 + 1;
        return this;
    }

    public final zzhv zzb() {
        zzht zzhtVar = this.zzc;
        if (zzhtVar == null) {
            zzil zzf = zzil.zzf(this.zzb, this.zza, this);
            zzht zzhtVar2 = this.zzc;
            if (zzhtVar2 == null) {
                return zzf;
            }
            throw zzhtVar2.zza();
        }
        throw zzhtVar.zza();
    }
}

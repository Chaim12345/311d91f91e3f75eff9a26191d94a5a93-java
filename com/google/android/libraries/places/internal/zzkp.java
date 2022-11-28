package com.google.android.libraries.places.internal;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/* loaded from: classes2.dex */
final class zzkp extends AbstractMap {
    private static final Comparator zza = new zzkm();
    private final Object[] zzb;
    private final int[] zzc;
    private final Set zzd = new zzko(this, -1);
    private Integer zze = null;
    private String zzf = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkp(List list) {
        Iterator it = list.iterator();
        if (it.hasNext()) {
            String unused = ((zzkl) it.next()).zza;
            throw null;
        }
        int size = list.size();
        Object[] objArr = new Object[size];
        int[] iArr = new int[1];
        Iterator it2 = list.iterator();
        if (it2.hasNext()) {
            String unused2 = ((zzkl) it2.next()).zza;
            throw null;
        }
        iArr[0] = 0;
        if (size > 16 && size * 9 > 0) {
            objArr = Arrays.copyOf(objArr, 0);
        }
        this.zzb = objArr;
        this.zzc = iArr;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        return this.zzd;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        if (this.zze == null) {
            this.zze = Integer.valueOf(super.hashCode());
        }
        return this.zze.intValue();
    }

    @Override // java.util.AbstractMap
    public final String toString() {
        if (this.zzf == null) {
            this.zzf = super.toString();
        }
        return this.zzf;
    }
}

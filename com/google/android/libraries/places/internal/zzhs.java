package com.google.android.libraries.places.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;
/* loaded from: classes2.dex */
public abstract class zzhs extends zzhp implements List, RandomAccess {
    private static final zziq zza = new zzhq(zzig.zza, 0);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhs zzi(Object[] objArr) {
        return zzj(objArr, objArr.length);
    }

    static zzhs zzj(Object[] objArr, int i2) {
        return i2 == 0 ? zzig.zza : new zzig(objArr, i2);
    }

    public static zzhs zzk(Collection collection) {
        if (!(collection instanceof zzhp)) {
            Object[] array = collection.toArray();
            int length = array.length;
            zzic.zza(array, length);
            return zzj(array, length);
        }
        zzhs zzd = ((zzhp) collection).zzd();
        if (zzd.zzf()) {
            Object[] array2 = zzd.toArray();
            return zzj(array2, array2.length);
        }
        return zzd;
    }

    public static zzhs zzl(Object[] objArr) {
        if (objArr.length == 0) {
            return zzig.zza;
        }
        Object[] objArr2 = (Object[]) objArr.clone();
        int length = objArr2.length;
        zzic.zza(objArr2, length);
        return zzj(objArr2, length);
    }

    public static zzhs zzm() {
        return zzig.zza;
    }

    public static zzhs zzn(Object obj) {
        Object[] objArr = {obj};
        zzic.zza(objArr, 1);
        return zzj(objArr, 1);
    }

    public static zzhs zzo(Object obj, Object obj2) {
        Object[] objArr = {obj, obj2};
        zzic.zza(objArr, 2);
        return zzj(objArr, 2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static zzhs zzp(Comparator comparator, Iterable iterable) {
        Object[] array = iterable.toArray();
        int length = array.length;
        zzic.zza(array, length);
        Arrays.sort(array, comparator);
        return zzj(array, length);
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int i2, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int i2, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.libraries.places.internal.zzhp, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    for (int i2 = 0; i2 < size; i2++) {
                        if (zzgw.zza(get(i2), list.get(i2))) {
                        }
                    }
                    return true;
                }
                Iterator it = iterator();
                Iterator it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it2.hasNext()) {
                            if (!zzgw.zza(it.next(), it2.next())) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else if (!it2.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int i2 = 1;
        for (int i3 = 0; i3 < size; i3++) {
            i2 = (i2 * 31) + get(i3).hashCode();
        }
        return i2;
    }

    @Override // java.util.List
    public final int indexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (obj.equals(get(i2))) {
                return i2;
            }
        }
        return -1;
    }

    @Override // com.google.android.libraries.places.internal.zzhp, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    @Deprecated
    public final Object remove(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final Object set(int i2, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.libraries.places.internal.zzhp
    int zza(Object[] objArr, int i2) {
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            objArr[i3] = get(i3);
        }
        return size;
    }

    @Override // com.google.android.libraries.places.internal.zzhp
    @Deprecated
    public final zzhs zzd() {
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzhp
    public final zzip zze() {
        return listIterator(0);
    }

    @Override // java.util.List
    /* renamed from: zzh */
    public zzhs subList(int i2, int i3) {
        zzha.zzg(i2, i3, size());
        int i4 = i3 - i2;
        return i4 == size() ? this : i4 == 0 ? zzig.zza : new zzhr(this, i2, i4);
    }

    @Override // java.util.List
    /* renamed from: zzq */
    public final zziq listIterator(int i2) {
        zzha.zzb(i2, size(), FirebaseAnalytics.Param.INDEX);
        return isEmpty() ? zza : new zzhq(this, i2);
    }
}

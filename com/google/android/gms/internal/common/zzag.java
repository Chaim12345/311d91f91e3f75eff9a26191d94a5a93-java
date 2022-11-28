package com.google.android.gms.internal.common;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;
/* loaded from: classes.dex */
public abstract class zzag<E> extends zzac<E> implements List<E>, RandomAccess {
    private static final zzak<Object> zza = new zzae(zzai.f5852b, 0);

    static zzag f(Object[] objArr, int i2) {
        return i2 == 0 ? zzai.f5852b : new zzai(objArr, i2);
    }

    public static <E> zzag<E> zzj(Iterable<? extends E> iterable) {
        Objects.requireNonNull(iterable);
        if (iterable instanceof Collection) {
            return zzk((Collection) iterable);
        }
        Iterator<? extends E> it = iterable.iterator();
        if (it.hasNext()) {
            E next = it.next();
            if (it.hasNext()) {
                zzad zzadVar = new zzad(4);
                zzadVar.zzb((zzad) next);
                zzadVar.zzc(it);
                zzadVar.f5848c = true;
                return f(zzadVar.f5846a, zzadVar.f5847b);
            }
            return zzm(next);
        }
        return zzai.f5852b;
    }

    public static <E> zzag<E> zzk(Collection<? extends E> collection) {
        if (!(collection instanceof zzac)) {
            Object[] array = collection.toArray();
            int length = array.length;
            zzah.a(array, length);
            return f(array, length);
        }
        zzag<E> zzd = ((zzac) collection).zzd();
        if (zzd.d()) {
            Object[] array2 = zzd.toArray();
            return f(array2, array2.length);
        }
        return zzd;
    }

    public static <E> zzag<E> zzl() {
        return zzai.f5852b;
    }

    public static <E> zzag<E> zzm(E e2) {
        Object[] objArr = {e2};
        zzah.a(objArr, 1);
        return f(objArr, 1);
    }

    public static <E> zzag<E> zzn(E e2, E e3) {
        Object[] objArr = {e2, e3};
        zzah.a(objArr, 2);
        return f(objArr, 2);
    }

    @Override // com.google.android.gms.internal.common.zzac
    int a(Object[] objArr, int i2) {
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            objArr[i3] = get(i3);
        }
        return size;
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int i2, E e2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int i2, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
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
                        if (zzr.zza(get(i2), list.get(i2))) {
                        }
                    }
                    return true;
                }
                Iterator<E> it = iterator();
                Iterator<E> it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it2.hasNext()) {
                            if (!zzr.zza(it.next(), it2.next())) {
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

    @Override // com.google.android.gms.internal.common.zzac, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
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
    public final E remove(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final E set(int i2, E e2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.common.zzac
    @Deprecated
    public final zzag<E> zzd() {
        return this;
    }

    @Override // com.google.android.gms.internal.common.zzac
    public final zzaj<E> zze() {
        return listIterator(0);
    }

    @Override // java.util.List
    /* renamed from: zzh */
    public zzag<E> subList(int i2, int i3) {
        zzs.zzc(i2, i3, size());
        int i4 = i3 - i2;
        return i4 == size() ? this : i4 == 0 ? zzai.f5852b : new zzaf(this, i2, i4);
    }

    @Override // java.util.List
    /* renamed from: zzo */
    public final zzak<E> listIterator(int i2) {
        zzs.zzb(i2, size(), FirebaseAnalytics.Param.INDEX);
        return isEmpty() ? (zzak<E>) zza : new zzae(this, i2);
    }
}

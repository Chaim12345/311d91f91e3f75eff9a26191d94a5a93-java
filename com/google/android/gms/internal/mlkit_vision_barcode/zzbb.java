package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;
import javax.annotation.CheckForNull;
/* loaded from: classes2.dex */
class zzbb extends AbstractCollection {

    /* renamed from: a  reason: collision with root package name */
    final Object f6258a;

    /* renamed from: b  reason: collision with root package name */
    Collection f6259b;
    @CheckForNull

    /* renamed from: c  reason: collision with root package name */
    final zzbb f6260c;
    @CheckForNull

    /* renamed from: d  reason: collision with root package name */
    final Collection f6261d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzbe f6262e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbb(zzbe zzbeVar, Object obj, @CheckForNull Collection collection, zzbb zzbbVar) {
        this.f6262e = zzbeVar;
        this.f6258a = obj;
        this.f6259b = collection;
        this.f6260c = zzbbVar;
        this.f6261d = zzbbVar == null ? null : zzbbVar.f6259b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        zzbb zzbbVar = this.f6260c;
        if (zzbbVar != null) {
            zzbbVar.a();
        } else {
            zzbe.j(this.f6262e).put(this.f6258a, this.f6259b);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        zzb();
        boolean isEmpty = this.f6259b.isEmpty();
        boolean add = this.f6259b.add(obj);
        if (add) {
            zzbe.e(this.f6262e);
            if (isEmpty) {
                a();
                return true;
            }
            return add;
        }
        return add;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean addAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = this.f6259b.addAll(collection);
        if (addAll) {
            zzbe.g(this.f6262e, this.f6259b.size() - size);
            if (size == 0) {
                a();
                return true;
            }
            return addAll;
        }
        return addAll;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b() {
        zzbb zzbbVar = this.f6260c;
        if (zzbbVar != null) {
            zzbbVar.b();
        } else if (this.f6259b.isEmpty()) {
            zzbe.j(this.f6262e).remove(this.f6258a);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        int size = size();
        if (size == 0) {
            return;
        }
        this.f6259b.clear();
        zzbe.h(this.f6262e, size);
        b();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        zzb();
        return this.f6259b.contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean containsAll(Collection collection) {
        zzb();
        return this.f6259b.containsAll(collection);
    }

    @Override // java.util.Collection
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        zzb();
        return this.f6259b.equals(obj);
    }

    @Override // java.util.Collection
    public final int hashCode() {
        zzb();
        return this.f6259b.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        zzb();
        return new zzba(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean remove(@CheckForNull Object obj) {
        zzb();
        boolean remove = this.f6259b.remove(obj);
        if (remove) {
            zzbe.f(this.f6262e);
            b();
        }
        return remove;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean removeAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean removeAll = this.f6259b.removeAll(collection);
        if (removeAll) {
            zzbe.g(this.f6262e, this.f6259b.size() - size);
            b();
        }
        return removeAll;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean retainAll(Collection collection) {
        Objects.requireNonNull(collection);
        int size = size();
        boolean retainAll = this.f6259b.retainAll(collection);
        if (retainAll) {
            zzbe.g(this.f6262e, this.f6259b.size() - size);
            b();
        }
        return retainAll;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        zzb();
        return this.f6259b.size();
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        zzb();
        return this.f6259b.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb() {
        Collection collection;
        zzbb zzbbVar = this.f6260c;
        if (zzbbVar != null) {
            zzbbVar.zzb();
            if (this.f6260c.f6259b != this.f6261d) {
                throw new ConcurrentModificationException();
            }
        } else if (!this.f6259b.isEmpty() || (collection = (Collection) zzbe.j(this.f6262e).get(this.f6258a)) == null) {
        } else {
            this.f6259b = collection;
        }
    }
}

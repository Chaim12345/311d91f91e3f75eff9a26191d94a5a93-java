package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes2.dex */
class zzba implements Iterator {

    /* renamed from: a  reason: collision with root package name */
    final Iterator f6255a;

    /* renamed from: b  reason: collision with root package name */
    final Collection f6256b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzbb f6257c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzba(zzbb zzbbVar) {
        this.f6257c = zzbbVar;
        Collection collection = zzbbVar.f6259b;
        this.f6256b = collection;
        this.f6255a = collection instanceof List ? ((List) collection).listIterator() : collection.iterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzba(zzbb zzbbVar, Iterator it) {
        this.f6257c = zzbbVar;
        this.f6256b = zzbbVar.f6259b;
        this.f6255a = it;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        this.f6257c.zzb();
        if (this.f6257c.f6259b != this.f6256b) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        a();
        return this.f6255a.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        a();
        return this.f6255a.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.f6255a.remove();
        zzbe.f(this.f6257c.f6262e);
        this.f6257c.b();
    }
}

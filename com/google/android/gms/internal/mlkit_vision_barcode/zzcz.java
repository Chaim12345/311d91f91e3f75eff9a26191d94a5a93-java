package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Iterator;
import java.util.Objects;
/* loaded from: classes2.dex */
abstract class zzcz implements Iterator {

    /* renamed from: a  reason: collision with root package name */
    final Iterator f6293a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcz(Iterator it) {
        Objects.requireNonNull(it);
        this.f6293a = it;
    }

    abstract Object a(Object obj);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f6293a.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        return a(this.f6293a.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.f6293a.remove();
    }
}

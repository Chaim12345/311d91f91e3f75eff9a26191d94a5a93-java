package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;
/* loaded from: classes2.dex */
final class zzax implements Iterator {
    @CheckForNull

    /* renamed from: a  reason: collision with root package name */
    Map.Entry f6251a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Iterator f6252b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzay f6253c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzax(zzay zzayVar, Iterator it) {
        this.f6253c = zzayVar;
        this.f6252b = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f6252b.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Map.Entry entry = (Map.Entry) this.f6252b.next();
        this.f6251a = entry;
        return entry.getKey();
    }

    @Override // java.util.Iterator
    public final void remove() {
        zzaq.zzd(this.f6251a != null, "no calls to next() since the last call to remove()");
        Collection collection = (Collection) this.f6251a.getValue();
        this.f6252b.remove();
        zzbe.h(this.f6253c.f6254b, collection.size());
        collection.clear();
        this.f6251a = null;
    }
}

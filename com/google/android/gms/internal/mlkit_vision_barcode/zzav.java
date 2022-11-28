package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;
/* loaded from: classes2.dex */
final class zzav implements Iterator {

    /* renamed from: a  reason: collision with root package name */
    final Iterator f6246a;
    @CheckForNull

    /* renamed from: b  reason: collision with root package name */
    Collection f6247b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzaw f6248c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzav(zzaw zzawVar) {
        this.f6248c = zzawVar;
        this.f6246a = zzawVar.f6249a.entrySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f6246a.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.f6246a.next();
        this.f6247b = (Collection) entry.getValue();
        zzaw zzawVar = this.f6248c;
        Object key = entry.getKey();
        return new zzby(key, zzawVar.f6250b.d(key, (Collection) entry.getValue()));
    }

    @Override // java.util.Iterator
    public final void remove() {
        zzaq.zzd(this.f6247b != null, "no calls to next() since the last call to remove()");
        this.f6246a.remove();
        zzbe.h(this.f6248c.f6250b, this.f6247b.size());
        this.f6247b.clear();
        this.f6247b = null;
    }
}

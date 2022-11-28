package com.google.android.gms.internal.measurement;

import java.util.Iterator;
/* loaded from: classes.dex */
final class zzmp implements Iterator {

    /* renamed from: a  reason: collision with root package name */
    final Iterator f6109a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzmq f6110b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzmp(zzmq zzmqVar) {
        zzkr zzkrVar;
        this.f6110b = zzmqVar;
        zzkrVar = zzmqVar.zza;
        this.f6109a = zzkrVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f6109a.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.f6109a.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

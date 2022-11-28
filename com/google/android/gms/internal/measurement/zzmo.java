package com.google.android.gms.internal.measurement;

import java.util.ListIterator;
/* loaded from: classes.dex */
final class zzmo implements ListIterator {

    /* renamed from: a  reason: collision with root package name */
    final ListIterator f6106a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ int f6107b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzmq f6108c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzmo(zzmq zzmqVar, int i2) {
        zzkr zzkrVar;
        this.f6108c = zzmqVar;
        this.f6107b = i2;
        zzkrVar = zzmqVar.zza;
        this.f6106a = zzkrVar.listIterator(i2);
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void add(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.f6106a.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f6106a.hasPrevious();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.f6106a.next();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f6106a.nextIndex();
    }

    @Override // java.util.ListIterator
    public final /* bridge */ /* synthetic */ Object previous() {
        return (String) this.f6106a.previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f6106a.previousIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void set(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }
}

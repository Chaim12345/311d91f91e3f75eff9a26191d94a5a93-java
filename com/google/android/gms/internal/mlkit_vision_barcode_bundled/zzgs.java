package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.ListIterator;
/* loaded from: classes2.dex */
final class zzgs implements ListIterator {

    /* renamed from: a  reason: collision with root package name */
    final ListIterator f6435a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ int f6436b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzgu f6437c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgs(zzgu zzguVar, int i2) {
        zzev zzevVar;
        this.f6437c = zzguVar;
        this.f6436b = i2;
        zzevVar = zzguVar.zza;
        this.f6435a = zzevVar.listIterator(i2);
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void add(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.f6435a.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f6435a.hasPrevious();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.f6435a.next();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f6435a.nextIndex();
    }

    @Override // java.util.ListIterator
    public final /* bridge */ /* synthetic */ Object previous() {
        return (String) this.f6435a.previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f6435a.previousIndex();
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

package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.List;
import java.util.ListIterator;
/* loaded from: classes2.dex */
final class zzbc extends zzba implements ListIterator {

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzbd f6263d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbc(zzbd zzbdVar) {
        super(zzbdVar);
        this.f6263d = zzbdVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbc(zzbd zzbdVar, int i2) {
        super(zzbdVar, ((List) zzbdVar.f6259b).listIterator(i2));
        this.f6263d = zzbdVar;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        boolean isEmpty = this.f6263d.isEmpty();
        a();
        ((ListIterator) this.f6255a).add(obj);
        zzbe.e(this.f6263d.f6264f);
        if (isEmpty) {
            this.f6263d.a();
        }
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        a();
        return ((ListIterator) this.f6255a).hasPrevious();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        a();
        return ((ListIterator) this.f6255a).nextIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        a();
        return ((ListIterator) this.f6255a).previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        a();
        return ((ListIterator) this.f6255a).previousIndex();
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        a();
        ((ListIterator) this.f6255a).set(obj);
    }
}

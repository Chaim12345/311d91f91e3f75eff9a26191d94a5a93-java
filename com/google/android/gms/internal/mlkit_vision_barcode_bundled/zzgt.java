package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Iterator;
/* loaded from: classes2.dex */
final class zzgt implements Iterator {

    /* renamed from: a  reason: collision with root package name */
    final Iterator f6438a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzgu f6439b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgt(zzgu zzguVar) {
        zzev zzevVar;
        this.f6439b = zzguVar;
        zzevVar = zzguVar.zza;
        this.f6438a = zzevVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f6438a.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.f6438a.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzbr extends AbstractCollection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzbs f6275a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbr(zzbs zzbsVar) {
        this.f6275a = zzbsVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.f6275a.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        zzbs zzbsVar = this.f6275a;
        Map l2 = zzbsVar.l();
        return l2 != null ? l2.values().iterator() : new zzbm(zzbsVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.f6275a.size();
    }
}

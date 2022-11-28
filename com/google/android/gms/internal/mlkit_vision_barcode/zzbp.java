package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;
/* loaded from: classes2.dex */
final class zzbp extends AbstractSet {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzbs f6273a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbp(zzbs zzbsVar) {
        this.f6273a = zzbsVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.f6273a.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        return this.f6273a.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        zzbs zzbsVar = this.f6273a;
        Map l2 = zzbsVar.l();
        return l2 != null ? l2.keySet().iterator() : new zzbk(zzbsVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        Object zzx;
        Object obj2;
        Map l2 = this.f6273a.l();
        if (l2 != null) {
            return l2.keySet().remove(obj);
        }
        zzx = this.f6273a.zzx(obj);
        obj2 = zzbs.zzd;
        return zzx != obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f6273a.size();
    }
}

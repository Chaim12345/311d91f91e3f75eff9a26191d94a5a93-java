package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;
/* loaded from: classes2.dex */
final class zzbn extends AbstractSet {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzbs f6268a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbn(zzbs zzbsVar) {
        this.f6268a = zzbsVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.f6268a.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        int zzv;
        Map l2 = this.f6268a.l();
        if (l2 != null) {
            return l2.entrySet().contains(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            zzv = this.f6268a.zzv(entry.getKey());
            if (zzv != -1 && zzam.zza(zzbs.j(this.f6268a, zzv), entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        zzbs zzbsVar = this.f6268a;
        Map l2 = zzbsVar.l();
        return l2 != null ? l2.entrySet().iterator() : new zzbl(zzbsVar);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        int zzu;
        int[] zzz;
        Object[] zzA;
        Object[] zzB;
        Map l2 = this.f6268a.l();
        if (l2 != null) {
            return l2.entrySet().remove(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            zzbs zzbsVar = this.f6268a;
            if (zzbsVar.q()) {
                return false;
            }
            zzu = zzbsVar.zzu();
            Object key = entry.getKey();
            Object value = entry.getValue();
            Object k2 = zzbs.k(this.f6268a);
            zzz = this.f6268a.zzz();
            zzA = this.f6268a.zzA();
            zzB = this.f6268a.zzB();
            int b2 = zzbt.b(key, value, zzu, k2, zzz, zzA, zzB);
            if (b2 == -1) {
                return false;
            }
            this.f6268a.p(b2, zzu);
            zzbs.b(this.f6268a);
            this.f6268a.n();
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f6268a.size();
    }
}

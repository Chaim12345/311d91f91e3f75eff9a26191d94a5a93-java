package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzaw extends zzcn {

    /* renamed from: a  reason: collision with root package name */
    final transient Map f6249a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzbe f6250b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaw(zzbe zzbeVar, Map map) {
        this.f6250b = zzbeVar;
        this.f6249a = map;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcn
    protected final Set a() {
        return new zzau(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        Map map;
        Map map2 = this.f6249a;
        zzbe zzbeVar = this.f6250b;
        map = zzbeVar.zza;
        if (map2 == map) {
            zzbeVar.zzn();
        } else {
            zzcg.a(new zzav(this));
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(@CheckForNull Object obj) {
        return zzco.b(this.f6249a, obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(@CheckForNull Object obj) {
        return this == obj || this.f6249a.equals(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final /* bridge */ /* synthetic */ Object get(@CheckForNull Object obj) {
        Collection collection = (Collection) zzco.a(this.f6249a, obj);
        if (collection == null) {
            return null;
        }
        return this.f6250b.d(obj, collection);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        return this.f6249a.hashCode();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcn, java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return this.f6250b.zzq();
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public final /* bridge */ /* synthetic */ Object remove(@CheckForNull Object obj) {
        Collection collection = (Collection) this.f6249a.remove(obj);
        if (collection == null) {
            return null;
        }
        Collection c2 = this.f6250b.c();
        c2.addAll(collection);
        zzbe.h(this.f6250b, collection.size());
        collection.clear();
        return c2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f6249a.size();
    }

    @Override // java.util.AbstractMap
    public final String toString() {
        return this.f6249a.toString();
    }
}

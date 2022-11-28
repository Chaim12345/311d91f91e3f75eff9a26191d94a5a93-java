package com.google.android.gms.internal.mlkit_vision_barcode;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Set;
import javax.annotation.CheckForNull;
/* loaded from: classes2.dex */
public abstract class zzbe extends zzbg implements Serializable {
    private transient Map zza;
    private transient int zzb;

    public zzbe(Map map) {
        if (!map.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.zza = map;
    }

    public static /* synthetic */ int e(zzbe zzbeVar) {
        int i2 = zzbeVar.zzb;
        zzbeVar.zzb = i2 + 1;
        return i2;
    }

    public static /* synthetic */ int f(zzbe zzbeVar) {
        int i2 = zzbeVar.zzb;
        zzbeVar.zzb = i2 - 1;
        return i2;
    }

    public static /* synthetic */ int g(zzbe zzbeVar, int i2) {
        int i3 = zzbeVar.zzb + i2;
        zzbeVar.zzb = i3;
        return i3;
    }

    public static /* synthetic */ int h(zzbe zzbeVar, int i2) {
        int i3 = zzbeVar.zzb - i2;
        zzbeVar.zzb = i3;
        return i3;
    }

    public static /* synthetic */ Map j(zzbe zzbeVar) {
        return zzbeVar.zza;
    }

    public static /* synthetic */ void k(zzbe zzbeVar, Object obj) {
        Object obj2;
        Map map = zzbeVar.zza;
        Objects.requireNonNull(map);
        try {
            obj2 = map.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            obj2 = null;
        }
        Collection collection = (Collection) obj2;
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            zzbeVar.zzb -= size;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbg
    final Map a() {
        return new zzaw(this, this.zza);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbg
    final Set b() {
        return new zzay(this, this.zza);
    }

    public abstract Collection c();

    public Collection d(Object obj, Collection collection) {
        throw null;
    }

    public final List i(Object obj, List list, @CheckForNull zzbb zzbbVar) {
        return list instanceof RandomAccess ? new zzaz(this, obj, list, zzbbVar) : new zzbd(this, obj, list, zzbbVar);
    }

    public final Collection zzh(Object obj) {
        Collection collection = (Collection) this.zza.get(obj);
        if (collection == null) {
            collection = c();
        }
        return d(obj, collection);
    }

    public final void zzn() {
        for (Collection collection : this.zza.values()) {
            collection.clear();
        }
        this.zza.clear();
        this.zzb = 0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbg, com.google.android.gms.internal.mlkit_vision_barcode.zzcp
    public final boolean zzo(Object obj, Object obj2) {
        Collection collection = (Collection) this.zza.get(obj);
        if (collection != null) {
            if (collection.add(obj2)) {
                this.zzb++;
                return true;
            }
            return false;
        }
        Collection c2 = c();
        if (c2.add(obj2)) {
            this.zzb++;
            this.zza.put(obj, c2);
            return true;
        }
        throw new AssertionError("New Collection violated the Collection spec");
    }
}

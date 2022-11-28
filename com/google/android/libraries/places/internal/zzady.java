package com.google.android.libraries.places.internal;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
/* loaded from: classes2.dex */
public final class zzady extends zzacd implements RandomAccess, zzadz {
    public static final zzadz zza;
    private static final zzady zzb;
    private final List zzc;

    static {
        zzady zzadyVar = new zzady(10);
        zzb = zzadyVar;
        zzadyVar.zzb();
        zza = zzadyVar;
    }

    public zzady() {
        this(10);
    }

    public zzady(int i2) {
        this.zzc = new ArrayList(i2);
    }

    private zzady(ArrayList arrayList) {
        this.zzc = arrayList;
    }

    private static String zzi(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzacp ? ((zzacp) obj).zzm(zzads.zzb) : zzads.zzh((byte[]) obj);
    }

    @Override // com.google.android.libraries.places.internal.zzacd, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i2, Object obj) {
        zza();
        this.zzc.add(i2, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.libraries.places.internal.zzacd, java.util.AbstractList, java.util.List
    public final boolean addAll(int i2, Collection collection) {
        zza();
        if (collection instanceof zzadz) {
            collection = ((zzadz) collection).zzh();
        }
        boolean addAll = this.zzc.addAll(i2, collection);
        ((AbstractList) this).modCount++;
        return addAll;
    }

    @Override // com.google.android.libraries.places.internal.zzacd, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }

    @Override // com.google.android.libraries.places.internal.zzacd, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zza();
        this.zzc.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.android.libraries.places.internal.zzacd, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i2) {
        zza();
        Object remove = this.zzc.remove(i2);
        ((AbstractList) this).modCount++;
        return zzi(remove);
    }

    @Override // com.google.android.libraries.places.internal.zzacd, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i2, Object obj) {
        zza();
        return zzi(this.zzc.set(i2, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc.size();
    }

    @Override // com.google.android.libraries.places.internal.zzadz
    public final zzadz zzd() {
        return zzc() ? new zzafy(this) : this;
    }

    @Override // com.google.android.libraries.places.internal.zzadz
    public final Object zze(int i2) {
        return this.zzc.get(i2);
    }

    @Override // com.google.android.libraries.places.internal.zzadr
    public final /* bridge */ /* synthetic */ zzadr zzf(int i2) {
        if (i2 >= size()) {
            ArrayList arrayList = new ArrayList(i2);
            arrayList.addAll(this.zzc);
            return new zzady(arrayList);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: zzg */
    public final String get(int i2) {
        Object obj = this.zzc.get(i2);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzacp) {
            zzacp zzacpVar = (zzacp) obj;
            String zzm = zzacpVar.zzm(zzads.zzb);
            if (zzacpVar.zzi()) {
                this.zzc.set(i2, zzm);
            }
            return zzm;
        }
        byte[] bArr = (byte[]) obj;
        String zzh = zzads.zzh(bArr);
        if (zzads.zzi(bArr)) {
            this.zzc.set(i2, zzh);
        }
        return zzh;
    }

    @Override // com.google.android.libraries.places.internal.zzadz
    public final List zzh() {
        return Collections.unmodifiableList(this.zzc);
    }
}

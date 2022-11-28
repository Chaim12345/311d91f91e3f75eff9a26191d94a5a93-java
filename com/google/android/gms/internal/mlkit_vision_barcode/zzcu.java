package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Iterator;
import javax.annotation.CheckForNull;
/* loaded from: classes2.dex */
final class zzcu extends zzcf {
    private final transient zzce zza;
    private final transient zzcc zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcu(zzce zzceVar, zzcc zzccVar) {
        this.zza = zzceVar;
        this.zzb = zzccVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbx
    public final int a(Object[] objArr, int i2) {
        return this.zzb.a(objArr, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbx, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcf, com.google.android.gms.internal.mlkit_vision_barcode.zzbx, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzcf, com.google.android.gms.internal.mlkit_vision_barcode.zzbx
    public final zzda zzd() {
        return this.zzb.listIterator(0);
    }
}

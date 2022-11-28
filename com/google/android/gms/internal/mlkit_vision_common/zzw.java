package com.google.android.gms.internal.mlkit_vision_common;

import java.util.Iterator;
import javax.annotation.CheckForNull;
/* loaded from: classes2.dex */
final class zzw extends zzs {
    private final transient zzr zza;
    private final transient zzp zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzw(zzr zzrVar, zzp zzpVar) {
        this.zza = zzrVar;
        this.zzb = zzpVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_common.zzl
    public final int a(Object[] objArr, int i2) {
        return this.zzb.a(objArr, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzl, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzs, com.google.android.gms.internal.mlkit_vision_common.zzl, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzs, com.google.android.gms.internal.mlkit_vision_common.zzl
    public final zzaa zzd() {
        return this.zzb.listIterator(0);
    }
}

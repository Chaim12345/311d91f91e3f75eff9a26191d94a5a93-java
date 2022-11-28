package com.google.android.gms.internal.mlkit_common;

import java.util.Iterator;
import javax.annotation.CheckForNull;
/* loaded from: classes.dex */
final class zzau extends zzap {
    private final transient zzao zza;
    private final transient zzam zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzau(zzao zzaoVar, zzam zzamVar) {
        this.zza = zzaoVar;
        this.zzb = zzamVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_common.zzai
    public final int a(Object[] objArr, int i2) {
        return this.zzb.a(objArr, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzai, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzap, com.google.android.gms.internal.mlkit_common.zzai, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzap, com.google.android.gms.internal.mlkit_common.zzai
    public final zzay zzd() {
        return this.zzb.listIterator(0);
    }
}

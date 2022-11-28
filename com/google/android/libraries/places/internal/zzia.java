package com.google.android.libraries.places.internal;

import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzia extends AbstractSequentialList implements Serializable {
    final List zza;
    final zzaz zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzia(List list, zzaz zzazVar, byte[] bArr) {
        Objects.requireNonNull(list);
        this.zza = list;
        this.zzb = zzazVar;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        this.zza.clear();
    }

    @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i2) {
        return new zzhz(this, this.zza.listIterator(i2));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }
}

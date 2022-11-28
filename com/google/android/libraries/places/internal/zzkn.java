package com.google.android.libraries.places.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes2.dex */
final class zzkn implements Iterator {
    final /* synthetic */ zzko zza;
    private int zzb = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkn(zzko zzkoVar) {
        this.zza = zzkoVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i2 = this.zzb;
        zzko zzkoVar = this.zza;
        return i2 < zzkoVar.zza() - zzkoVar.zzb();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object[] objArr;
        int i2 = this.zzb;
        zzko zzkoVar = this.zza;
        if (i2 < zzkoVar.zza() - zzkoVar.zzb()) {
            zzko zzkoVar2 = this.zza;
            objArr = zzkoVar2.zzb.zzb;
            Object obj = objArr[zzkoVar2.zzb() + i2];
            this.zzb = i2 + 1;
            return obj;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

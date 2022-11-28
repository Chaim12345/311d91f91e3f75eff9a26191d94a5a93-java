package com.google.android.libraries.places.internal;

import java.util.Iterator;
/* loaded from: classes2.dex */
final class zzafx implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzafy zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzafx(zzafy zzafyVar) {
        zzadz zzadzVar;
        this.zzb = zzafyVar;
        zzadzVar = zzafyVar.zza;
        this.zza = zzadzVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.zza.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzad implements Iterator {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzae f5921a;
    private int zzb = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzad(zzae zzaeVar) {
        this.f5921a = zzaeVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.f5921a.zzc();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        if (this.zzb < this.f5921a.zzc()) {
            zzae zzaeVar = this.f5921a;
            int i2 = this.zzb;
            this.zzb = i2 + 1;
            return zzaeVar.zze(i2);
        }
        int i3 = this.zzb;
        throw new NoSuchElementException("Out of bounds index: " + i3);
    }
}

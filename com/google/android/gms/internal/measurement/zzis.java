package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;
/* loaded from: classes.dex */
final class zzis extends zziu {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzjb f6092a;
    private int zzb = 0;
    private final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzis(zzjb zzjbVar) {
        this.f6092a = zzjbVar;
        this.zzc = zzjbVar.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zziw
    public final byte zza() {
        int i2 = this.zzb;
        if (i2 < this.zzc) {
            this.zzb = i2 + 1;
            return this.f6092a.a(i2);
        }
        throw new NoSuchElementException();
    }
}

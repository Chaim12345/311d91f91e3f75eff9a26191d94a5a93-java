package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.NoSuchElementException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzfx extends zzcv {

    /* renamed from: a  reason: collision with root package name */
    final zzfz f6427a;

    /* renamed from: b  reason: collision with root package name */
    zzcx f6428b = zzb();

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzga f6429c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfx(zzga zzgaVar) {
        this.f6429c = zzgaVar;
        this.f6427a = new zzfz(zzgaVar, null);
    }

    private final zzcx zzb() {
        zzfz zzfzVar = this.f6427a;
        if (zzfzVar.hasNext()) {
            return zzfzVar.next().iterator();
        }
        return null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f6428b != null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcx
    public final byte zza() {
        zzcx zzcxVar = this.f6428b;
        if (zzcxVar != null) {
            byte zza = zzcxVar.zza();
            if (!this.f6428b.hasNext()) {
                this.f6428b = zzb();
            }
            return zza;
        }
        throw new NoSuchElementException();
    }
}

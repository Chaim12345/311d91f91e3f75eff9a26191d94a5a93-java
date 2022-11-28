package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes2.dex */
final class zzfz implements Iterator {
    private final ArrayDeque zza;
    private zzcy zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfz(zzdb zzdbVar, zzfx zzfxVar) {
        zzcy zzcyVar;
        zzdb zzdbVar2;
        if (zzdbVar instanceof zzga) {
            zzga zzgaVar = (zzga) zzdbVar;
            ArrayDeque arrayDeque = new ArrayDeque(zzgaVar.c());
            this.zza = arrayDeque;
            arrayDeque.push(zzgaVar);
            zzdbVar2 = zzgaVar.zzd;
            zzcyVar = zzb(zzdbVar2);
        } else {
            this.zza = null;
            zzcyVar = (zzcy) zzdbVar;
        }
        this.zzb = zzcyVar;
    }

    private final zzcy zzb(zzdb zzdbVar) {
        while (zzdbVar instanceof zzga) {
            zzga zzgaVar = (zzga) zzdbVar;
            this.zza.push(zzgaVar);
            zzdbVar = zzgaVar.zzd;
        }
        return (zzcy) zzdbVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb != null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    /* renamed from: zza */
    public final zzcy next() {
        zzcy zzcyVar;
        zzdb zzdbVar;
        zzcy zzcyVar2 = this.zzb;
        if (zzcyVar2 != null) {
            do {
                ArrayDeque arrayDeque = this.zza;
                zzcyVar = null;
                if (arrayDeque == null || arrayDeque.isEmpty()) {
                    break;
                }
                zzdbVar = ((zzga) this.zza.pop()).zze;
                zzcyVar = zzb(zzdbVar);
            } while (zzcyVar.zzd() == 0);
            this.zzb = zzcyVar;
            return zzcyVar2;
        }
        throw new NoSuchElementException();
    }
}

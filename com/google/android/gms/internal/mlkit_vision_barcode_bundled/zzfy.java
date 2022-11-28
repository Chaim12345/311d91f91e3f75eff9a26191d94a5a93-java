package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.ArrayDeque;
import java.util.Arrays;
/* loaded from: classes2.dex */
public final class zzfy {
    private final ArrayDeque zza = new ArrayDeque();

    private zzfy() {
    }

    public /* synthetic */ zzfy(zzfx zzfxVar) {
    }

    public static /* synthetic */ zzdb a(zzfy zzfyVar, zzdb zzdbVar, zzdb zzdbVar2) {
        zzfyVar.zzb(zzdbVar);
        zzfyVar.zzb(zzdbVar2);
        zzdb zzdbVar3 = (zzdb) zzfyVar.zza.pop();
        while (!zzfyVar.zza.isEmpty()) {
            zzdbVar3 = new zzga((zzdb) zzfyVar.zza.pop(), zzdbVar3, null);
        }
        return zzdbVar3;
    }

    private final void zzb(zzdb zzdbVar) {
        zzdb zzdbVar2;
        zzdb zzdbVar3;
        if (!zzdbVar.d()) {
            if (!(zzdbVar instanceof zzga)) {
                throw new IllegalArgumentException("Has a new type of ByteString been created? Found ".concat(String.valueOf(zzdbVar.getClass())));
            }
            zzga zzgaVar = (zzga) zzdbVar;
            zzdbVar2 = zzgaVar.zzd;
            zzb(zzdbVar2);
            zzdbVar3 = zzgaVar.zze;
            zzb(zzdbVar3);
            return;
        }
        int zzc = zzc(zzdbVar.zzd());
        int m2 = zzga.m(zzc + 1);
        if (this.zza.isEmpty() || ((zzdb) this.zza.peek()).zzd() >= m2) {
            this.zza.push(zzdbVar);
            return;
        }
        int m3 = zzga.m(zzc);
        zzdb zzdbVar4 = (zzdb) this.zza.pop();
        while (!this.zza.isEmpty() && ((zzdb) this.zza.peek()).zzd() < m3) {
            zzdbVar4 = new zzga((zzdb) this.zza.pop(), zzdbVar4, null);
        }
        zzga zzgaVar2 = new zzga(zzdbVar4, zzdbVar, null);
        while (!this.zza.isEmpty()) {
            if (((zzdb) this.zza.peek()).zzd() >= zzga.m(zzc(zzgaVar2.zzd()) + 1)) {
                break;
            }
            zzgaVar2 = new zzga((zzdb) this.zza.pop(), zzgaVar2, null);
        }
        this.zza.push(zzgaVar2);
    }

    private static final int zzc(int i2) {
        int binarySearch = Arrays.binarySearch(zzga.f6431a, i2);
        return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
    }
}

package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzek;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;
/* loaded from: classes2.dex */
public final class zzf extends zzec implements zzfm {
    private static final zzf zza;
    private zzek zze = zzec.j();

    static {
        zzf zzfVar = new zzf();
        zza = zzfVar;
        zzec.n(zzf.class, zzfVar);
    }

    private zzf() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void r(zzf zzfVar, zzc zzcVar) {
        zzcVar.getClass();
        zzek zzekVar = zzfVar.zze;
        if (!zzekVar.zzc()) {
            zzfVar.zze = zzec.k(zzekVar);
        }
        zzfVar.zze.add(zzcVar);
    }

    public static zze zza() {
        return (zze) zza.c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    public final Object p(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            return null;
                        }
                        return zza;
                    }
                    return new zze(null);
                }
                return new zzf();
            }
            return zzec.m(zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzc.class});
        }
        return (byte) 1;
    }
}

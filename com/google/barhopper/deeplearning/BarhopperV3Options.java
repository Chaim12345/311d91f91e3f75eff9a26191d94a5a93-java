package com.google.barhopper.deeplearning;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;
/* loaded from: classes2.dex */
public final class BarhopperV3Options extends zzec<BarhopperV3Options, zzk> implements zzfm {
    private static final BarhopperV3Options zza;
    private int zze;
    private zzi zzf;
    private zzn zzg;

    static {
        BarhopperV3Options barhopperV3Options = new BarhopperV3Options();
        zza = barhopperV3Options;
        zzec.n(BarhopperV3Options.class, barhopperV3Options);
    }

    private BarhopperV3Options() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void r(BarhopperV3Options barhopperV3Options, zzi zziVar) {
        zziVar.getClass();
        barhopperV3Options.zzf = zziVar;
        barhopperV3Options.zze |= 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void s(BarhopperV3Options barhopperV3Options, zzn zznVar) {
        zznVar.getClass();
        barhopperV3Options.zzg = zznVar;
        barhopperV3Options.zze |= 2;
    }

    public static zzk zza() {
        return (zzk) zza.c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    @NonNull
    public final Object p(int i2, @NonNull Object obj, @NonNull Object obj2) {
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
                    return new zzk(null);
                }
                return new BarhopperV3Options();
            }
            return zzec.m(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }
}

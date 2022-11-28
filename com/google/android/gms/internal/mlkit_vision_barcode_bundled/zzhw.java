package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes2.dex */
public final class zzhw extends zzec implements zzfm {
    private static final zzhw zza;
    private int zze;
    private int zzf;
    private long zzg;

    static {
        zzhw zzhwVar = new zzhw();
        zza = zzhwVar;
        zzec.n(zzhw.class, zzhwVar);
    }

    private zzhw() {
    }

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
                    return new zzhv(null);
                }
                return new zzhw();
            }
            return zzec.m(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", zzhx.f6447a, "zzg"});
        }
        return (byte) 1;
    }
}

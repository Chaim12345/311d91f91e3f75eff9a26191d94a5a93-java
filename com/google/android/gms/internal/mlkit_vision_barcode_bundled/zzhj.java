package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes2.dex */
public final class zzhj extends zzec implements zzfm {
    private static final zzhj zza;
    private int zze;
    private int zzf;
    private zzjc zzg;
    private zziq zzh;
    private zzis zzi;

    static {
        zzhj zzhjVar = new zzhj();
        zza = zzhjVar;
        zzec.n(zzhj.class, zzhjVar);
    }

    private zzhj() {
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
                    return new zzhi(null);
                }
                return new zzhj();
            }
            return zzec.m(zza, "\u0001\u0004\u0000\u0001\u0001\u0005\u0004\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0005ဉ\u0003", new Object[]{"zze", "zzf", zzic.f6451a, "zzg", "zzh", "zzi"});
        }
        return (byte) 1;
    }
}

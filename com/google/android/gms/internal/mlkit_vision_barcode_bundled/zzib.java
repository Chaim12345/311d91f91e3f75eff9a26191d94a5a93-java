package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes2.dex */
public final class zzib extends zzec implements zzfm {
    private static final zzib zza;
    private int zze;
    private int zzf;
    private zzhu zzi;
    private int zzk;
    private int zzl;
    private zzek zzg = zzec.j();
    private int zzh = -1;
    private String zzj = "";

    static {
        zzib zzibVar = new zzib();
        zza = zzibVar;
        zzec.n(zzib.class, zzibVar);
    }

    private zzib() {
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
                    return new zzhy(null);
                }
                return new zzib();
            }
            return zzec.m(zza, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0001\u0000\u0001ဌ\u0000\u0002\u001b\u0003င\u0001\u0004ဉ\u0002\u0005ဈ\u0003\u0006ဌ\u0004\u0007ဌ\u0005", new Object[]{"zze", "zzf", zzhx.f6447a, "zzg", zzhw.class, "zzh", "zzi", "zzj", "zzk", zzhz.f6448a, "zzl", zzia.f6450a});
        }
        return (byte) 1;
    }
}

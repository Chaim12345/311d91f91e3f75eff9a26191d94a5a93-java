package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes2.dex */
public final class zzy extends zzec implements zzfm {
    private static final zzy zza;
    private int zze;
    private zzab zzf;

    static {
        zzy zzyVar = new zzy();
        zza = zzyVar;
        zzec.n(zzy.class, zzyVar);
    }

    private zzy() {
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
                    return new zzx(null);
                }
                return new zzy();
            }
            return zzec.m(zza, "\u0001\u0001\u0000\u0001\u000f\u000f\u0001\u0000\u0000\u0000\u000fဉ\u0000", new Object[]{"zze", "zzf"});
        }
        return (byte) 1;
    }
}

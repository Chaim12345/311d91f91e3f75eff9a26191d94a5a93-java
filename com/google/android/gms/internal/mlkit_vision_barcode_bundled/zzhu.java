package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes2.dex */
public final class zzhu extends zzec implements zzfm {
    private static final zzhu zza;
    private int zze;
    private int zzf;
    private int zzg;
    private zzek zzh = zzec.j();
    private int zzi;

    static {
        zzhu zzhuVar = new zzhu();
        zza = zzhuVar;
        zzec.n(zzhu.class, zzhuVar);
    }

    private zzhu() {
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
                    return new zzhs(null);
                }
                return new zzhu();
            }
            return zzec.m(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001ဌ\u0000\u0002င\u0001\u0003\u001a\u0004င\u0002", new Object[]{"zze", "zzf", zzht.f6446a, "zzg", "zzh", "zzi"});
        }
        return (byte) 1;
    }
}

package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes2.dex */
public final class zzci extends zzec implements zzfm {
    private static final zzci zza;
    private int zze;
    private int zzf;
    private String zzg = "";

    static {
        zzci zzciVar = new zzci();
        zza = zzciVar;
        zzec.n(zzci.class, zzciVar);
    }

    private zzci() {
    }

    public static zzci zzb() {
        return zza;
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
                    return new zzce(null);
                }
                return new zzci();
            }
            return zzec.m(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", zzcg.f6409a, "zzg"});
        }
        return (byte) 1;
    }

    public final String zzc() {
        return this.zzg;
    }

    public final int zzd() {
        int zza2 = zzch.zza(this.zzf);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }
}

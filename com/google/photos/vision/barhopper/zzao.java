package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;
/* loaded from: classes2.dex */
public final class zzao extends zzec implements zzfm {
    private static final zzao zza;
    private int zze;
    private int zzg;
    private boolean zzi;
    private byte zzj = 2;
    private String zzf = "";
    private String zzh = "";

    static {
        zzao zzaoVar = new zzao();
        zza = zzaoVar;
        zzec.n(zzao.class, zzaoVar);
    }

    private zzao() {
    }

    public static zzao zzb() {
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
                            this.zzj = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zza;
                    }
                    return new zzak(null);
                }
                return new zzao();
            }
            return zzec.m(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0001\u0001ᔈ\u0000\u0002ဌ\u0001\u0003ဈ\u0002\u0004ဇ\u0003", new Object[]{"zze", "zzf", "zzg", zzam.f10349a, "zzh", "zzi"});
        }
        return Byte.valueOf(this.zzj);
    }

    public final String zzc() {
        return this.zzh;
    }

    public final String zzd() {
        return this.zzf;
    }

    public final int zze() {
        int zza2 = zzan.zza(this.zzg);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }
}

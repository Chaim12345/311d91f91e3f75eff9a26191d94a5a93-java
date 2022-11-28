package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;
/* loaded from: classes2.dex */
public final class zzae extends zzec implements zzfm {
    private static final zzae zza;
    private int zze;
    private int zzf;
    private int zzg;
    private byte zzh = 2;

    static {
        zzae zzaeVar = new zzae();
        zza = zzaeVar;
        zzec.n(zzae.class, zzaeVar);
    }

    private zzae() {
    }

    public static /* synthetic */ void r(zzae zzaeVar, int i2) {
        zzaeVar.zze |= 1;
        zzaeVar.zzf = i2;
    }

    public static /* synthetic */ void s(zzae zzaeVar, int i2) {
        zzaeVar.zze |= 2;
        zzaeVar.zzg = i2;
    }

    public static zzad zzc() {
        return (zzad) zza.c();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    public final Object p(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            this.zzh = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zza;
                    }
                    return new zzad(null);
                }
                return new zzae();
            }
            return zzec.m(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔄ\u0000\u0002ᔄ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        return Byte.valueOf(this.zzh);
    }

    public final int zza() {
        return this.zzf;
    }

    public final int zzb() {
        return this.zzg;
    }
}

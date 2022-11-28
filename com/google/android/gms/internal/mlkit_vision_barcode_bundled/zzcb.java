package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.List;
/* loaded from: classes2.dex */
public final class zzcb extends zzec implements zzfm {
    private static final zzcb zza;
    private int zze;
    private int zzf;
    private zzf zzh;
    private byte zzi = 2;
    private zzek zzg = zzec.j();

    static {
        zzcb zzcbVar = new zzcb();
        zza = zzcbVar;
        zzec.n(zzcb.class, zzcbVar);
    }

    private zzcb() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    public final Object p(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            this.zzi = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zza;
                    }
                    return new zzbx(null);
                }
                return new zzcb();
            }
            return zzec.m(zza, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0001\u0001ဌ\u0000\u0002\u001a\u0003ᐉ\u0001", new Object[]{"zze", "zzf", zzbz.f6408a, "zzg", "zzh"});
        }
        return Byte.valueOf(this.zzi);
    }

    public final List zzb() {
        return this.zzg;
    }

    public final int zzc() {
        int zza2 = zzca.zza(this.zzf);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }
}

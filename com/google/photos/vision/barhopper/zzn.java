package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;
/* loaded from: classes2.dex */
public final class zzn extends zzec implements zzfm {
    private static final zzn zza;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private boolean zzl;

    static {
        zzn zznVar = new zzn();
        zza = zznVar;
        zzec.n(zzn.class, zznVar);
    }

    private zzn() {
    }

    public static zzn zzi() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
                    return new zzm(null);
                }
                return new zzn();
            }
            return zzec.m(zza, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004\u0006င\u0005\u0007ဇ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
        }
        return (byte) 1;
    }

    public final int zza() {
        return this.zzh;
    }

    public final int zzb() {
        return this.zzi;
    }

    public final int zzc() {
        return this.zzj;
    }

    public final int zzd() {
        return this.zzg;
    }

    public final int zze() {
        return this.zzk;
    }

    public final int zzf() {
        return this.zzf;
    }

    public final boolean zzj() {
        return this.zzl;
    }
}

package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcd;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzek;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;
import java.util.List;
/* loaded from: classes2.dex */
public final class zzr extends zzec implements zzfm {
    private static final zzr zza;
    private int zze;
    private zzcd zzf;
    private byte zzn = 2;
    private String zzg = "";
    private String zzh = "";
    private zzek zzi = zzec.j();
    private zzek zzj = zzec.j();
    private zzek zzk = zzec.j();
    private zzek zzl = zzec.j();
    private String zzm = "";

    static {
        zzr zzrVar = new zzr();
        zza = zzrVar;
        zzec.n(zzr.class, zzrVar);
    }

    private zzr() {
    }

    public static zzr zzc() {
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
                            this.zzn = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zza;
                    }
                    return new zzq(null);
                }
                return new zzr();
            }
            return zzec.m(zza, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0004\u0001\u0001ဉ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004\u001b\u0005\u001b\u0006\u001a\u0007Л\bဈ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", zzci.class, "zzj", zzy.class, "zzk", "zzl", zzcb.class, "zzm"});
        }
        return Byte.valueOf(this.zzn);
    }

    public final zzcd zza() {
        zzcd zzcdVar = this.zzf;
        return zzcdVar == null ? zzcd.zzb() : zzcdVar;
    }

    public final String zzd() {
        return this.zzg;
    }

    public final String zze() {
        return this.zzh;
    }

    public final List zzf() {
        return this.zzl;
    }

    public final List zzh() {
        return this.zzj;
    }

    public final List zzi() {
        return this.zzi;
    }

    public final List zzj() {
        return this.zzk;
    }
}

package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes2.dex */
public final class zzj extends zzec implements zzfm {
    private static final zzj zza;
    private int zze;
    private int zzh;
    private zzv zzi;
    private zzp zzj;
    private zzjh zzk;
    private int zzl;
    private byte zzn = 2;
    private int zzf = 17;
    private zzek zzg = zzec.j();
    private zzek zzm = zzec.j();

    static {
        zzj zzjVar = new zzj();
        zza = zzjVar;
        zzec.n(zzj.class, zzjVar);
    }

    private zzj() {
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
                    return new zzh(null);
                }
                return new zzj();
            }
            return zzec.m(zza, "\u0001\b\u0000\u0001\u0001\u000f\b\u0000\u0002\u0004\u0001ဌ\u0000\u0003Л\u0004င\u0001\u0005ᐉ\u0002\u0006ᐉ\u0003\u0007င\u0005\b\u001b\u000fᐉ\u0004", new Object[]{"zze", "zzf", zzi.f6449a, "zzg", zzaj.class, "zzh", "zzi", "zzj", "zzl", "zzm", zzam.class, "zzk"});
        }
        return Byte.valueOf(this.zzn);
    }
}

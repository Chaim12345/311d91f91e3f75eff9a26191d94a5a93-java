package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes2.dex */
public final class zzv extends zzec implements zzfm {
    public static final zzea zza;
    private static final zzv zze;
    private int zzf;
    private long zzg;
    private long zzh;
    private zzjh zzi;
    private byte zzj = 2;

    static {
        zzv zzvVar = new zzv();
        zze = zzvVar;
        zzec.n(zzv.class, zzvVar);
        zza = zzec.zzH(zzjh.zzf(), zzvVar, zzvVar, null, 13258261, zzhf.zzk, zzv.class);
    }

    private zzv() {
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
                        return zze;
                    }
                    return new zzu(null);
                }
                return new zzv();
            }
            return zzec.m(zze, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0003\u0001ᔅ\u0000\u0002ᔅ\u0001\u0003ᐉ\u0002", new Object[]{"zzf", "zzg", "zzh", "zzi"});
        }
        return Byte.valueOf(this.zzj);
    }
}

package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes2.dex */
public final class zzf extends zzec implements zzfm {
    public static final zzea zza;
    private static final zzf zze;
    private int zzf;
    private zzjh zzk;
    private zzf zzl;
    private zzy zzm;
    private byte zzn = 2;
    private String zzg = "";
    private zzek zzh = zzec.j();
    private zzek zzi = zzec.j();
    private zzek zzj = zzec.j();

    static {
        zzf zzfVar = new zzf();
        zze = zzfVar;
        zzec.n(zzf.class, zzfVar);
        zza = zzec.zzH(zzjh.zzf(), zzfVar, zzfVar, null, 12208774, zzhf.zzk, zzf.class);
    }

    private zzf() {
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
                        return zze;
                    }
                    return new zze(null);
                }
                return new zzf();
            }
            return zzec.m(zze, "\u0001\u0007\u0000\u0001\u0002Ǵ\u0007\u0000\u0003\u0004\u0002Л\u0005Л\u0006\u001b\bᐉ\u0001\nဈ\u0000\u000bᐉ\u0002Ǵဉ\u0003", new Object[]{"zzf", "zzh", zzj.class, "zzj", zzj.class, "zzi", zzm.class, "zzk", "zzg", "zzl", "zzm"});
        }
        return Byte.valueOf(this.zzn);
    }
}

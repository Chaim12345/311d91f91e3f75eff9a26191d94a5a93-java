package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes2.dex */
public final class zzaj extends zzec implements zzfm {
    public static final zzea zza;
    private static final zzej zze = new zzag();
    private static final zzaj zzf;
    private int zzg;
    private zzy zzm;
    private zzjh zzn;
    private byte zzo = 2;
    private String zzh = "";
    private String zzi = "";
    private zzei zzj = zzec.i();
    private String zzk = "";
    private String zzl = "";

    static {
        zzaj zzajVar = new zzaj();
        zzf = zzajVar;
        zzec.n(zzaj.class, zzajVar);
        zza = zzec.zzH(zzjh.zzf(), zzajVar, zzajVar, null, 308676116, zzhf.zzk, zzaj.class);
    }

    private zzaj() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    public final Object p(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            this.zzo = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zzf;
                    }
                    return new zzah(null);
                }
                return new zzaj();
            }
            return zzec.m(zzf, "\u0001\u0007\u0000\u0001\u0001Ǵ\u0007\u0000\u0001\u0002\u0001ᔈ\u0000\u0002ဈ\u0001\u0003\u001e\u0005ဈ\u0002\u0006ဈ\u0003\u000fᐉ\u0005Ǵဉ\u0004", new Object[]{"zzg", "zzh", "zzi", "zzj", zzai.f6405a, "zzk", "zzl", "zzn", "zzm"});
        }
        return Byte.valueOf(this.zzo);
    }
}

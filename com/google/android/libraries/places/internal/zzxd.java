package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzxd extends zzadk implements zzaes {
    private static final zzxd zzb;
    private int zze;
    private zzwy zzh;
    private zzor zzi;
    private int zzl;
    private int zzm;
    private int zzo;
    private byte zzp = 2;
    private String zzf = "";
    private String zzg = "";
    private int zzj = 1;
    private String zzk = "";
    private String zzn = "";

    static {
        zzxd zzxdVar = new zzxd();
        zzb = zzxdVar;
        zzadk.zzG(zzxd.class, zzxdVar);
    }

    private zzxd() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    public final Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            this.zzp = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zzb;
                    }
                    return new zzwz(null);
                }
                return new zzxd();
            }
            return zzadk.zzF(zzb, "\u0001\n\u0000\u0001\u0001\n\n\u0000\u0000\u0001\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဉ\u0002\u0004ᐉ\u0003\u0005ဌ\u0004\u0006ဈ\u0005\u0007ဌ\u0006\bင\u0007\tဈ\b\nဌ\t", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", zzxa.zza, "zzk", "zzl", zzxc.zza, "zzm", "zzn", "zzo", zzxb.zza});
        }
        return Byte.valueOf(this.zzp);
    }
}

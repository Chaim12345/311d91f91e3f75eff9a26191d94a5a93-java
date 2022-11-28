package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzzv extends zzadk implements zzaes {
    private static final zzzv zzb;
    private int zze;
    private zzor zzf;
    private int zzg;
    private int zzh;
    private int zzj;
    private byte zzk = 2;
    private String zzi = "";

    static {
        zzzv zzzvVar = new zzzv();
        zzb = zzzvVar;
        zzadk.zzG(zzzv.class, zzzvVar);
    }

    private zzzv() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    public final Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            this.zzk = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zzb;
                    }
                    return new zzzt(null);
                }
                return new zzzv();
            }
            return zzadk.zzF(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0001\u0001ᐉ\u0000\u0002င\u0001\u0003င\u0002\u0004ဈ\u0003\u0005ဌ\u0004", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", zzzu.zza});
        }
        return Byte.valueOf(this.zzk);
    }
}

package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzni extends zzadk implements zzaes {
    private static final zzni zzb;
    private int zze;
    private zznk zzg;
    private zznm zzh;
    private zzabx zzi;
    private byte zzj = 2;
    private int zzf = 1;

    static {
        zzni zzniVar = new zzni();
        zzb = zzniVar;
        zzadk.zzG(zzni.class, zzniVar);
    }

    private zzni() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    public final Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            this.zzj = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zzb;
                    }
                    return new zzng(null);
                }
                return new zzni();
            }
            return zzadk.zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0001\u0001ဌ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ᐉ\u0003", new Object[]{"zze", "zzf", zznh.zza, "zzg", "zzh", "zzi"});
        }
        return Byte.valueOf(this.zzj);
    }
}

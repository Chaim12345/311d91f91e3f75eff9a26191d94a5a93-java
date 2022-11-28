package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzyj extends zzadk implements zzaes {
    private static final zzyj zzb;
    private int zze;
    private zzop zzf;
    private byte zzg = 2;

    static {
        zzyj zzyjVar = new zzyj();
        zzb = zzyjVar;
        zzadk.zzG(zzyj.class, zzyjVar);
    }

    private zzyj() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    public final Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            this.zzg = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zzb;
                    }
                    return new zzyi(null);
                }
                return new zzyj();
            }
            return zzadk.zzF(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0001\u0001ᐉ\u0000", new Object[]{"zze", "zzf"});
        }
        return Byte.valueOf(this.zzg);
    }
}

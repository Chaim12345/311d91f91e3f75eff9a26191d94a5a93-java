package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzor extends zzadk implements zzaes {
    private static final zzor zzb;
    private int zze;
    private zzop zzf;
    private zzop zzg;
    private byte zzh = 2;

    static {
        zzor zzorVar = new zzor();
        zzb = zzorVar;
        zzadk.zzG(zzor.class, zzorVar);
    }

    private zzor() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    public final Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            this.zzh = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zzb;
                    }
                    return new zzoq(null);
                }
                return new zzor();
            }
            return zzadk.zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᐉ\u0000\u0002ᐉ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        return Byte.valueOf(this.zzh);
    }
}

package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzmk extends zzadk implements zzaes {
    private static final zzmk zzb;
    private int zze;
    private zzadr zzf = zzadk.zzB();
    private int zzg;

    static {
        zzmk zzmkVar = new zzmk();
        zzb = zzmkVar;
        zzadk.zzG(zzmk.class, zzmkVar);
    }

    private zzmk() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    public final Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzmj(null);
                }
                return new zzmk();
            }
            return zzadk.zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002င\u0000", new Object[]{"zze", "zzf", zzmf.class, "zzg"});
        }
        return (byte) 1;
    }
}

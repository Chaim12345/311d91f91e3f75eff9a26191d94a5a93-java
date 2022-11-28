package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzzs extends zzadk implements zzaes {
    private static final zzzs zzb;
    private zzadr zze = zzadk.zzB();
    private zzadr zzf = zzadk.zzB();

    static {
        zzzs zzzsVar = new zzzs();
        zzb = zzzsVar;
        zzadk.zzG(zzzs.class, zzzsVar);
    }

    private zzzs() {
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
                    return new zzzr(null);
                }
                return new zzzs();
            }
            return zzadk.zzF(zzb, "\u0001\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001\u001a\u0002\u001b", new Object[]{"zze", "zzf", zzvt.class});
        }
        return (byte) 1;
    }
}

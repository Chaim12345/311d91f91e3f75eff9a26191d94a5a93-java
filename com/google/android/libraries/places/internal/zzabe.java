package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzabe extends zzadk implements zzaes {
    private static final zzabe zzb;
    private zzadr zze = zzadk.zzB();

    static {
        zzabe zzabeVar = new zzabe();
        zzb = zzabeVar;
        zzadk.zzG(zzabe.class, zzabeVar);
    }

    private zzabe() {
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
                    return new zzabd(null);
                }
                return new zzabe();
            }
            return zzadk.zzF(zzb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzabp.class});
        }
        return (byte) 1;
    }
}

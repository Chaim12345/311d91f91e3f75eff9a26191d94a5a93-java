package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes.dex */
public final class zzgo extends zzkc implements zzlk {
    private static final zzgo zza;
    private zzkj zze = zzkc.i();

    static {
        zzgo zzgoVar = new zzgo();
        zza = zzgoVar;
        zzkc.m(zzgo.class, zzgoVar);
    }

    private zzgo() {
    }

    public static zzgo zzc() {
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzkc
    public final Object n(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            return null;
                        }
                        return zza;
                    }
                    return new zzgn(null);
                }
                return new zzgo();
            }
            return zzkc.l(zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzgq.class});
        }
        return (byte) 1;
    }

    public final int zza() {
        return this.zze.size();
    }

    public final List zzd() {
        return this.zze;
    }
}

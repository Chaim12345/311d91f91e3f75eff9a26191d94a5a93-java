package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
public final class zzfa extends zzkc implements zzlk {
    private static final zzfa zza;
    private int zze;
    private String zzf = "";
    private zzkj zzg = zzkc.i();
    private boolean zzh;

    static {
        zzfa zzfaVar = new zzfa();
        zza = zzfaVar;
        zzkc.m(zzfa.class, zzfaVar);
    }

    private zzfa() {
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
                    return new zzez(null);
                }
                return new zzfa();
            }
            return zzkc.l(zza, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b\u0003ဇ\u0001", new Object[]{"zze", "zzf", "zzg", zzfg.class, "zzh"});
        }
        return (byte) 1;
    }

    public final String zzb() {
        return this.zzf;
    }
}

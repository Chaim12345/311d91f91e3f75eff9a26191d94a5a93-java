package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
public final class zzfu extends zzkc implements zzlk {
    private static final zzfu zza;
    private int zze;
    private String zzf = "";
    private long zzg;

    static {
        zzfu zzfuVar = new zzfu();
        zza = zzfuVar;
        zzkc.m(zzfu.class, zzfuVar);
    }

    private zzfu() {
    }

    public static /* synthetic */ void p(zzfu zzfuVar, String str) {
        str.getClass();
        zzfuVar.zze |= 1;
        zzfuVar.zzf = str;
    }

    public static /* synthetic */ void q(zzfu zzfuVar, long j2) {
        zzfuVar.zze |= 2;
        zzfuVar.zzg = j2;
    }

    public static zzft zza() {
        return (zzft) zza.d();
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
                    return new zzft(null);
                }
                return new zzfu();
            }
            return zzkc.l(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }
}

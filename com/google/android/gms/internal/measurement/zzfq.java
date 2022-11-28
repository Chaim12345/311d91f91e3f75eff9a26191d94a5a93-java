package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
public final class zzfq extends zzkc implements zzlk {
    private static final zzfq zza;
    private int zze;
    private int zzf;
    private long zzg;

    static {
        zzfq zzfqVar = new zzfq();
        zza = zzfqVar;
        zzkc.m(zzfq.class, zzfqVar);
    }

    private zzfq() {
    }

    public static /* synthetic */ void p(zzfq zzfqVar, int i2) {
        zzfqVar.zze |= 1;
        zzfqVar.zzf = i2;
    }

    public static /* synthetic */ void q(zzfq zzfqVar, long j2) {
        zzfqVar.zze |= 2;
        zzfqVar.zzg = j2;
    }

    public static zzfp zzc() {
        return (zzfp) zza.d();
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
                    return new zzfp(null);
                }
                return new zzfq();
            }
            return zzkc.l(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final int zza() {
        return this.zzf;
    }

    public final long zzb() {
        return this.zzg;
    }

    public final boolean zzg() {
        return (this.zze & 2) != 0;
    }

    public final boolean zzh() {
        return (this.zze & 1) != 0;
    }
}

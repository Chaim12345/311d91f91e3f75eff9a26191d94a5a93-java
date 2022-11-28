package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes.dex */
public final class zzgj extends zzkc implements zzlk {
    private static final zzgj zza;
    private int zze;
    private int zzf;
    private zzki zzg = zzkc.g();

    static {
        zzgj zzgjVar = new zzgj();
        zza = zzgjVar;
        zzkc.m(zzgj.class, zzgjVar);
    }

    private zzgj() {
    }

    public static /* synthetic */ void p(zzgj zzgjVar, int i2) {
        zzgjVar.zze |= 1;
        zzgjVar.zzf = i2;
    }

    public static /* synthetic */ void q(zzgj zzgjVar, Iterable iterable) {
        zzki zzkiVar = zzgjVar.zzg;
        if (!zzkiVar.zzc()) {
            zzgjVar.zzg = zzkc.h(zzkiVar);
        }
        zzil.b(iterable, zzgjVar.zzg);
    }

    public static zzgi zzd() {
        return (zzgi) zza.d();
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
                    return new zzgi(null);
                }
                return new zzgj();
            }
            return zzkc.l(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001င\u0000\u0002\u0014", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final int zza() {
        return this.zzg.size();
    }

    public final int zzb() {
        return this.zzf;
    }

    public final long zzc(int i2) {
        return this.zzg.zza(i2);
    }

    public final List zzf() {
        return this.zzg;
    }

    public final boolean zzi() {
        return (this.zze & 1) != 0;
    }
}

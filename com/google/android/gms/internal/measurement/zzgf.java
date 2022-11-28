package com.google.android.gms.internal.measurement;
/* loaded from: classes.dex */
public final class zzgf extends zzkc implements zzlk {
    private static final zzgf zza;
    private int zze;
    private int zzf = 1;
    private zzkj zzg = zzkc.i();

    static {
        zzgf zzgfVar = new zzgf();
        zza = zzgfVar;
        zzkc.m(zzgf.class, zzgfVar);
    }

    private zzgf() {
    }

    public static /* synthetic */ void p(zzgf zzgfVar, zzfu zzfuVar) {
        zzfuVar.getClass();
        zzkj zzkjVar = zzgfVar.zzg;
        if (!zzkjVar.zzc()) {
            zzgfVar.zzg = zzkc.j(zzkjVar);
        }
        zzgfVar.zzg.add(zzfuVar);
    }

    public static zzgd zza() {
        return (zzgd) zza.d();
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
                    return new zzgd(null);
                }
                return new zzgf();
            }
            return zzkc.l(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဌ\u0000\u0002\u001b", new Object[]{"zze", "zzf", zzge.f6062a, "zzg", zzfu.class});
        }
        return (byte) 1;
    }
}

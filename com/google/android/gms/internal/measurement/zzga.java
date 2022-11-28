package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes.dex */
public final class zzga extends zzkc implements zzlk {
    private static final zzga zza;
    private zzkj zze = zzkc.i();

    static {
        zzga zzgaVar = new zzga();
        zza = zzgaVar;
        zzkc.m(zzga.class, zzgaVar);
    }

    private zzga() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void p(zzga zzgaVar, zzgc zzgcVar) {
        zzgcVar.getClass();
        zzkj zzkjVar = zzgaVar.zze;
        if (!zzkjVar.zzc()) {
            zzgaVar.zze = zzkc.j(zzkjVar);
        }
        zzgaVar.zze.add(zzgcVar);
    }

    public static zzfz zza() {
        return (zzfz) zza.d();
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
                    return new zzfz(null);
                }
                return new zzga();
            }
            return zzkc.l(zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzgc.class});
        }
        return (byte) 1;
    }

    public final zzgc zzc(int i2) {
        return (zzgc) this.zze.get(0);
    }

    public final List zzd() {
        return this.zze;
    }
}

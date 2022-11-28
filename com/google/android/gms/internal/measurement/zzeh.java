package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes.dex */
public final class zzeh extends zzkc implements zzlk {
    private static final zzeh zza;
    private int zze;
    private int zzf;
    private zzkj zzg = zzkc.i();
    private zzkj zzh = zzkc.i();
    private boolean zzi;
    private boolean zzj;

    static {
        zzeh zzehVar = new zzeh();
        zza = zzehVar;
        zzkc.m(zzeh.class, zzehVar);
    }

    private zzeh() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void p(zzeh zzehVar, int i2, zzes zzesVar) {
        zzesVar.getClass();
        zzkj zzkjVar = zzehVar.zzg;
        if (!zzkjVar.zzc()) {
            zzehVar.zzg = zzkc.j(zzkjVar);
        }
        zzehVar.zzg.set(i2, zzesVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void q(zzeh zzehVar, int i2, zzej zzejVar) {
        zzejVar.getClass();
        zzkj zzkjVar = zzehVar.zzh;
        if (!zzkjVar.zzc()) {
            zzehVar.zzh = zzkc.j(zzkjVar);
        }
        zzehVar.zzh.set(i2, zzejVar);
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
                    return new zzeg(null);
                }
                return new zzeh();
            }
            return zzkc.l(zza, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဇ\u0001\u0005ဇ\u0002", new Object[]{"zze", "zzf", "zzg", zzes.class, "zzh", zzej.class, "zzi", "zzj"});
        }
        return (byte) 1;
    }

    public final int zza() {
        return this.zzf;
    }

    public final int zzb() {
        return this.zzh.size();
    }

    public final int zzc() {
        return this.zzg.size();
    }

    public final zzej zze(int i2) {
        return (zzej) this.zzh.get(i2);
    }

    public final zzes zzf(int i2) {
        return (zzes) this.zzg.get(i2);
    }

    public final List zzg() {
        return this.zzh;
    }

    public final List zzh() {
        return this.zzg;
    }

    public final boolean zzk() {
        return (this.zze & 1) != 0;
    }
}

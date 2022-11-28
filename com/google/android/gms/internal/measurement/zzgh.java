package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes.dex */
public final class zzgh extends zzkc implements zzlk {
    private static final zzgh zza;
    private zzki zze = zzkc.g();
    private zzki zzf = zzkc.g();
    private zzkj zzg = zzkc.i();
    private zzkj zzh = zzkc.i();

    static {
        zzgh zzghVar = new zzgh();
        zza = zzghVar;
        zzkc.m(zzgh.class, zzghVar);
    }

    private zzgh() {
    }

    public static /* synthetic */ void p(zzgh zzghVar, Iterable iterable) {
        zzki zzkiVar = zzghVar.zze;
        if (!zzkiVar.zzc()) {
            zzghVar.zze = zzkc.h(zzkiVar);
        }
        zzil.b(iterable, zzghVar.zze);
    }

    public static /* synthetic */ void r(zzgh zzghVar, Iterable iterable) {
        zzki zzkiVar = zzghVar.zzf;
        if (!zzkiVar.zzc()) {
            zzghVar.zzf = zzkc.h(zzkiVar);
        }
        zzil.b(iterable, zzghVar.zzf);
    }

    public static /* synthetic */ void t(zzgh zzghVar, Iterable iterable) {
        zzghVar.zzy();
        zzil.b(iterable, zzghVar.zzg);
    }

    public static /* synthetic */ void v(zzgh zzghVar, int i2) {
        zzghVar.zzy();
        zzghVar.zzg.remove(i2);
    }

    public static /* synthetic */ void w(zzgh zzghVar, Iterable iterable) {
        zzghVar.zzz();
        zzil.b(iterable, zzghVar.zzh);
    }

    public static /* synthetic */ void y(zzgh zzghVar, int i2) {
        zzghVar.zzz();
        zzghVar.zzh.remove(i2);
    }

    public static zzgg zzf() {
        return (zzgg) zza.d();
    }

    public static zzgh zzh() {
        return zza;
    }

    private final void zzy() {
        zzkj zzkjVar = this.zzg;
        if (zzkjVar.zzc()) {
            return;
        }
        this.zzg = zzkc.j(zzkjVar);
    }

    private final void zzz() {
        zzkj zzkjVar = this.zzh;
        if (zzkjVar.zzc()) {
            return;
        }
        this.zzh = zzkc.j(zzkjVar);
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
                    return new zzgg(null);
                }
                return new zzgh();
            }
            return zzkc.l(zza, "\u0001\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0004\u0000\u0001\u0015\u0002\u0015\u0003\u001b\u0004\u001b", new Object[]{"zze", "zzf", "zzg", zzfq.class, "zzh", zzgj.class});
        }
        return (byte) 1;
    }

    public final int zza() {
        return this.zzg.size();
    }

    public final int zzb() {
        return this.zzf.size();
    }

    public final int zzc() {
        return this.zzh.size();
    }

    public final int zzd() {
        return this.zze.size();
    }

    public final zzfq zze(int i2) {
        return (zzfq) this.zzg.get(i2);
    }

    public final zzgj zzi(int i2) {
        return (zzgj) this.zzh.get(i2);
    }

    public final List zzj() {
        return this.zzg;
    }

    public final List zzk() {
        return this.zzf;
    }

    public final List zzm() {
        return this.zzh;
    }

    public final List zzn() {
        return this.zze;
    }
}

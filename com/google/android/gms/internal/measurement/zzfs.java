package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes.dex */
public final class zzfs extends zzkc implements zzlk {
    private static final zzfs zza;
    private int zze;
    private zzkj zzf = zzkc.i();
    private String zzg = "";
    private long zzh;
    private long zzi;
    private int zzj;

    static {
        zzfs zzfsVar = new zzfs();
        zza = zzfsVar;
        zzkc.m(zzfs.class, zzfsVar);
    }

    private zzfs() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void p(zzfs zzfsVar, int i2, zzfw zzfwVar) {
        zzfwVar.getClass();
        zzfsVar.zzv();
        zzfsVar.zzf.set(i2, zzfwVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void q(zzfs zzfsVar, zzfw zzfwVar) {
        zzfwVar.getClass();
        zzfsVar.zzv();
        zzfsVar.zzf.add(zzfwVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void r(zzfs zzfsVar, Iterable iterable) {
        zzfsVar.zzv();
        zzil.b(iterable, zzfsVar.zzf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void t(zzfs zzfsVar, int i2) {
        zzfsVar.zzv();
        zzfsVar.zzf.remove(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void u(zzfs zzfsVar, String str) {
        str.getClass();
        zzfsVar.zze |= 1;
        zzfsVar.zzg = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void v(zzfs zzfsVar, long j2) {
        zzfsVar.zze |= 2;
        zzfsVar.zzh = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void w(zzfs zzfsVar, long j2) {
        zzfsVar.zze |= 4;
        zzfsVar.zzi = j2;
    }

    public static zzfr zze() {
        return (zzfr) zza.d();
    }

    private final void zzv() {
        zzkj zzkjVar = this.zzf;
        if (zzkjVar.zzc()) {
            return;
        }
        this.zzf = zzkc.j(zzkjVar);
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
                    return new zzfr(null);
                }
                return new zzfs();
            }
            return zzkc.l(zza, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\u001b\u0002ဈ\u0000\u0003ဂ\u0001\u0004ဂ\u0002\u0005င\u0003", new Object[]{"zze", "zzf", zzfw.class, "zzg", "zzh", "zzi", "zzj"});
        }
        return (byte) 1;
    }

    public final int zza() {
        return this.zzj;
    }

    public final int zzb() {
        return this.zzf.size();
    }

    public final long zzc() {
        return this.zzi;
    }

    public final long zzd() {
        return this.zzh;
    }

    public final zzfw zzg(int i2) {
        return (zzfw) this.zzf.get(i2);
    }

    public final String zzh() {
        return this.zzg;
    }

    public final List zzi() {
        return this.zzf;
    }

    public final boolean zzs() {
        return (this.zze & 8) != 0;
    }

    public final boolean zzt() {
        return (this.zze & 4) != 0;
    }

    public final boolean zzu() {
        return (this.zze & 2) != 0;
    }
}

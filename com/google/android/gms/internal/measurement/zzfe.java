package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes.dex */
public final class zzfe extends zzkc implements zzlk {
    private static final zzfe zza;
    private int zze;
    private long zzf;
    private int zzh;
    private boolean zzm;
    private String zzg = "";
    private zzkj zzi = zzkc.i();
    private zzkj zzj = zzkc.i();
    private zzkj zzk = zzkc.i();
    private String zzl = "";
    private zzkj zzn = zzkc.i();
    private zzkj zzo = zzkc.i();
    private String zzp = "";

    static {
        zzfe zzfeVar = new zzfe();
        zza = zzfeVar;
        zzkc.m(zzfe.class, zzfeVar);
    }

    private zzfe() {
    }

    public static /* synthetic */ void p(zzfe zzfeVar, int i2, zzfc zzfcVar) {
        zzfcVar.getClass();
        zzkj zzkjVar = zzfeVar.zzj;
        if (!zzkjVar.zzc()) {
            zzfeVar.zzj = zzkc.j(zzkjVar);
        }
        zzfeVar.zzj.set(i2, zzfcVar);
    }

    public static zzfd zze() {
        return (zzfd) zza.d();
    }

    public static zzfe zzg() {
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
                    return new zzfd(null);
                }
                return new zzfe();
            }
            return zzkc.l(zza, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0005\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003င\u0002\u0004\u001b\u0005\u001b\u0006\u001b\u0007ဈ\u0003\bဇ\u0004\t\u001b\n\u001b\u000bဈ\u0005", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", zzfi.class, "zzj", zzfc.class, "zzk", zzeh.class, "zzl", "zzm", "zzn", zzgs.class, "zzo", zzfa.class, "zzp"});
        }
        return (byte) 1;
    }

    public final int zza() {
        return this.zzn.size();
    }

    public final int zzb() {
        return this.zzj.size();
    }

    public final long zzc() {
        return this.zzf;
    }

    public final zzfc zzd(int i2) {
        return (zzfc) this.zzj.get(i2);
    }

    public final String zzh() {
        return this.zzg;
    }

    public final String zzi() {
        return this.zzp;
    }

    public final List zzj() {
        return this.zzk;
    }

    public final List zzk() {
        return this.zzo;
    }

    public final List zzm() {
        return this.zzn;
    }

    public final List zzn() {
        return this.zzi;
    }

    public final boolean zzq() {
        return this.zzm;
    }

    public final boolean zzr() {
        return (this.zze & 2) != 0;
    }

    public final boolean zzs() {
        return (this.zze & 1) != 0;
    }
}

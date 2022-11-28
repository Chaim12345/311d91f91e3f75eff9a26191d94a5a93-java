package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzwt extends zzadk implements zzaes {
    private static final zzwt zzb;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private zzwh zzk;
    private zzwa zzl;
    private zzvv zzm;
    private zzaab zzn;
    private zzwc zzo;
    private zzwf zzp;
    private zzaad zzq;
    private zzaal zzr;
    private zzaah zzs;
    private int zzt;

    static {
        zzwt zzwtVar = new zzwt();
        zzb = zzwtVar;
        zzadk.zzG(zzwt.class, zzwtVar);
    }

    private zzwt() {
    }

    public static zzwo zza() {
        return (zzwo) zzb.zzx();
    }

    public static /* synthetic */ void zzd(zzwt zzwtVar, int i2) {
        zzwtVar.zze |= 4;
        zzwtVar.zzh = i2;
    }

    public static /* synthetic */ void zze(zzwt zzwtVar, zzwh zzwhVar) {
        zzwhVar.getClass();
        zzwtVar.zzk = zzwhVar;
        zzwtVar.zze |= 32;
    }

    public static /* synthetic */ void zzf(zzwt zzwtVar, zzvv zzvvVar) {
        zzvvVar.getClass();
        zzwtVar.zzm = zzvvVar;
        zzwtVar.zze |= 128;
    }

    public static /* synthetic */ void zzg(zzwt zzwtVar, zzwc zzwcVar) {
        zzwcVar.getClass();
        zzwtVar.zzo = zzwcVar;
        zzwtVar.zze |= 512;
    }

    public static /* synthetic */ void zzh(zzwt zzwtVar, int i2) {
        zzwtVar.zzf = i2 - 1;
        zzwtVar.zze |= 1;
    }

    public static /* synthetic */ void zzi(zzwt zzwtVar, int i2) {
        zzwtVar.zzg = i2 - 1;
        zzwtVar.zze |= 2;
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    public final Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzwo(null);
                }
                return new zzwt();
            }
            return zzadk.zzF(zzb, "\u0001\u000f\u0000\u0001\u0001\u000f\u000f\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003င\u0002\u0004ဌ\u0003\u0005ဌ\u0004\u0006ဉ\u0005\u0007ဉ\u0006\bဉ\u0007\tဉ\b\nဉ\t\u000bဉ\n\fဉ\u000b\rဉ\f\u000eဉ\r\u000fဌ\u000e", new Object[]{"zze", "zzf", zzwq.zza, "zzg", zzws.zza, "zzh", "zzi", zzwp.zza, "zzj", zzwn.zza, "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", zzwr.zza});
        }
        return (byte) 1;
    }
}

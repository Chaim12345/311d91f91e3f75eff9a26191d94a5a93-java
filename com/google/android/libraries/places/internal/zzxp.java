package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzxp extends zzadk implements zzaes {
    private static final zzxp zzb;
    private int zze;
    private int zzf;
    private int zzg = 1;
    private boolean zzh;
    private boolean zzi;
    private boolean zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private int zzn;
    private int zzo;
    private int zzp;
    private int zzq;
    private boolean zzr;
    private int zzs;
    private int zzt;
    private int zzu;

    static {
        zzxp zzxpVar = new zzxp();
        zzb = zzxpVar;
        zzadk.zzG(zzxp.class, zzxpVar);
    }

    private zzxp() {
    }

    public static zzxm zza() {
        return (zzxm) zzb.zzx();
    }

    public static /* synthetic */ void zzd(zzxp zzxpVar, boolean z) {
        zzxpVar.zze |= 4;
        zzxpVar.zzh = z;
    }

    public static /* synthetic */ void zze(zzxp zzxpVar, boolean z) {
        zzxpVar.zze |= 8;
        zzxpVar.zzi = z;
    }

    public static /* synthetic */ void zzf(zzxp zzxpVar, boolean z) {
        zzxpVar.zze |= 16;
        zzxpVar.zzj = z;
    }

    public static /* synthetic */ void zzg(zzxp zzxpVar, int i2) {
        zzxpVar.zze |= 32;
        zzxpVar.zzk = i2;
    }

    public static /* synthetic */ void zzh(zzxp zzxpVar, int i2) {
        zzxpVar.zze |= 64;
        zzxpVar.zzl = i2;
    }

    public static /* synthetic */ void zzi(zzxp zzxpVar, int i2) {
        zzxpVar.zze |= 128;
        zzxpVar.zzm = i2;
    }

    public static /* synthetic */ void zzj(zzxp zzxpVar, int i2) {
        zzxpVar.zze |= 256;
        zzxpVar.zzn = i2;
    }

    public static /* synthetic */ void zzk(zzxp zzxpVar, int i2) {
        zzxpVar.zze |= 512;
        zzxpVar.zzo = i2;
    }

    public static /* synthetic */ void zzl(zzxp zzxpVar, int i2) {
        zzxpVar.zze |= 1024;
        zzxpVar.zzp = i2;
    }

    public static /* synthetic */ void zzm(zzxp zzxpVar, int i2) {
        zzxpVar.zze |= 2048;
        zzxpVar.zzq = i2;
    }

    public static /* synthetic */ void zzn(zzxp zzxpVar, boolean z) {
        zzxpVar.zze |= 4096;
        zzxpVar.zzr = z;
    }

    public static /* synthetic */ void zzo(zzxp zzxpVar, int i2) {
        zzxpVar.zze |= 8192;
        zzxpVar.zzs = i2;
    }

    public static /* synthetic */ void zzp(zzxp zzxpVar, int i2) {
        zzxpVar.zzf = i2 - 1;
        zzxpVar.zze |= 1;
    }

    public static /* synthetic */ void zzq(zzxp zzxpVar, int i2) {
        zzxpVar.zzg = i2;
        zzxpVar.zze |= 2;
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
                    return new zzxm(null);
                }
                return new zzxp();
            }
            return zzadk.zzF(zzb, "\u0001\u0010\u0000\u0001\u0001\u0011\u0010\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003ဇ\u0002\u0004ဇ\u0003\u0005ဇ\u0004\u0006ဋ\u0005\u0007ဋ\u0006\bဋ\u0007\nဋ\t\u000bဋ\n\fဋ\u000b\rဇ\f\u000eဋ\r\u000fဋ\b\u0010ဋ\u000e\u0011ဌ\u000f", new Object[]{"zze", "zzf", zzxo.zza, "zzg", zzxl.zza, "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzo", "zzp", "zzq", "zzr", "zzs", "zzn", "zzt", "zzu", zzxn.zza});
        }
        return (byte) 1;
    }
}

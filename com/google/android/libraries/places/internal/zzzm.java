package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzzm extends zzadk implements zzaes {
    private static final zzzm zzb;
    private int zze;
    private int zzf;
    private zzwy zzi;
    private zzzv zzj;
    private zzyj zzk;
    private zzxi zzl;
    private zzyh zzm;
    private zzxk zzn;
    private zzyf zzo;
    private zzzx zzp;
    private zzzx zzq;
    private zzyl zzr;
    private zzxu zzs;
    private zzzo zzt;
    private zzzq zzu;
    private zzzb zzv;
    private zzyr zzw;
    private zzzs zzx;
    private byte zzy = 2;
    private String zzg = "";
    private String zzh = "";

    static {
        zzzm zzzmVar = new zzzm();
        zzb = zzzmVar;
        zzadk.zzG(zzzm.class, zzzmVar);
    }

    private zzzm() {
    }

    public static zzzk zza() {
        return (zzzk) zzb.zzx();
    }

    public static /* synthetic */ void zzd(zzzm zzzmVar, String str) {
        str.getClass();
        zzzmVar.zze |= 2;
        zzzmVar.zzg = str;
    }

    public static /* synthetic */ void zze(zzzm zzzmVar, String str) {
        str.getClass();
        zzzmVar.zze |= 4;
        zzzmVar.zzh = str;
    }

    public static /* synthetic */ void zzf(zzzm zzzmVar, zzyh zzyhVar) {
        zzyhVar.getClass();
        zzzmVar.zzm = zzyhVar;
        zzzmVar.zze |= 128;
    }

    public static /* synthetic */ void zzg(zzzm zzzmVar, zzxk zzxkVar) {
        zzxkVar.getClass();
        zzzmVar.zzn = zzxkVar;
        zzzmVar.zze |= 256;
    }

    public static /* synthetic */ void zzh(zzzm zzzmVar, int i2) {
        zzzmVar.zzf = i2 - 1;
        zzzmVar.zze |= 1;
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    public final Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            this.zzy = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zzb;
                    }
                    return new zzzk(null);
                }
                return new zzzm();
            }
            return zzadk.zzF(zzb, "\u0001\u0013\u0000\u0001\u0001\u0013\u0013\u0000\u0000\u0004\u0001???\u0000\u0002???\u0001\u0003???\u0002\u0004???\u0003\u0005???\u0004\u0006???\u0005\u0007???\u0006\b???\u0007\t???\b\n???\t\u000b???\u000b\f???\n\r???\f\u000e???\r\u000f???\u000e\u0010???\u000f\u0011???\u0010\u0012???\u0011\u0013???\u0012", new Object[]{"zze", "zzf", zzzl.zza, "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzq", "zzp", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx"});
        }
        return Byte.valueOf(this.zzy);
    }
}

package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzlg extends zzadk implements zzaes {
    private static final zzlg zzb;
    private int zze;
    private zzya zzg;
    private zzni zzh;
    private zzaav zzi;
    private zzux zzj;
    private zzqq zzk;
    private zzob zzl;
    private zznc zzm;
    private zzmi zzn;
    private zznv zzo;
    private zzsh zzp;
    private zzsv zzq;
    private zzsy zzr;
    private zzls zzs;
    private zzox zzt;
    private zzc zzu;
    private zzaj zzv;
    private zzar zzw;
    private byte zzx = 2;
    private int zzf = 1;

    static {
        zzlg zzlgVar = new zzlg();
        zzb = zzlgVar;
        zzadk.zzG(zzlg.class, zzlgVar);
    }

    private zzlg() {
    }

    public static zzle zza() {
        return (zzle) zzb.zzx();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzd(zzlg zzlgVar, zzya zzyaVar) {
        zzyaVar.getClass();
        zzlgVar.zzg = zzyaVar;
        zzlgVar.zze |= 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zze(zzlg zzlgVar, int i2) {
        zzlgVar.zzf = 1;
        zzlgVar.zze = 1 | zzlgVar.zze;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.places.internal.zzadk
    public final Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            this.zzx = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zzb;
                    }
                    return new zzle(null);
                }
                return new zzlg();
            }
            return zzadk.zzF(zzb, "\u0001\u0012\u0000\u0001\u0001\u0013\u0012\u0000\u0000\u0002\u0001???\u0000\u0002???\u0001\u0003???\u0002\u0004???\u0003\u0005???\u0004\u0006???\u0005\u0007???\u0006\b???\u0007\t???\b\n???\t\u000b???\n\f???\u000b\r???\f\u000f???\r\u0010???\u000e\u0011???\u000f\u0012???\u0010\u0013???\u0011", new Object[]{"zze", "zzf", zzlf.zza, "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw"});
        }
        return Byte.valueOf(this.zzx);
    }
}

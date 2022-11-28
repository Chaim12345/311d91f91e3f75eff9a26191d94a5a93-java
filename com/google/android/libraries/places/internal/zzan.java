package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzan extends zzadk implements zzaes {
    private static final zzan zzb;
    private int zze;
    private boolean zzf;
    private long zzg;
    private long zzh;
    private int zzi;
    private float zzj;
    private float zzk;
    private boolean zzl;
    private float zzm;
    private double zzn;
    private int zzo;

    static {
        zzan zzanVar = new zzan();
        zzb = zzanVar;
        zzadk.zzG(zzan.class, zzanVar);
    }

    private zzan() {
    }

    public static /* synthetic */ zzan zza() {
        return zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    public final Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 == 2) {
                zzadn zzadnVar = zzam.zza;
                return zzadk.zzF(zzb, "\u0001\n\u0000\u0001\u0001\n\n\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဌ\u0003\u0005ခ\u0004\u0006ခ\u0005\u0007ဇ\u0006\bခ\u0007\tက\b\nဌ\t", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", zzadnVar, "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", zzadnVar});
            } else if (i3 != 3) {
                if (i3 != 4) {
                    if (i3 != 5) {
                        return null;
                    }
                    return zzb;
                }
                return new zzal(null);
            } else {
                return new zzan();
            }
        }
        return (byte) 1;
    }
}

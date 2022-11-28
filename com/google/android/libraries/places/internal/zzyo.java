package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzyo extends zzadk implements zzaes {
    private static final zzyo zzb;
    private int zze;
    private zzwy zzf;
    private int zzg;
    private int zzh;
    private zzzg zzi;

    static {
        zzyo zzyoVar = new zzyo();
        zzb = zzyoVar;
        zzadk.zzG(zzyo.class, zzyoVar);
    }

    private zzyo() {
    }

    public static zzym zza() {
        return (zzym) zzb.zzx();
    }

    public static /* synthetic */ void zzd(zzyo zzyoVar, int i2) {
        zzyoVar.zze |= 4;
        zzyoVar.zzh = i2;
    }

    public static /* synthetic */ void zze(zzyo zzyoVar, zzzg zzzgVar) {
        zzzgVar.getClass();
        zzyoVar.zzi = zzzgVar;
        zzyoVar.zze |= 8;
    }

    public static /* synthetic */ void zzf(zzyo zzyoVar, int i2) {
        zzyoVar.zzg = i2 - 1;
        zzyoVar.zze |= 2;
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
                    return new zzym(null);
                }
                return new zzyo();
            }
            return zzadk.zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဌ\u0001\u0003င\u0002\u0004ဉ\u0003", new Object[]{"zze", "zzf", "zzg", zzyn.zza, "zzh", "zzi"});
        }
        return (byte) 1;
    }
}

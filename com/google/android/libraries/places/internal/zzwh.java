package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzwh extends zzadk implements zzaes {
    private static final zzwh zzb;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzwh zzwhVar = new zzwh();
        zzb = zzwhVar;
        zzadk.zzG(zzwh.class, zzwhVar);
    }

    private zzwh() {
    }

    public static zzwg zza() {
        return (zzwg) zzb.zzx();
    }

    public static /* synthetic */ void zzd(zzwh zzwhVar, int i2) {
        zzwhVar.zze |= 1;
        zzwhVar.zzf = 1;
    }

    public static /* synthetic */ void zze(zzwh zzwhVar, int i2) {
        zzwhVar.zze |= 2;
        zzwhVar.zzg = i2;
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
                    return new zzwg(null);
                }
                return new zzwh();
            }
            return zzadk.zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }
}

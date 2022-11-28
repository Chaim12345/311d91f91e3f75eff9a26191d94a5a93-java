package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzln extends zzadk implements zzaes {
    private static final zzln zzb;
    private int zze;
    private int zzg;
    private int zzh;
    private int zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private int zzn;
    private String zzf = "";
    private String zzi = "";

    static {
        zzln zzlnVar = new zzln();
        zzb = zzlnVar;
        zzadk.zzG(zzln.class, zzlnVar);
    }

    private zzln() {
    }

    public static zzli zza() {
        return (zzli) zzb.zzx();
    }

    public static /* synthetic */ void zzd(zzln zzlnVar, String str) {
        zzlnVar.zze |= 1;
        zzlnVar.zzf = str;
    }

    public static /* synthetic */ void zze(zzln zzlnVar, int i2) {
        zzlnVar.zze |= 2;
        zzlnVar.zzg = i2;
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
                    return new zzli(null);
                }
                return new zzln();
            }
            return zzadk.zzF(zzb, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဈ\u0000\u0002င\u0001\u0003င\u0002\u0004ဈ\u0003\u0005င\u0004\u0006ဌ\u0005\u0007ဌ\u0006\bဌ\u0007\tဌ\b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzlj.zza, "zzl", zzll.zza, "zzm", zzlk.zza, "zzn", zzlm.zza});
        }
        return (byte) 1;
    }
}

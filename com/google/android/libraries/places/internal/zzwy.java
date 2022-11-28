package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzwy extends zzadk implements zzaes {
    private static final zzwy zzb;
    private int zze;
    private int zzf;
    private boolean zzj;
    private zzadr zzg = zzadk.zzB();
    private String zzh = "";
    private String zzi = "";
    private zzadr zzk = zzadk.zzB();

    static {
        zzwy zzwyVar = new zzwy();
        zzb = zzwyVar;
        zzadk.zzG(zzwy.class, zzwyVar);
    }

    private zzwy() {
    }

    public static zzwx zza() {
        return (zzwx) zzb.zzx();
    }

    public static /* synthetic */ void zzd(zzwy zzwyVar, String str) {
        zzadr zzadrVar = zzwyVar.zzg;
        if (!zzadrVar.zzc()) {
            zzwyVar.zzg = zzadk.zzC(zzadrVar);
        }
        zzwyVar.zzg.add(str);
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
                    return new zzwx(null);
                }
                return new zzwy();
            }
            return zzadk.zzF(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0002\u0000\u0001င\u0000\u0002\u001a\u0003ဈ\u0001\u0004ဈ\u0002\u0005ဇ\u0003\u0006\u001a", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        }
        return (byte) 1;
    }
}

package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzzg extends zzadk implements zzaes {
    private static final zzzg zzb;
    private zzadr zze = zzadk.zzB();

    static {
        zzzg zzzgVar = new zzzg();
        zzb = zzzgVar;
        zzadk.zzG(zzzg.class, zzzgVar);
    }

    private zzzg() {
    }

    public static zzzf zza() {
        return (zzzf) zzb.zzx();
    }

    public static /* synthetic */ void zzd(zzzg zzzgVar, Iterable iterable) {
        zzadr zzadrVar = zzzgVar.zze;
        if (!zzadrVar.zzc()) {
            zzzgVar.zze = zzadk.zzC(zzadrVar);
        }
        zzacc.zzt(iterable, zzzgVar.zze);
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
                    return new zzzf(null);
                }
                return new zzzg();
            }
            return zzadk.zzF(zzb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"zze"});
        }
        return (byte) 1;
    }
}

package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzvv extends zzadk implements zzaes {
    private static final zzvv zzb;
    private int zze;
    private int zzf;

    static {
        zzvv zzvvVar = new zzvv();
        zzb = zzvvVar;
        zzadk.zzG(zzvv.class, zzvvVar);
    }

    private zzvv() {
    }

    public static zzvu zza() {
        return (zzvu) zzb.zzx();
    }

    public static /* synthetic */ void zzd(zzvv zzvvVar, int i2) {
        zzvvVar.zze |= 1;
        zzvvVar.zzf = i2;
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
                    return new zzvu(null);
                }
                return new zzvv();
            }
            return zzadk.zzF(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"zze", "zzf"});
        }
        return (byte) 1;
    }
}

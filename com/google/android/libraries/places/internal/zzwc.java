package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzwc extends zzadk implements zzaes {
    private static final zzwc zzb;
    private int zze;
    private int zzf;

    static {
        zzwc zzwcVar = new zzwc();
        zzb = zzwcVar;
        zzadk.zzG(zzwc.class, zzwcVar);
    }

    private zzwc() {
    }

    public static zzwb zza() {
        return (zzwb) zzb.zzx();
    }

    public static /* synthetic */ void zzd(zzwc zzwcVar, int i2) {
        zzwcVar.zze |= 1;
        zzwcVar.zzf = i2;
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
                    return new zzwb(null);
                }
                return new zzwc();
            }
            return zzadk.zzF(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"zze", "zzf"});
        }
        return (byte) 1;
    }
}

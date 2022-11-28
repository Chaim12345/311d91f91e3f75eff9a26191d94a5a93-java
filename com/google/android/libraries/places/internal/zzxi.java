package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
public final class zzxi extends zzadk implements zzaes {
    private static final zzxi zzb;
    private int zze;
    private zzop zzg;
    private byte zzl = 2;
    private String zzf = "";
    private String zzh = "";
    private zzadr zzi = zzadk.zzB();
    private String zzj = "";
    private String zzk = "";

    static {
        zzxi zzxiVar = new zzxi();
        zzb = zzxiVar;
        zzadk.zzG(zzxi.class, zzxiVar);
    }

    private zzxi() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    public final Object zzb(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            this.zzl = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zzb;
                    }
                    return new zzxh(null);
                }
                return new zzxi();
            }
            return zzadk.zzF(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0001\u0001ဈ\u0000\u0002ᐉ\u0001\u0003ဈ\u0002\u0004\u001a\u0005ဈ\u0003\u0006ဈ\u0004", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        }
        return Byte.valueOf(this.zzl);
    }
}

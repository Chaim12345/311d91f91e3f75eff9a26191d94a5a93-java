package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes2.dex */
public final class zzjh extends zzdy {
    private static final zzjh zze;
    private byte zzf = 2;

    static {
        zzjh zzjhVar = new zzjh();
        zze = zzjhVar;
        zzec.n(zzjh.class, zzjhVar);
    }

    private zzjh() {
    }

    public static zzjh zzf() {
        return zze;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    public final Object p(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            this.zzf = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zze;
                    }
                    return new zzjg(null);
                }
                return new zzjh();
            }
            return zzec.m(zze, "\u0003\u0000", null);
        }
        return Byte.valueOf(this.zzf);
    }
}

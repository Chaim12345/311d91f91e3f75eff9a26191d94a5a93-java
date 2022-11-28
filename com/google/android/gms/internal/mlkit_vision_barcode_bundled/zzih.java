package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes2.dex */
public final class zzih extends zzec implements zzfm {
    private static final zzih zza;
    private int zze;
    private boolean zzf;
    private int zzg;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private boolean zzh = true;
    private String zzm = "";
    private String zzn = "";

    static {
        zzih zzihVar = new zzih();
        zza = zzihVar;
        zzec.n(zzih.class, zzihVar);
    }

    private zzih() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    public final Object p(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 == 2) {
                zzeg zzegVar = zzii.f6453a;
                return zzec.m(zza, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဌ\u0001\u0003ဇ\u0002\u0004ဌ\u0003\u0005ဌ\u0004\u0006ဌ\u0005\u0007ဌ\u0006\bဈ\u0007\tဈ\b", new Object[]{"zze", "zzf", "zzg", zzij.f6454a, "zzh", "zzi", zzif.f6452a, "zzj", zzegVar, "zzk", zzegVar, "zzl", zzegVar, "zzm", "zzn"});
            } else if (i3 != 3) {
                if (i3 != 4) {
                    if (i3 != 5) {
                        return null;
                    }
                    return zza;
                }
                return new zzig(null);
            } else {
                return new zzih();
            }
        }
        return (byte) 1;
    }
}

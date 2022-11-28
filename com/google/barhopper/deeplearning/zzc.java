package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;
/* loaded from: classes2.dex */
public final class zzc extends zzec implements zzfm {
    private static final zzc zza;
    private int zze;
    private zzeh zzf = zzec.g();
    private zzeh zzg = zzec.g();
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;

    static {
        zzc zzcVar = new zzc();
        zza = zzcVar;
        zzec.n(zzc.class, zzcVar);
    }

    private zzc() {
    }

    public static /* synthetic */ void r(zzc zzcVar, int i2) {
        zzcVar.zze |= 2;
        zzcVar.zzi = i2;
    }

    public static /* synthetic */ void s(zzc zzcVar, float f2) {
        zzeh zzehVar = zzcVar.zzf;
        if (!zzehVar.zzc()) {
            zzcVar.zzf = zzec.h(zzehVar);
        }
        zzcVar.zzf.zzg(f2);
    }

    public static /* synthetic */ void t(zzc zzcVar, float f2) {
        zzeh zzehVar = zzcVar.zzg;
        if (!zzehVar.zzc()) {
            zzcVar.zzg = zzec.h(zzehVar);
        }
        zzcVar.zzg.zzg(f2);
    }

    public static /* synthetic */ void u(zzc zzcVar, int i2) {
        zzcVar.zze |= 1;
        zzcVar.zzh = i2;
    }

    public static zzb zza() {
        return (zzb) zza.c();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec
    public final Object p(int i2, Object obj, Object obj2) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 2) {
                if (i3 != 3) {
                    if (i3 != 4) {
                        if (i3 != 5) {
                            return null;
                        }
                        return zza;
                    }
                    return new zzb(null);
                }
                return new zzc();
            }
            return zzec.m(zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0002\u0000\u0001\u0013\u0002\u0013\u0003ဋ\u0000\u0004ဋ\u0001\u0005ဋ\u0002\u0006ဋ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        }
        return (byte) 1;
    }
}

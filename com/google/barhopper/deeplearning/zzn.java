package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;
/* loaded from: classes2.dex */
public final class zzn extends zzec implements zzfm {
    private static final zzn zza;
    private int zze;
    private String zzf = "";
    private zzdb zzg;
    private String zzh;
    private zzdb zzi;
    private float zzj;
    private float zzk;
    private float zzl;
    private float zzm;
    private int zzn;

    static {
        zzn zznVar = new zzn();
        zza = zznVar;
        zzec.n(zzn.class, zznVar);
    }

    private zzn() {
        zzdb zzdbVar = zzdb.zzb;
        this.zzg = zzdbVar;
        this.zzh = "";
        this.zzi = zzdbVar;
        this.zzj = 0.25f;
        this.zzk = 0.25f;
        this.zzl = 0.5f;
        this.zzm = 0.85f;
        this.zzn = 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void r(zzn zznVar, zzdb zzdbVar) {
        zzdbVar.getClass();
        zznVar.zze |= 2;
        zznVar.zzg = zzdbVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void s(zzn zznVar, zzdb zzdbVar) {
        zzdbVar.getClass();
        zznVar.zze |= 8;
        zznVar.zzi = zzdbVar;
    }

    public static zzm zza() {
        return (zzm) zza.c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
                    return new zzm(null);
                }
                return new zzn();
            }
            return zzec.m(zza, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဈ\u0000\u0002ည\u0001\u0003ဈ\u0002\u0004ည\u0003\u0005ခ\u0004\u0006ခ\u0005\u0007ခ\u0006\bခ\u0007\tင\b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn"});
        }
        return (byte) 1;
    }
}

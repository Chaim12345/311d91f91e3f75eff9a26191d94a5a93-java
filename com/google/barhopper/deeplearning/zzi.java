package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhj;
/* loaded from: classes2.dex */
public final class zzi extends zzec implements zzfm {
    private static final zzi zza;
    private int zze;
    private zzf zzk;
    private zzhj zzn;
    private String zzf = "";
    private zzdb zzg = zzdb.zzb;
    private int zzh = 10;
    private float zzi = 0.5f;
    private float zzj = 0.05f;
    private zzeh zzl = zzec.g();
    private int zzm = 1;
    private int zzo = 320;
    private int zzp = 4;
    private int zzq = 2;

    static {
        zzi zziVar = new zzi();
        zza = zziVar;
        zzec.n(zzi.class, zziVar);
    }

    private zzi() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void r(zzi zziVar, zzf zzfVar) {
        zzfVar.getClass();
        zziVar.zzk = zzfVar;
        zziVar.zze |= 32;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void s(zzi zziVar, zzdb zzdbVar) {
        zzdbVar.getClass();
        zziVar.zze |= 2;
        zziVar.zzg = zzdbVar;
    }

    public static zzh zza() {
        return (zzh) zza.c();
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
                    return new zzh(null);
                }
                return new zzi();
            }
            return zzec.m(zza, "\u0001\f\u0000\u0001\u0001\f\f\u0000\u0001\u0000\u0001ဈ\u0000\u0002ည\u0001\u0003ဋ\u0002\u0004ခ\u0003\u0005ခ\u0004\u0006ဉ\u0005\u0007\u0013\bင\u0006\tဉ\u0007\nင\b\u000bင\t\fင\n", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq"});
        }
        return (byte) 1;
    }
}

package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes2.dex */
public abstract class zzdi extends zzcr {
    private static final Logger zzb = Logger.getLogger(zzdi.class.getName());
    private static final boolean zzc = zzgz.x();

    /* renamed from: a  reason: collision with root package name */
    zzdj f6413a;

    private zzdi() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdi(zzdf zzdfVar) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(zzfl zzflVar, zzgb zzgbVar) {
        zzck zzckVar = (zzck) zzflVar;
        int a2 = zzckVar.a();
        if (a2 == -1) {
            a2 = zzgbVar.zza(zzckVar);
            zzckVar.b(a2);
        }
        return zzD(a2) + a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static int d(int i2, zzfl zzflVar, zzgb zzgbVar) {
        int zzD = zzD(i2 << 3);
        int i3 = zzD + zzD;
        zzck zzckVar = (zzck) zzflVar;
        int a2 = zzckVar.a();
        if (a2 == -1) {
            a2 = zzgbVar.zza(zzckVar);
            zzckVar.b(a2);
        }
        return i3 + a2;
    }

    public static int zzB(String str) {
        int length;
        try {
            length = zzhe.e(str);
        } catch (zzhd unused) {
            length = str.getBytes(zzel.f6425a).length;
        }
        return zzD(length) + length;
    }

    public static int zzC(int i2) {
        return zzD(i2 << 3);
    }

    public static int zzD(int i2) {
        if ((i2 & (-128)) == 0) {
            return 1;
        }
        if ((i2 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i2) == 0) {
            return 3;
        }
        return (i2 & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzE(long j2) {
        int i2;
        if (((-128) & j2) == 0) {
            return 1;
        }
        if (j2 < 0) {
            return 10;
        }
        if (((-34359738368L) & j2) != 0) {
            j2 >>>= 28;
            i2 = 6;
        } else {
            i2 = 2;
        }
        if (((-2097152) & j2) != 0) {
            i2 += 2;
            j2 >>>= 14;
        }
        return (j2 & (-16384)) != 0 ? i2 + 1 : i2;
    }

    public static zzdi zzF(byte[] bArr) {
        return new zzdg(bArr, 0, bArr.length);
    }

    public static int zzt(byte[] bArr) {
        int length = bArr.length;
        return zzD(length) + length;
    }

    public static int zzu(zzdb zzdbVar) {
        int zzd = zzdbVar.zzd();
        return zzD(zzd) + zzd;
    }

    @Deprecated
    public static int zzw(zzfl zzflVar) {
        return zzflVar.zzE();
    }

    public static int zzx(int i2) {
        if (i2 >= 0) {
            return zzD(i2);
        }
        return 10;
    }

    public static int zzy(zzet zzetVar) {
        int zza = zzetVar.zza();
        return zzD(zza) + zza;
    }

    public static int zzz(zzfl zzflVar) {
        int zzE = zzflVar.zzE();
        return zzD(zzE) + zzE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(String str, zzhd zzhdVar) {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzhdVar);
        byte[] bytes = str.getBytes(zzel.f6425a);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (zzdh e2) {
            throw e2;
        } catch (IndexOutOfBoundsException e3) {
            throw new zzdh(e3);
        }
    }

    public final void zzG() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b2);

    public abstract void zzd(int i2, boolean z);

    public abstract void zze(int i2, zzdb zzdbVar);

    public abstract void zzf(int i2, int i3);

    public abstract void zzg(int i2);

    public abstract void zzh(int i2, long j2);

    public abstract void zzi(long j2);

    public abstract void zzj(int i2, int i3);

    public abstract void zzk(int i2);

    public abstract void zzl(byte[] bArr, int i2, int i3);

    public abstract void zzm(int i2, String str);

    public abstract void zzo(int i2, int i3);

    public abstract void zzp(int i2, int i3);

    public abstract void zzq(int i2);

    public abstract void zzr(int i2, long j2);

    public abstract void zzs(long j2);
}

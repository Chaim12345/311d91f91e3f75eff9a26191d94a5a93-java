package com.google.android.gms.internal.measurement;

import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes.dex */
public abstract class zzjj extends zzir {
    private static final Logger zzb = Logger.getLogger(zzjj.class.getName());
    private static final boolean zzc = zzmv.x();

    /* renamed from: a  reason: collision with root package name */
    zzjk f6095a;

    private zzjj() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzjj(zzji zzjiVar) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static int c(int i2, zzlj zzljVar, zzlu zzluVar) {
        int zzA = zzA(i2 << 3);
        int i3 = zzA + zzA;
        zzil zzilVar = (zzil) zzljVar;
        int a2 = zzilVar.a();
        if (a2 == -1) {
            a2 = zzluVar.zza(zzilVar);
            zzilVar.c(a2);
        }
        return i3 + a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int d(zzlj zzljVar, zzlu zzluVar) {
        zzil zzilVar = (zzil) zzljVar;
        int a2 = zzilVar.a();
        if (a2 == -1) {
            a2 = zzluVar.zza(zzilVar);
            zzilVar.c(a2);
        }
        return zzA(a2) + a2;
    }

    public static int zzA(int i2) {
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

    public static int zzB(long j2) {
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

    public static zzjj zzC(byte[] bArr) {
        return new zzjg(bArr, 0, bArr.length);
    }

    public static int zzt(zzjb zzjbVar) {
        int zzd = zzjbVar.zzd();
        return zzA(zzd) + zzd;
    }

    public static int zzv(int i2) {
        if (i2 >= 0) {
            return zzA(i2);
        }
        return 10;
    }

    public static int zzw(zzkp zzkpVar) {
        int zza = zzkpVar.zza();
        return zzA(zza) + zza;
    }

    public static int zzy(String str) {
        int length;
        try {
            length = zzna.c(str);
        } catch (zzmz unused) {
            length = str.getBytes(zzkk.f6100a).length;
        }
        return zzA(length) + length;
    }

    public static int zzz(int i2) {
        return zzA(i2 << 3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(String str, zzmz zzmzVar) {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzmzVar);
        byte[] bytes = str.getBytes(zzkk.f6100a);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzjh(e2);
        }
    }

    public final void zzD() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b2);

    public abstract void zzd(int i2, boolean z);

    public abstract void zze(int i2, zzjb zzjbVar);

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

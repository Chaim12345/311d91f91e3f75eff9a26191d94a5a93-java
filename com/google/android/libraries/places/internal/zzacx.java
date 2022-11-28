package com.google.android.libraries.places.internal;

import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes2.dex */
public abstract class zzacx extends zzacf {
    private static final Logger zzb = Logger.getLogger(zzacx.class.getName());
    private static final boolean zzc = zzagd.zzx();
    zzacy zza;

    private zzacx() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzacx(zzacw zzacwVar) {
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

    public static zzacx zzC(byte[] bArr) {
        return new zzacu(bArr, 0, bArr.length);
    }

    public static int zzt(zzacp zzacpVar) {
        int zzd = zzacpVar.zzd();
        return zzA(zzd) + zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static int zzu(int i2, zzaer zzaerVar, zzafc zzafcVar) {
        int zzA = zzA(i2 << 3);
        int i3 = zzA + zzA;
        zzacc zzaccVar = (zzacc) zzaerVar;
        int zzr = zzaccVar.zzr();
        if (zzr == -1) {
            zzr = zzafcVar.zza(zzaccVar);
            zzaccVar.zzu(zzr);
        }
        return i3 + zzr;
    }

    public static int zzv(int i2) {
        if (i2 >= 0) {
            return zzA(i2);
        }
        return 10;
    }

    public static int zzw(zzadx zzadxVar) {
        int zza = zzadxVar.zza();
        return zzA(zza) + zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzx(zzaer zzaerVar, zzafc zzafcVar) {
        zzacc zzaccVar = (zzacc) zzaerVar;
        int zzr = zzaccVar.zzr();
        if (zzr == -1) {
            zzr = zzafcVar.zza(zzaccVar);
            zzaccVar.zzu(zzr);
        }
        return zzA(zzr) + zzr;
    }

    public static int zzy(String str) {
        int length;
        try {
            length = zzagh.zzc(str);
        } catch (zzagg unused) {
            length = str.getBytes(zzads.zzb).length;
        }
        return zzA(length) + length;
    }

    public static int zzz(int i2) {
        return zzA(i2 << 3);
    }

    public final void zzD() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzE(String str, zzagg zzaggVar) {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzaggVar);
        byte[] bytes = str.getBytes(zzads.zzb);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (zzacv e2) {
            throw e2;
        } catch (IndexOutOfBoundsException e3) {
            throw new zzacv(e3);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b2);

    public abstract void zzd(int i2, boolean z);

    public abstract void zze(int i2, zzacp zzacpVar);

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

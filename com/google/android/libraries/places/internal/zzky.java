package com.google.android.libraries.places.internal;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzky {
    final int zza;
    final int zzb;
    final int zzc;
    final int zzd;
    private final String zze;
    private final char[] zzf;
    private final byte[] zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzky(String str, char[] cArr) {
        this.zze = str;
        Objects.requireNonNull(cArr);
        this.zzf = cArr;
        try {
            int length = cArr.length;
            int zzb = zzaax.zzb(length, RoundingMode.UNNECESSARY);
            this.zzb = zzb;
            int min = Math.min(8, Integer.lowestOneBit(zzb));
            try {
                this.zzc = 8 / min;
                this.zzd = zzb / min;
                this.zza = length - 1;
                byte[] bArr = new byte[128];
                Arrays.fill(bArr, (byte) -1);
                int i2 = 0;
                while (true) {
                    boolean z = true;
                    if (i2 >= cArr.length) {
                        break;
                    }
                    char c2 = cArr[i2];
                    zzha.zzf(c2 < 128, "Non-ASCII character: %s", c2);
                    if (bArr[c2] != -1) {
                        z = false;
                    }
                    zzha.zzf(z, "Duplicate character: %s", c2);
                    bArr[c2] = (byte) i2;
                    i2++;
                }
                this.zzg = bArr;
                boolean[] zArr = new boolean[this.zzc];
                for (int i3 = 0; i3 < this.zzd; i3++) {
                    zArr[zzaax.zza(i3 * 8, this.zzb, RoundingMode.CEILING)] = true;
                }
            } catch (ArithmeticException e2) {
                String str2 = new String(cArr);
                throw new IllegalArgumentException(str2.length() != 0 ? "Illegal alphabet ".concat(str2) : new String("Illegal alphabet "), e2);
            }
        } catch (ArithmeticException e3) {
            int length2 = cArr.length;
            StringBuilder sb = new StringBuilder(35);
            sb.append("Illegal alphabet length ");
            sb.append(length2);
            throw new IllegalArgumentException(sb.toString(), e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ char[] zzc(zzky zzkyVar) {
        return zzkyVar.zzf;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzky) {
            return Arrays.equals(this.zzf, ((zzky) obj).zzf);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zzf);
    }

    public final String toString() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final char zza(int i2) {
        return this.zzf[i2];
    }

    public final boolean zzb(char c2) {
        return c2 < 128 && this.zzg[c2] != -1;
    }
}

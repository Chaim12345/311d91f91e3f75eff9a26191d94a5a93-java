package com.google.android.libraries.places.internal;

import java.math.RoundingMode;
import java.util.Arrays;
import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class zzlb extends zzlc {
    final zzky zzb;
    @CheckForNull
    final Character zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzlb(zzky zzkyVar, @CheckForNull Character ch) {
        this.zzb = zzkyVar;
        if (!(ch == null || !zzkyVar.zzb(ch.charValue()))) {
            throw new IllegalArgumentException(zzhf.zza("Padding character %s was already in alphabet", ch));
        }
        this.zzc = ch;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzlb(String str, String str2, @CheckForNull Character ch) {
        this(new zzky(str, str2.toCharArray()), ch);
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzlb) {
            zzlb zzlbVar = (zzlb) obj;
            if (this.zzb.equals(zzlbVar.zzb) && zzgw.zza(this.zzc, zzlbVar.zzc)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.zzb.hashCode() ^ Arrays.hashCode(new Object[]{this.zzc});
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("BaseEncoding.");
        sb.append(this.zzb.toString());
        if (8 % this.zzb.zzb != 0) {
            if (this.zzc == null) {
                str = ".omitPadding()";
            } else {
                sb.append(".withPadChar('");
                sb.append(this.zzc);
                str = "')";
            }
            sb.append(str);
        }
        return sb.toString();
    }

    @Override // com.google.android.libraries.places.internal.zzlc
    void zza(Appendable appendable, byte[] bArr, int i2, int i3) {
        int i4 = 0;
        zzha.zzg(0, i3, bArr.length);
        while (i4 < i3) {
            zzc(appendable, bArr, i4, Math.min(this.zzb.zzd, i3 - i4));
            i4 += this.zzb.zzd;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzlc
    final int zzb(int i2) {
        zzky zzkyVar = this.zzb;
        return zzkyVar.zzc * zzaax.zza(i2, zzkyVar.zzd, RoundingMode.CEILING);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc(Appendable appendable, byte[] bArr, int i2, int i3) {
        zzha.zzg(i2, i2 + i3, bArr.length);
        int i4 = 0;
        zzha.zzd(i3 <= this.zzb.zzd);
        long j2 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            j2 = (j2 | (bArr[i2 + i5] & 255)) << 8;
        }
        int i6 = ((i3 + 1) * 8) - this.zzb.zzb;
        while (i4 < i3 * 8) {
            zzky zzkyVar = this.zzb;
            appendable.append(zzkyVar.zza(((int) (j2 >>> (i6 - i4))) & zzkyVar.zza));
            i4 += this.zzb.zzb;
        }
        if (this.zzc != null) {
            while (i4 < this.zzb.zzd * 8) {
                appendable.append(this.zzc.charValue());
                i4 += this.zzb.zzb;
            }
        }
    }
}

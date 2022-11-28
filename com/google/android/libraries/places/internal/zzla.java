package com.google.android.libraries.places.internal;

import javax.annotation.CheckForNull;
/* loaded from: classes2.dex */
final class zzla extends zzlb {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public zzla(String str, String str2, @CheckForNull Character ch) {
        super(r0, ch);
        zzky zzkyVar = new zzky(str, str2.toCharArray());
        zzha.zzd(zzky.zzc(zzkyVar).length == 64);
    }

    @Override // com.google.android.libraries.places.internal.zzlb, com.google.android.libraries.places.internal.zzlc
    final void zza(Appendable appendable, byte[] bArr, int i2, int i3) {
        int i4 = 0;
        zzha.zzg(0, i3, bArr.length);
        int i5 = i3;
        while (i5 >= 3) {
            int i6 = i4 + 1;
            int i7 = i6 + 1;
            int i8 = ((bArr[i4] & 255) << 16) | ((bArr[i6] & 255) << 8) | (bArr[i7] & 255);
            appendable.append(this.zzb.zza(i8 >>> 18));
            appendable.append(this.zzb.zza((i8 >>> 12) & 63));
            appendable.append(this.zzb.zza((i8 >>> 6) & 63));
            appendable.append(this.zzb.zza(i8 & 63));
            i5 -= 3;
            i4 = i7 + 1;
        }
        if (i4 < i3) {
            zzc(appendable, bArr, i4, i3 - i4);
        }
    }
}

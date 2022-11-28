package com.google.android.libraries.places.internal;
/* loaded from: classes2.dex */
final class zzkz extends zzlb {
    final char[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public zzkz(String str, String str2) {
        super(r4, null);
        zzky zzkyVar = new zzky("base16()", "0123456789ABCDEF".toCharArray());
        this.zza = new char[512];
        zzha.zzd(zzky.zzc(zzkyVar).length == 16);
        for (int i2 = 0; i2 < 256; i2++) {
            this.zza[i2] = zzkyVar.zza(i2 >>> 4);
            this.zza[i2 | 256] = zzkyVar.zza(i2 & 15);
        }
    }

    @Override // com.google.android.libraries.places.internal.zzlb, com.google.android.libraries.places.internal.zzlc
    final void zza(Appendable appendable, byte[] bArr, int i2, int i3) {
        zzha.zzg(0, i3, bArr.length);
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = bArr[i4] & 255;
            appendable.append(this.zza[i5]);
            appendable.append(this.zza[i5 | 256]);
        }
    }
}

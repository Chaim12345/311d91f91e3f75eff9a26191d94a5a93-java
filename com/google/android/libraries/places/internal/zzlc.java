package com.google.android.libraries.places.internal;

import java.io.IOException;
/* loaded from: classes2.dex */
public abstract class zzlc {
    private static final zzlc zza = new zzla("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
    private static final zzlc zzb = new zzla("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');
    private static final zzlc zzc = new zzlb("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
    private static final zzlc zzd = new zzlb("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
    private static final zzlc zze = new zzkz("base16()", "0123456789ABCDEF");

    public static zzlc zzd() {
        return zze;
    }

    abstract void zza(Appendable appendable, byte[] bArr, int i2, int i3);

    abstract int zzb(int i2);

    public final String zze(byte[] bArr, int i2, int i3) {
        zzha.zzg(0, i3, bArr.length);
        StringBuilder sb = new StringBuilder(zzb(i3));
        try {
            zza(sb, bArr, 0, i3);
            return sb.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}

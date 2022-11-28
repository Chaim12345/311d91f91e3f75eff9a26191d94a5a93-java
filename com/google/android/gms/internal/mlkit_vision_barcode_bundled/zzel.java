package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;
/* loaded from: classes2.dex */
public final class zzel {

    /* renamed from: a  reason: collision with root package name */
    static final Charset f6425a;
    public static final byte[] zzd;
    public static final ByteBuffer zze;
    public static final zzde zzf;

    static {
        Charset.forName("US-ASCII");
        f6425a = Charset.forName("UTF-8");
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        zzd = bArr;
        zze = ByteBuffer.wrap(bArr);
        int i2 = zzde.zza;
        zzdd zzddVar = new zzdd(bArr, 0, 0, false, null);
        try {
            zzddVar.zza(0);
            zzf = zzddVar;
        } catch (zzen e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int i2, byte[] bArr, int i3, int i4) {
        for (int i5 = i3; i5 < i3 + i4; i5++) {
            i2 = (i2 * 31) + bArr[i5];
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object b(Object obj) {
        Objects.requireNonNull(obj);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object c(Object obj, String str) {
        Objects.requireNonNull(obj, str);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object d(Object obj, Object obj2) {
        return ((zzfl) obj).zzV().zzg((zzfl) obj2).zzm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean e(zzfl zzflVar) {
        if (zzflVar instanceof zzcl) {
            zzcl zzclVar = (zzcl) zzflVar;
            throw null;
        }
        return false;
    }

    public static int zza(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int zzb(byte[] bArr) {
        int length = bArr.length;
        int a2 = a(length, bArr, 0, length);
        if (a2 == 0) {
            return 1;
        }
        return a2;
    }

    public static int zzc(long j2) {
        return (int) (j2 ^ (j2 >>> 32));
    }

    public static String zzh(byte[] bArr) {
        return new String(bArr, f6425a);
    }

    public static boolean zzj(byte[] bArr) {
        return zzhe.h(bArr);
    }
}

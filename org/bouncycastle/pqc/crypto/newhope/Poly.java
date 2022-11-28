package org.bouncycastle.pqc.crypto.newhope;

import kotlin.UShort;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
class Poly {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(short[] sArr, short[] sArr2, short[] sArr3) {
        for (int i2 = 0; i2 < 1024; i2++) {
            sArr3[i2] = Reduce.a((short) (sArr[i2] + sArr2[i2]));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(short[] sArr, byte[] bArr) {
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = i2 * 7;
            int i4 = bArr[i3 + 1] & 255;
            int i5 = bArr[i3 + 3] & 255;
            int i6 = bArr[i3 + 5] & 255;
            int i7 = i2 * 4;
            sArr[i7 + 0] = (short) ((bArr[i3 + 0] & 255) | ((i4 & 63) << 8));
            sArr[i7 + 1] = (short) ((i4 >>> 6) | ((bArr[i3 + 2] & 255) << 2) | ((i5 & 15) << 10));
            sArr[i7 + 2] = (short) ((i5 >>> 4) | ((bArr[i3 + 4] & 255) << 4) | ((i6 & 3) << 12));
            sArr[i7 + 3] = (short) (((bArr[i3 + 6] & 255) << 6) | (i6 >>> 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void c(short[] sArr) {
        NTT.a(sArr);
        NTT.b(sArr, Precomp.f14560b);
        NTT.c(sArr, Precomp.f14562d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(short[] sArr, byte[] bArr, byte b2) {
        byte[] bArr2 = new byte[8];
        bArr2[0] = b2;
        byte[] bArr3 = new byte[4096];
        ChaCha20.a(bArr, bArr2, bArr3, 0, 4096);
        for (int i2 = 0; i2 < 1024; i2++) {
            int bigEndianToInt = Pack.bigEndianToInt(bArr3, i2 * 4);
            int i3 = 0;
            for (int i4 = 0; i4 < 8; i4++) {
                i3 += (bigEndianToInt >> i4) & 16843009;
            }
            sArr[i2] = (short) (((((i3 >>> 24) + (i3 >>> 0)) & 255) + 12289) - (((i3 >>> 16) + (i3 >>> 8)) & 255));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(short[] sArr, short[] sArr2, short[] sArr3) {
        for (int i2 = 0; i2 < 1024; i2++) {
            sArr3[i2] = Reduce.b((sArr[i2] & UShort.MAX_VALUE) * (65535 & Reduce.b((sArr2[i2] & UShort.MAX_VALUE) * 3186)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void f(byte[] bArr, short[] sArr) {
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = i2 * 4;
            short normalize = normalize(sArr[i3 + 0]);
            short normalize2 = normalize(sArr[i3 + 1]);
            short normalize3 = normalize(sArr[i3 + 2]);
            short normalize4 = normalize(sArr[i3 + 3]);
            int i4 = i2 * 7;
            bArr[i4 + 0] = (byte) normalize;
            bArr[i4 + 1] = (byte) ((normalize >> 8) | (normalize2 << 6));
            bArr[i4 + 2] = (byte) (normalize2 >> 2);
            bArr[i4 + 3] = (byte) ((normalize2 >> 10) | (normalize3 << 4));
            bArr[i4 + 4] = (byte) (normalize3 >> 4);
            bArr[i4 + 5] = (byte) ((normalize3 >> 12) | (normalize4 << 2));
            bArr[i4 + 6] = (byte) (normalize4 >> 6);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void g(short[] sArr) {
        NTT.c(sArr, Precomp.f14561c);
        NTT.b(sArr, Precomp.f14559a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void h(short[] sArr, byte[] bArr) {
        SHAKEDigest sHAKEDigest = new SHAKEDigest(128);
        sHAKEDigest.update(bArr, 0, bArr.length);
        int i2 = 0;
        while (true) {
            byte[] bArr2 = new byte[256];
            sHAKEDigest.doOutput(bArr2, 0, 256);
            for (int i3 = 0; i3 < 256; i3 += 2) {
                int i4 = (bArr2[i3] & 255) | ((bArr2[i3 + 1] & 255) << 8);
                if (i4 < 61445) {
                    int i5 = i2 + 1;
                    sArr[i2] = (short) i4;
                    if (i5 == 1024) {
                        return;
                    }
                    i2 = i5;
                }
            }
        }
    }

    private static short normalize(short s2) {
        short a2 = Reduce.a(s2);
        int i2 = a2 - 12289;
        return (short) (((a2 ^ i2) & (i2 >> 31)) ^ i2);
    }
}

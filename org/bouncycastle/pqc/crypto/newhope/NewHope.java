package org.bouncycastle.pqc.crypto.newhope;

import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.SHA3Digest;
/* loaded from: classes4.dex */
class NewHope {
    public static final int AGREEMENT_SIZE = 32;
    public static final int POLY_SIZE = 1024;
    public static final int SENDA_BYTES = 1824;
    public static final int SENDB_BYTES = 2048;
    private static final boolean STATISTICAL_TEST = false;

    static void a(short[] sArr, byte[] bArr, byte[] bArr2) {
        Poly.b(sArr, bArr2);
        System.arraycopy(bArr2, 1792, bArr, 0, 32);
    }

    static void b(short[] sArr, short[] sArr2, byte[] bArr) {
        Poly.b(sArr, bArr);
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = i2 * 4;
            int i4 = bArr[i2 + 1792] & 255;
            sArr2[i3 + 0] = (short) (i4 & 3);
            sArr2[i3 + 1] = (short) ((i4 >>> 2) & 3);
            sArr2[i3 + 2] = (short) ((i4 >>> 4) & 3);
            sArr2[i3 + 3] = (short) (i4 >>> 6);
        }
    }

    static void c(byte[] bArr, short[] sArr, byte[] bArr2) {
        Poly.f(bArr, sArr);
        System.arraycopy(bArr2, 0, bArr, 1792, 32);
    }

    static void d(byte[] bArr, short[] sArr, short[] sArr2) {
        Poly.f(bArr, sArr);
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = i2 * 4;
            bArr[i2 + 1792] = (byte) ((sArr2[i3 + 3] << 6) | sArr2[i3] | (sArr2[i3 + 1] << 2) | (sArr2[i3 + 2] << 4));
        }
    }

    static void e(short[] sArr, byte[] bArr) {
        Poly.h(sArr, bArr);
    }

    static void f(byte[] bArr) {
        SHA3Digest sHA3Digest = new SHA3Digest(256);
        sHA3Digest.update(bArr, 0, 32);
        sHA3Digest.doFinal(bArr, 0);
    }

    public static void keygen(SecureRandom secureRandom, byte[] bArr, short[] sArr) {
        byte[] bArr2 = new byte[32];
        secureRandom.nextBytes(bArr2);
        f(bArr2);
        short[] sArr2 = new short[1024];
        e(sArr2, bArr2);
        byte[] bArr3 = new byte[32];
        secureRandom.nextBytes(bArr3);
        Poly.d(sArr, bArr3, (byte) 0);
        Poly.g(sArr);
        short[] sArr3 = new short[1024];
        Poly.d(sArr3, bArr3, (byte) 1);
        Poly.g(sArr3);
        short[] sArr4 = new short[1024];
        Poly.e(sArr2, sArr, sArr4);
        short[] sArr5 = new short[1024];
        Poly.a(sArr4, sArr3, sArr5);
        c(bArr, sArr5, bArr2);
    }

    public static void sharedA(byte[] bArr, short[] sArr, byte[] bArr2) {
        short[] sArr2 = new short[1024];
        short[] sArr3 = new short[1024];
        b(sArr2, sArr3, bArr2);
        short[] sArr4 = new short[1024];
        Poly.e(sArr, sArr2, sArr4);
        Poly.c(sArr4);
        ErrorCorrection.f(bArr, sArr4, sArr3);
        f(bArr);
    }

    public static void sharedB(SecureRandom secureRandom, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        short[] sArr = new short[1024];
        byte[] bArr4 = new byte[32];
        a(sArr, bArr4, bArr3);
        short[] sArr2 = new short[1024];
        e(sArr2, bArr4);
        byte[] bArr5 = new byte[32];
        secureRandom.nextBytes(bArr5);
        short[] sArr3 = new short[1024];
        Poly.d(sArr3, bArr5, (byte) 0);
        Poly.g(sArr3);
        short[] sArr4 = new short[1024];
        Poly.d(sArr4, bArr5, (byte) 1);
        Poly.g(sArr4);
        short[] sArr5 = new short[1024];
        Poly.e(sArr2, sArr3, sArr5);
        Poly.a(sArr5, sArr4, sArr5);
        short[] sArr6 = new short[1024];
        Poly.e(sArr, sArr3, sArr6);
        Poly.c(sArr6);
        short[] sArr7 = new short[1024];
        Poly.d(sArr7, bArr5, (byte) 2);
        Poly.a(sArr6, sArr7, sArr6);
        short[] sArr8 = new short[1024];
        ErrorCorrection.e(sArr8, sArr6, bArr5, (byte) 3);
        d(bArr2, sArr5, sArr8);
        ErrorCorrection.f(bArr, sArr6, sArr8);
        f(bArr);
    }
}

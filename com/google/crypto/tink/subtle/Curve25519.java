package com.google.crypto.tink.subtle;

import com.facebook.stetho.dumpapp.Framer;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.google.crypto.tink.annotations.Alpha;
import java.security.InvalidKeyException;
import java.util.Arrays;
import org.bouncycastle.crypto.signers.PSSSigner;
@Alpha
/* loaded from: classes2.dex */
final class Curve25519 {

    /* renamed from: a  reason: collision with root package name */
    static final byte[][] f9837a = {new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-32, -21, 122, 124, 59, 65, -72, -82, Ascii.SYN, 86, -29, -6, -15, -97, -60, 106, -38, 9, -115, -21, -100, Framer.STDERR_FRAME_PREFIX, -79, -3, -122, 98, 5, Ascii.SYN, Framer.STDIN_REQUEST_FRAME_PREFIX, 73, -72, 0}, new byte[]{Framer.STDIN_REQUEST_FRAME_PREFIX, -100, -107, PSSSigner.TRAILER_IMPLICIT, -93, 80, -116, 36, -79, -48, -79, 85, -100, -125, ByteSourceJsonBootstrapper.UTF8_BOM_1, 91, 4, 68, 92, -60, 88, Ascii.FS, -114, -122, -40, 34, 78, -35, -48, -97, 17, 87}, new byte[]{-20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Byte.MAX_VALUE}, new byte[]{-19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Byte.MAX_VALUE}, new byte[]{-18, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Byte.MAX_VALUE}};

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(long[] jArr, long[] jArr2, int i2) {
        int i3 = -i2;
        for (int i4 = 0; i4 < 10; i4++) {
            jArr[i4] = ((((int) jArr[i4]) ^ ((int) jArr2[i4])) & i3) ^ ((int) jArr[i4]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(long[] jArr, byte[] bArr, byte[] bArr2) {
        validatePubKeyAndClearMsb(bArr2);
        long[] b2 = Field25519.b(bArr2);
        long[] jArr2 = new long[19];
        long[] jArr3 = new long[19];
        int i2 = 0;
        jArr3[0] = 1;
        long[] jArr4 = new long[19];
        jArr4[0] = 1;
        long[] jArr5 = new long[19];
        long[] jArr6 = new long[19];
        long[] jArr7 = new long[19];
        jArr7[0] = 1;
        long[] jArr8 = new long[19];
        long[] jArr9 = new long[19];
        jArr9[0] = 1;
        int i3 = 10;
        System.arraycopy(b2, 0, jArr2, 0, 10);
        int i4 = 0;
        while (i4 < 32) {
            int i5 = bArr[(32 - i4) - 1] & 255;
            long[] jArr10 = jArr6;
            long[] jArr11 = jArr7;
            long[] jArr12 = jArr2;
            long[] jArr13 = jArr3;
            int i6 = i2;
            long[] jArr14 = jArr8;
            long[] jArr15 = jArr9;
            long[] jArr16 = jArr5;
            long[] jArr17 = jArr4;
            long[] jArr18 = jArr16;
            while (i6 < 8) {
                int i7 = (i5 >> (7 - i6)) & 1;
                c(jArr17, jArr12, i7);
                c(jArr18, jArr13, i7);
                long[] jArr19 = jArr14;
                long[] jArr20 = jArr11;
                int i8 = i5;
                long[] jArr21 = jArr10;
                long[] jArr22 = jArr18;
                long[] jArr23 = jArr17;
                long[] jArr24 = jArr13;
                long[] jArr25 = jArr12;
                monty(jArr14, jArr15, jArr10, jArr11, jArr17, jArr18, jArr12, jArr13, b2);
                c(jArr19, jArr21, i7);
                c(jArr15, jArr20, i7);
                i6++;
                jArr13 = jArr20;
                jArr18 = jArr15;
                jArr17 = jArr19;
                jArr12 = jArr21;
                i5 = i8;
                jArr15 = jArr22;
                jArr14 = jArr23;
                jArr11 = jArr24;
                jArr10 = jArr25;
            }
            long[] jArr26 = jArr17;
            long[] jArr27 = jArr13;
            long[] jArr28 = jArr12;
            jArr7 = jArr11;
            i4++;
            jArr9 = jArr15;
            jArr8 = jArr14;
            jArr6 = jArr10;
            jArr5 = jArr18;
            jArr4 = jArr26;
            jArr3 = jArr27;
            jArr2 = jArr28;
            i2 = 0;
            i3 = 10;
        }
        long[] jArr29 = new long[i3];
        Field25519.c(jArr29, jArr5);
        Field25519.d(jArr, jArr4, jArr29);
        if (isCollinear(b2, jArr, jArr2, jArr3)) {
            return;
        }
        throw new IllegalStateException("Arithmetic error in curve multiplication with the public key: " + Hex.encode(bArr2));
    }

    static void c(long[] jArr, long[] jArr2, int i2) {
        int i3 = -i2;
        for (int i4 = 0; i4 < 10; i4++) {
            int i5 = (((int) jArr[i4]) ^ ((int) jArr2[i4])) & i3;
            jArr[i4] = ((int) jArr[i4]) ^ i5;
            jArr2[i4] = i5 ^ ((int) jArr2[i4]);
        }
    }

    private static boolean isCollinear(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4) {
        long[] jArr5 = new long[10];
        long[] jArr6 = new long[10];
        long[] jArr7 = new long[11];
        long[] jArr8 = new long[11];
        long[] jArr9 = new long[11];
        Field25519.d(jArr5, jArr, jArr2);
        Field25519.n(jArr6, jArr, jArr2);
        long[] jArr10 = new long[10];
        jArr10[0] = 486662;
        Field25519.n(jArr8, jArr6, jArr10);
        Field25519.d(jArr8, jArr8, jArr4);
        Field25519.m(jArr8, jArr3);
        Field25519.d(jArr8, jArr8, jArr5);
        Field25519.d(jArr8, jArr8, jArr3);
        Field25519.i(jArr7, jArr8, 4L);
        Field25519.g(jArr7);
        Field25519.d(jArr8, jArr5, jArr4);
        Field25519.l(jArr8, jArr8, jArr4);
        Field25519.d(jArr9, jArr6, jArr3);
        Field25519.n(jArr8, jArr8, jArr9);
        Field25519.j(jArr8, jArr8);
        return Bytes.equal(Field25519.a(jArr7), Field25519.a(jArr8));
    }

    private static void monty(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long[] jArr5, long[] jArr6, long[] jArr7, long[] jArr8, long[] jArr9) {
        long[] copyOf = Arrays.copyOf(jArr5, 10);
        long[] jArr10 = new long[19];
        long[] jArr11 = new long[19];
        long[] jArr12 = new long[19];
        long[] jArr13 = new long[19];
        long[] jArr14 = new long[19];
        long[] jArr15 = new long[19];
        long[] jArr16 = new long[19];
        Field25519.m(jArr5, jArr6);
        Field25519.k(jArr6, copyOf);
        long[] copyOf2 = Arrays.copyOf(jArr7, 10);
        Field25519.m(jArr7, jArr8);
        Field25519.k(jArr8, copyOf2);
        Field25519.e(jArr13, jArr7, jArr6);
        Field25519.e(jArr14, jArr5, jArr8);
        Field25519.h(jArr13);
        Field25519.g(jArr13);
        Field25519.h(jArr14);
        Field25519.g(jArr14);
        System.arraycopy(jArr13, 0, copyOf2, 0, 10);
        Field25519.m(jArr13, jArr14);
        Field25519.k(jArr14, copyOf2);
        Field25519.j(jArr16, jArr13);
        Field25519.j(jArr15, jArr14);
        Field25519.e(jArr14, jArr15, jArr9);
        Field25519.h(jArr14);
        Field25519.g(jArr14);
        System.arraycopy(jArr16, 0, jArr3, 0, 10);
        System.arraycopy(jArr14, 0, jArr4, 0, 10);
        Field25519.j(jArr11, jArr5);
        Field25519.j(jArr12, jArr6);
        Field25519.e(jArr, jArr11, jArr12);
        Field25519.h(jArr);
        Field25519.g(jArr);
        Field25519.k(jArr12, jArr11);
        Arrays.fill(jArr10, 10, 18, 0L);
        Field25519.i(jArr10, jArr12, 121665L);
        Field25519.g(jArr10);
        Field25519.m(jArr10, jArr11);
        Field25519.e(jArr2, jArr12, jArr10);
        Field25519.h(jArr2);
        Field25519.g(jArr2);
    }

    private static void validatePubKeyAndClearMsb(byte[] bArr) {
        if (bArr.length != 32) {
            throw new InvalidKeyException("Public key length is not 32-byte");
        }
        bArr[31] = (byte) (bArr[31] & Byte.MAX_VALUE);
        int i2 = 0;
        while (true) {
            byte[][] bArr2 = f9837a;
            if (i2 >= bArr2.length) {
                return;
            }
            if (Bytes.equal(bArr2[i2], bArr)) {
                throw new InvalidKeyException("Banned public key: " + Hex.encode(bArr2[i2]));
            }
            i2++;
        }
    }
}

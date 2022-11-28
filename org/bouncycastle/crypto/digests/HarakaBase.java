package org.bouncycastle.crypto.digests;

import com.facebook.stetho.dumpapp.Framer;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import okio.Utf8;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.signers.PSSSigner;
/* loaded from: classes3.dex */
public abstract class HarakaBase implements Digest {
    private static final byte[][] S = {new byte[]{99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118}, new byte[]{-54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64}, new byte[]{-73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, -91, -27, -15, 113, -40, Framer.STDOUT_FRAME_PREFIX, Ascii.NAK}, new byte[]{4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, -30, -21, 39, -78, 117}, new byte[]{9, -125, 44, Ascii.SUB, Ascii.ESC, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124}, new byte[]{83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49}, new byte[]{-48, ByteSourceJsonBootstrapper.UTF8_BOM_1, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88}, new byte[]{81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, Framer.ENTER_FRAME_PREFIX, 16, -1, -13, -46}, new byte[]{-51, Ascii.FF, 19, -20, Framer.STDIN_REQUEST_FRAME_PREFIX, -105, 68, Ascii.ETB, -60, -89, 126, 61, 100, 93, Ascii.EM, 115}, new byte[]{96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, Ascii.VT, -37}, new byte[]{-32, Framer.STDERR_FRAME_PREFIX, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121}, new byte[]{-25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8}, new byte[]{-70, Framer.EXIT_FRAME_PREFIX, 37, 46, Ascii.FS, -90, -76, -58, -24, -35, 116, Ascii.US, 75, -67, -117, -118}, new byte[]{112, 62, -75, 102, 72, 3, -10, Ascii.SO, 97, 53, 87, -71, -122, -63, Ascii.GS, -98}, new byte[]{-31, -8, -104, 17, 105, -39, -114, -108, -101, Ascii.RS, -121, -23, -50, 85, 40, -33}, new byte[]{-116, -95, -119, Ascii.CR, ByteSourceJsonBootstrapper.UTF8_BOM_3, -26, 66, 104, 65, -103, Framer.STDIN_FRAME_PREFIX, Ascii.SI, -80, 84, ByteSourceJsonBootstrapper.UTF8_BOM_2, Ascii.SYN}};

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] mixColumns = mixColumns(c(d(bArr)));
        g(mixColumns, bArr2);
        return mixColumns;
    }

    static byte b(byte b2) {
        return S[(b2 & 255) >>> 4][b2 & Ascii.SI];
    }

    static byte[] c(byte[] bArr) {
        return new byte[]{bArr[0], bArr[5], bArr[10], bArr[15], bArr[4], bArr[9], bArr[14], bArr[3], bArr[8], bArr[13], bArr[2], bArr[7], bArr[12], bArr[1], bArr[6], bArr[11]};
    }

    static byte[] d(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        bArr2[0] = b(bArr[0]);
        bArr2[1] = b(bArr[1]);
        bArr2[2] = b(bArr[2]);
        bArr2[3] = b(bArr[3]);
        bArr2[4] = b(bArr[4]);
        bArr2[5] = b(bArr[5]);
        bArr2[6] = b(bArr[6]);
        bArr2[7] = b(bArr[7]);
        bArr2[8] = b(bArr[8]);
        bArr2[9] = b(bArr[9]);
        bArr2[10] = b(bArr[10]);
        bArr2[11] = b(bArr[11]);
        bArr2[12] = b(bArr[12]);
        bArr2[13] = b(bArr[13]);
        bArr2[14] = b(bArr[14]);
        bArr2[15] = b(bArr[15]);
        return bArr2;
    }

    static byte e(byte b2) {
        int i2 = b2 >>> 7;
        int i3 = b2 << 1;
        if (i2 > 0) {
            i3 ^= 27;
        }
        return (byte) (i3 & 255);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] f(byte[] bArr, byte[] bArr2, int i2) {
        byte[] bArr3 = new byte[16];
        int i3 = 0;
        while (i3 < 16) {
            bArr3[i3] = (byte) (bArr2[i2] ^ bArr[i3]);
            i3++;
            i2++;
        }
        return bArr3;
    }

    static void g(byte[] bArr, byte[] bArr2) {
        bArr[0] = (byte) (bArr[0] ^ bArr2[15]);
        bArr[1] = (byte) (bArr[1] ^ bArr2[14]);
        bArr[2] = (byte) (bArr[2] ^ bArr2[13]);
        bArr[3] = (byte) (bArr[3] ^ bArr2[12]);
        bArr[4] = (byte) (bArr[4] ^ bArr2[11]);
        bArr[5] = (byte) (bArr[5] ^ bArr2[10]);
        bArr[6] = (byte) (bArr[6] ^ bArr2[9]);
        bArr[7] = (byte) (bArr[7] ^ bArr2[8]);
        bArr[8] = (byte) (bArr2[7] ^ bArr[8]);
        bArr[9] = (byte) (bArr2[6] ^ bArr[9]);
        bArr[10] = (byte) (bArr2[5] ^ bArr[10]);
        bArr[11] = (byte) (bArr2[4] ^ bArr[11]);
        bArr[12] = (byte) (bArr2[3] ^ bArr[12]);
        bArr[13] = (byte) (bArr2[2] ^ bArr[13]);
        bArr[14] = (byte) (bArr2[1] ^ bArr[14]);
        bArr[15] = (byte) (bArr2[0] ^ bArr[15]);
    }

    private static byte[] mixColumns(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            int i4 = i2 + 1;
            int i5 = i3 * 4;
            int i6 = i5 + 1;
            int i7 = i5 + 2;
            int i8 = i5 + 3;
            bArr2[i2] = (byte) ((((e(bArr[i5]) ^ e(bArr[i6])) ^ bArr[i6]) ^ bArr[i7]) ^ bArr[i8]);
            int i9 = i4 + 1;
            bArr2[i4] = (byte) ((((bArr[i5] ^ e(bArr[i6])) ^ e(bArr[i7])) ^ bArr[i7]) ^ bArr[i8]);
            int i10 = i9 + 1;
            bArr2[i9] = (byte) ((((bArr[i5] ^ bArr[i6]) ^ e(bArr[i7])) ^ e(bArr[i8])) ^ bArr[i8]);
            i2 = i10 + 1;
            bArr2[i10] = (byte) ((((bArr[i5] ^ e(bArr[i5])) ^ bArr[i6]) ^ bArr[i7]) ^ e(bArr[i8]));
        }
        return bArr2;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }
}

package org.bouncycastle.crypto.engines;

import com.facebook.stetho.dumpapp.Framer;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.lang.reflect.Array;
import okio.Utf8;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes3.dex */
public class ARIAEngine implements BlockCipher {
    private static final byte[][] C = {Hex.decodeStrict("517cc1b727220a94fe13abe8fa9a6ee0"), Hex.decodeStrict("6db14acc9e21c820ff28b1d5ef5de2b0"), Hex.decodeStrict("db92371d2126e9700324977504e8c90e")};
    private static final byte[] SB1_sbox = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, -91, -27, -15, 113, -40, Framer.STDOUT_FRAME_PREFIX, Ascii.NAK, 4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, Ascii.SUB, Ascii.ESC, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, ByteSourceJsonBootstrapper.UTF8_BOM_1, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, Framer.ENTER_FRAME_PREFIX, 16, -1, -13, -46, -51, Ascii.FF, 19, -20, Framer.STDIN_REQUEST_FRAME_PREFIX, -105, 68, Ascii.ETB, -60, -89, 126, 61, 100, 93, Ascii.EM, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, Ascii.VT, -37, -32, Framer.STDERR_FRAME_PREFIX, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, Framer.EXIT_FRAME_PREFIX, 37, 46, Ascii.FS, -90, -76, -58, -24, -35, 116, Ascii.US, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, Ascii.SO, 97, 53, 87, -71, -122, -63, Ascii.GS, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, Ascii.RS, -121, -23, -50, 85, 40, -33, -116, -95, -119, Ascii.CR, ByteSourceJsonBootstrapper.UTF8_BOM_3, -26, 66, 104, 65, -103, Framer.STDIN_FRAME_PREFIX, Ascii.SI, -80, 84, ByteSourceJsonBootstrapper.UTF8_BOM_2, Ascii.SYN};
    private static final byte[] SB2_sbox = {-30, 78, 84, -4, -108, -62, 74, -52, 98, Ascii.CR, 106, 70, 60, 77, -117, -47, 94, -6, 100, -53, -76, -105, -66, 43, PSSSigner.TRAILER_IMPLICIT, 119, 46, 3, -45, Ascii.EM, 89, -63, Ascii.GS, 6, 65, 107, 85, -16, -103, 105, -22, -100, Ascii.CAN, -82, 99, -33, -25, ByteSourceJsonBootstrapper.UTF8_BOM_2, 0, 115, 102, -5, -106, 76, -123, -28, 58, 9, 69, -86, Ascii.SI, -18, 16, -21, Framer.STDIN_FRAME_PREFIX, Byte.MAX_VALUE, -12, 41, -84, -49, -83, -111, -115, Framer.EXIT_FRAME_PREFIX, -56, -107, -7, 47, -50, -51, 8, 122, -120, 56, 92, -125, 42, 40, 71, -37, -72, -57, -109, -92, Ascii.DC2, 83, -1, -121, Ascii.SO, Framer.STDOUT_FRAME_PREFIX, 54, Framer.ENTER_FRAME_PREFIX, 88, 72, 1, -114, 55, 116, Framer.STDERR_FRAME_PREFIX, -54, -23, -79, -73, -85, Ascii.FF, -41, -60, 86, 66, 38, 7, -104, 96, -39, -74, -71, 17, SignedBytes.MAX_POWER_OF_TWO, -20, 32, -116, -67, -96, -55, -124, 4, 73, 35, -15, 79, 80, Ascii.US, 19, -36, -40, -64, -98, 87, -29, -61, 123, 101, 59, 2, -113, 62, -24, 37, -110, -27, Ascii.NAK, -35, -3, Ascii.ETB, -87, ByteSourceJsonBootstrapper.UTF8_BOM_3, -44, -102, 126, -59, 57, 103, -2, 118, -99, 67, -89, -31, -48, -11, 104, -14, Ascii.ESC, 52, 112, 5, -93, -118, -43, 121, -122, -88, 48, -58, 81, 75, Ascii.RS, -90, 39, -10, 53, -46, 110, 36, Ascii.SYN, -126, Framer.STDIN_REQUEST_FRAME_PREFIX, -38, -26, 117, -94, ByteSourceJsonBootstrapper.UTF8_BOM_1, 44, -78, Ascii.FS, -97, 93, 111, Byte.MIN_VALUE, 10, 114, 68, -101, 108, -112, Ascii.VT, 91, 51, 125, 90, 82, -13, 97, -95, -9, -80, -42, Utf8.REPLACEMENT_BYTE, 124, 109, -19, Ascii.DC4, -32, -91, 61, 34, -77, -8, -119, -34, 113, Ascii.SUB, -81, -70, -75, -127};
    private static final byte[] SB3_sbox = {82, 9, 106, -43, 48, 54, -91, 56, ByteSourceJsonBootstrapper.UTF8_BOM_3, SignedBytes.MAX_POWER_OF_TWO, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, Framer.STDERR_FRAME_PREFIX, -90, -62, 35, 61, -18, 76, -107, Ascii.VT, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, Ascii.SYN, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, Ascii.NAK, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, Ascii.RS, -113, -54, Utf8.REPLACEMENT_BYTE, Ascii.SI, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, Ascii.FS, 117, -33, 110, 71, -15, Ascii.SUB, 113, Ascii.GS, 41, -59, -119, 111, -73, 98, Ascii.SO, -86, Ascii.CAN, -66, Ascii.ESC, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, Framer.EXIT_FRAME_PREFIX, -51, 90, -12, Ascii.US, -35, -88, 51, -120, 7, -57, Framer.STDOUT_FRAME_PREFIX, -79, Ascii.DC2, 16, 89, 39, Byte.MIN_VALUE, -20, Framer.STDIN_REQUEST_FRAME_PREFIX, 96, 81, Byte.MAX_VALUE, -87, Ascii.EM, -75, 74, Ascii.CR, Framer.STDIN_FRAME_PREFIX, -27, 122, -97, -109, -55, -100, ByteSourceJsonBootstrapper.UTF8_BOM_1, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, ByteSourceJsonBootstrapper.UTF8_BOM_2, 60, -125, 83, -103, 97, Ascii.ETB, 43, 4, 126, -70, 119, -42, 38, -31, 105, Ascii.DC4, 99, 85, Framer.ENTER_FRAME_PREFIX, Ascii.FF, 125};
    private static final byte[] SB4_sbox = {48, 104, -103, Ascii.ESC, -121, -71, Framer.ENTER_FRAME_PREFIX, Framer.EXIT_FRAME_PREFIX, 80, 57, -37, -31, 114, 9, 98, 60, 62, 126, 94, -114, -15, -96, -52, -93, 42, Ascii.GS, -5, -74, -42, 32, -60, -115, -127, 101, -11, -119, -53, -99, 119, -58, 87, 67, 86, Ascii.ETB, -44, SignedBytes.MAX_POWER_OF_TWO, Ascii.SUB, 77, -64, 99, 108, -29, -73, -56, 100, 106, 83, -86, 56, -104, Ascii.FF, -12, -101, -19, Byte.MAX_VALUE, 34, 118, -81, -35, 58, Ascii.VT, 88, 103, -120, 6, -61, 53, Ascii.CR, 1, -117, -116, -62, -26, Framer.STDIN_REQUEST_FRAME_PREFIX, 2, 36, 117, -109, 102, Ascii.RS, -27, -30, 84, -40, 16, -50, 122, -24, 8, 44, Ascii.DC2, -105, Framer.STDERR_FRAME_PREFIX, -85, -76, 39, 10, 35, -33, ByteSourceJsonBootstrapper.UTF8_BOM_1, -54, -39, -72, -6, -36, Framer.STDOUT_FRAME_PREFIX, 107, -47, -83, Ascii.EM, 73, -67, 81, -106, -18, -28, -88, 65, -38, -1, -51, 85, -122, 54, -66, 97, 82, -8, ByteSourceJsonBootstrapper.UTF8_BOM_2, Ascii.SO, -126, 72, 105, -102, -32, 71, -98, 92, 4, 75, 52, Ascii.NAK, 121, 38, -89, -34, 41, -82, -110, -41, -124, -23, -46, -70, 93, -13, -59, -80, ByteSourceJsonBootstrapper.UTF8_BOM_3, -92, 59, 113, 68, 70, 43, -4, -21, 111, -43, -10, Ascii.DC4, -2, 124, 112, 90, 125, -3, 47, Ascii.CAN, -125, Ascii.SYN, -91, -111, Ascii.US, 5, -107, 116, -87, -63, 91, 74, -123, 109, 19, 7, 79, 78, 69, -78, Ascii.SI, -55, Ascii.FS, -90, PSSSigner.TRAILER_IMPLICIT, -20, 115, -112, 123, -49, 89, -113, -95, -7, Framer.STDIN_FRAME_PREFIX, -14, -79, 0, -108, 55, -97, -48, 46, -100, 110, 40, Utf8.REPLACEMENT_BYTE, Byte.MIN_VALUE, -16, 61, -45, 37, -118, -75, -25, 66, -77, -57, -22, -9, 76, 17, 51, 3, -94, -84, 96};
    private byte[][] roundKeys;

    protected static void a(byte[] bArr) {
        byte b2 = bArr[0];
        byte b3 = bArr[1];
        byte b4 = bArr[2];
        byte b5 = bArr[3];
        byte b6 = bArr[4];
        byte b7 = bArr[5];
        byte b8 = bArr[6];
        byte b9 = bArr[7];
        byte b10 = bArr[8];
        byte b11 = bArr[9];
        byte b12 = bArr[10];
        byte b13 = bArr[11];
        byte b14 = bArr[12];
        byte b15 = bArr[13];
        byte b16 = bArr[14];
        byte b17 = bArr[15];
        bArr[0] = (byte) ((((((b5 ^ b6) ^ b8) ^ b10) ^ b11) ^ b15) ^ b16);
        bArr[1] = (byte) ((((((b4 ^ b7) ^ b9) ^ b10) ^ b11) ^ b14) ^ b17);
        bArr[2] = (byte) ((((((b3 ^ b6) ^ b8) ^ b12) ^ b13) ^ b14) ^ b17);
        bArr[3] = (byte) ((((((b2 ^ b7) ^ b9) ^ b12) ^ b13) ^ b15) ^ b16);
        int i2 = b2 ^ b4;
        bArr[4] = (byte) (((((i2 ^ b7) ^ b10) ^ b13) ^ b16) ^ b17);
        int i3 = b3 ^ b5;
        bArr[5] = (byte) (((((i3 ^ b6) ^ b11) ^ b12) ^ b16) ^ b17);
        bArr[6] = (byte) (((((i2 ^ b9) ^ b11) ^ b12) ^ b14) ^ b15);
        bArr[7] = (byte) (((((i3 ^ b8) ^ b10) ^ b13) ^ b14) ^ b15);
        int i4 = b2 ^ b3;
        bArr[8] = (byte) (((((i4 ^ b6) ^ b9) ^ b12) ^ b15) ^ b17);
        bArr[9] = (byte) (((((i4 ^ b7) ^ b8) ^ b13) ^ b14) ^ b16);
        int i5 = b4 ^ b5;
        bArr[10] = (byte) (((((i5 ^ b7) ^ b8) ^ b10) ^ b15) ^ b17);
        bArr[11] = (byte) (((((i5 ^ b6) ^ b9) ^ b11) ^ b14) ^ b16);
        int i6 = b3 ^ b4;
        bArr[12] = (byte) (((((i6 ^ b8) ^ b9) ^ b11) ^ b13) ^ b14);
        int i7 = b2 ^ b5;
        bArr[13] = (byte) (((((i7 ^ b8) ^ b9) ^ b10) ^ b12) ^ b15);
        bArr[14] = (byte) (((((i7 ^ b6) ^ b7) ^ b11) ^ b13) ^ b16);
        bArr[15] = (byte) (((((i6 ^ b6) ^ b7) ^ b10) ^ b12) ^ b17);
    }

    protected static void b(byte[] bArr, byte[] bArr2) {
        m(bArr, bArr2);
        i(bArr);
        a(bArr);
    }

    protected static void c(byte[] bArr, byte[] bArr2) {
        m(bArr, bArr2);
        h(bArr);
        a(bArr);
    }

    protected static byte d(byte b2) {
        return SB1_sbox[b2 & 255];
    }

    protected static byte e(byte b2) {
        return SB2_sbox[b2 & 255];
    }

    protected static byte f(byte b2) {
        return SB3_sbox[b2 & 255];
    }

    protected static byte g(byte b2) {
        return SB4_sbox[b2 & 255];
    }

    protected static void h(byte[] bArr) {
        bArr[0] = d(bArr[0]);
        bArr[1] = e(bArr[1]);
        bArr[2] = f(bArr[2]);
        bArr[3] = g(bArr[3]);
        bArr[4] = d(bArr[4]);
        bArr[5] = e(bArr[5]);
        bArr[6] = f(bArr[6]);
        bArr[7] = g(bArr[7]);
        bArr[8] = d(bArr[8]);
        bArr[9] = e(bArr[9]);
        bArr[10] = f(bArr[10]);
        bArr[11] = g(bArr[11]);
        bArr[12] = d(bArr[12]);
        bArr[13] = e(bArr[13]);
        bArr[14] = f(bArr[14]);
        bArr[15] = g(bArr[15]);
    }

    protected static void i(byte[] bArr) {
        bArr[0] = f(bArr[0]);
        bArr[1] = g(bArr[1]);
        bArr[2] = d(bArr[2]);
        bArr[3] = e(bArr[3]);
        bArr[4] = f(bArr[4]);
        bArr[5] = g(bArr[5]);
        bArr[6] = d(bArr[6]);
        bArr[7] = e(bArr[7]);
        bArr[8] = f(bArr[8]);
        bArr[9] = g(bArr[9]);
        bArr[10] = d(bArr[10]);
        bArr[11] = e(bArr[11]);
        bArr[12] = f(bArr[12]);
        bArr[13] = g(bArr[13]);
        bArr[14] = d(bArr[14]);
        bArr[15] = e(bArr[15]);
    }

    protected static byte[][] j(boolean z, byte[] bArr) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i2 = (length >>> 3) - 2;
        byte[][] bArr2 = C;
        byte[] bArr3 = bArr2[i2];
        byte[] bArr4 = bArr2[(i2 + 1) % 3];
        byte[] bArr5 = bArr2[(i2 + 2) % 3];
        byte[] bArr6 = new byte[16];
        byte[] bArr7 = new byte[16];
        System.arraycopy(bArr, 0, bArr6, 0, 16);
        System.arraycopy(bArr, 16, bArr7, 0, length - 16);
        byte[] bArr8 = new byte[16];
        byte[] bArr9 = new byte[16];
        byte[] bArr10 = new byte[16];
        byte[] bArr11 = new byte[16];
        System.arraycopy(bArr6, 0, bArr8, 0, 16);
        System.arraycopy(bArr8, 0, bArr9, 0, 16);
        c(bArr9, bArr3);
        m(bArr9, bArr7);
        System.arraycopy(bArr9, 0, bArr10, 0, 16);
        b(bArr10, bArr4);
        m(bArr10, bArr8);
        System.arraycopy(bArr10, 0, bArr11, 0, 16);
        c(bArr11, bArr5);
        m(bArr11, bArr9);
        int i3 = (i2 * 2) + 12;
        byte[][] bArr12 = (byte[][]) Array.newInstance(byte.class, i3 + 1, 16);
        k(bArr12[0], bArr8, bArr9, 19);
        k(bArr12[1], bArr9, bArr10, 19);
        k(bArr12[2], bArr10, bArr11, 19);
        k(bArr12[3], bArr11, bArr8, 19);
        k(bArr12[4], bArr8, bArr9, 31);
        k(bArr12[5], bArr9, bArr10, 31);
        k(bArr12[6], bArr10, bArr11, 31);
        k(bArr12[7], bArr11, bArr8, 31);
        k(bArr12[8], bArr8, bArr9, 67);
        k(bArr12[9], bArr9, bArr10, 67);
        k(bArr12[10], bArr10, bArr11, 67);
        k(bArr12[11], bArr11, bArr8, 67);
        k(bArr12[12], bArr8, bArr9, 97);
        if (i3 > 12) {
            k(bArr12[13], bArr9, bArr10, 97);
            k(bArr12[14], bArr10, bArr11, 97);
            if (i3 > 14) {
                k(bArr12[15], bArr11, bArr8, 97);
                k(bArr12[16], bArr8, bArr9, 109);
            }
        }
        if (!z) {
            l(bArr12);
            for (int i4 = 1; i4 < i3; i4++) {
                a(bArr12[i4]);
            }
        }
        return bArr12;
    }

    protected static void k(byte[] bArr, byte[] bArr2, byte[] bArr3, int i2) {
        int i3 = i2 >>> 3;
        int i4 = i2 & 7;
        int i5 = 8 - i4;
        int i6 = bArr3[15 - i3] & 255;
        int i7 = 0;
        while (i7 < 16) {
            int i8 = bArr3[(i7 - i3) & 15] & 255;
            bArr[i7] = (byte) (((i6 << i5) | (i8 >>> i4)) ^ (bArr2[i7] & 255));
            i7++;
            i6 = i8;
        }
    }

    protected static void l(byte[][] bArr) {
        int length = bArr.length;
        int i2 = length / 2;
        int i3 = length - 1;
        for (int i4 = 0; i4 < i2; i4++) {
            byte[] bArr2 = bArr[i4];
            int i5 = i3 - i4;
            bArr[i4] = bArr[i5];
            bArr[i5] = bArr2;
        }
    }

    protected static void m(byte[] bArr, byte[] bArr2) {
        for (int i2 = 0; i2 < 16; i2++) {
            bArr[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "ARIA";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.roundKeys = j(z, ((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to ARIA init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (this.roundKeys != null) {
            if (i2 <= bArr.length - 16) {
                if (i3 <= bArr2.length - 16) {
                    byte[] bArr3 = new byte[16];
                    System.arraycopy(bArr, i2, bArr3, 0, 16);
                    int length = this.roundKeys.length - 3;
                    int i4 = 0;
                    while (i4 < length) {
                        int i5 = i4 + 1;
                        c(bArr3, this.roundKeys[i4]);
                        b(bArr3, this.roundKeys[i5]);
                        i4 = i5 + 1;
                    }
                    int i6 = i4 + 1;
                    c(bArr3, this.roundKeys[i4]);
                    m(bArr3, this.roundKeys[i6]);
                    i(bArr3);
                    m(bArr3, this.roundKeys[i6 + 1]);
                    System.arraycopy(bArr3, 0, bArr2, i3, 16);
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("ARIA engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}

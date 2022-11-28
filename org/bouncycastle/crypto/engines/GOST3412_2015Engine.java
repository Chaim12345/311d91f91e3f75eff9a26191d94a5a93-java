package org.bouncycastle.crypto.engines;

import com.facebook.stetho.dumpapp.Framer;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import okio.Utf8;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public class GOST3412_2015Engine implements BlockCipher {
    private static final byte[] PI = {-4, -18, -35, 17, -49, 110, Framer.STDOUT_FRAME_PREFIX, Ascii.SYN, -5, -60, -6, -38, 35, -59, 4, 77, -23, 119, -16, -37, -109, 46, -103, -70, Ascii.ETB, 54, -15, ByteSourceJsonBootstrapper.UTF8_BOM_2, Ascii.DC4, -51, Framer.STDIN_REQUEST_FRAME_PREFIX, -63, -7, Ascii.CAN, 101, 90, -30, 92, ByteSourceJsonBootstrapper.UTF8_BOM_1, Framer.ENTER_FRAME_PREFIX, -127, Ascii.FS, 60, 66, -117, 1, -114, 79, 5, -124, 2, -82, -29, 106, -113, -96, 6, Ascii.VT, -19, -104, Byte.MAX_VALUE, -44, -45, Ascii.US, -21, 52, 44, 81, -22, -56, 72, -85, -14, 42, 104, -94, -3, 58, -50, -52, -75, 112, Ascii.SO, 86, 8, Ascii.FF, 118, Ascii.DC2, ByteSourceJsonBootstrapper.UTF8_BOM_3, 114, 19, 71, -100, -73, 93, -121, Ascii.NAK, -95, -106, 41, 16, 123, -102, -57, -13, -111, Framer.EXIT_FRAME_PREFIX, 111, -99, -98, -78, -79, Framer.STDERR_FRAME_PREFIX, 117, Ascii.EM, 61, -1, 53, -118, 126, 109, 84, -58, Byte.MIN_VALUE, -61, -67, Ascii.CR, 87, -33, -11, 36, -87, 62, -88, 67, -55, -41, 121, -42, -10, 124, 34, -71, 3, -32, Ascii.SI, -20, -34, 122, -108, -80, PSSSigner.TRAILER_IMPLICIT, -36, -24, 40, 80, 78, 51, 10, 74, -89, -105, 96, 115, Ascii.RS, 0, 98, 68, Ascii.SUB, -72, 56, -126, 100, -97, 38, 65, -83, 69, 70, -110, 39, 94, 85, 47, -116, -93, -91, 125, 105, -43, -107, 59, 7, 88, -77, SignedBytes.MAX_POWER_OF_TWO, -122, -84, Ascii.GS, -9, 48, 55, 107, -28, -120, -39, -25, -119, -31, Ascii.ESC, -125, 73, 76, Utf8.REPLACEMENT_BYTE, -8, -2, -115, 83, -86, -112, -54, -40, -123, 97, 32, 113, 103, -92, Framer.STDIN_FRAME_PREFIX, 43, 9, 91, -53, -101, 37, -48, -66, -27, 108, 82, 89, -90, 116, -46, -26, -12, -76, -64, -47, 102, -81, -62, 57, 75, 99, -74};
    private static final byte[] inversePI = {-91, Framer.STDIN_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, -113, Ascii.SO, 48, 56, -64, 84, -26, -98, 57, 85, 126, 82, -111, 100, 3, 87, 90, Ascii.FS, 96, 7, Ascii.CAN, Framer.ENTER_FRAME_PREFIX, 114, -88, -47, 41, -58, -92, Utf8.REPLACEMENT_BYTE, -32, 39, -115, Ascii.FF, -126, -22, -82, -76, -102, 99, 73, -27, 66, -28, Ascii.NAK, -73, -56, 6, 112, -99, 65, 117, Ascii.EM, -55, -86, -4, 77, ByteSourceJsonBootstrapper.UTF8_BOM_3, 42, 115, -124, -43, -61, -81, 43, -122, -89, -79, -78, 91, 70, -45, -97, -3, -44, Ascii.SI, -100, 47, -101, 67, ByteSourceJsonBootstrapper.UTF8_BOM_1, -39, 121, -74, 83, Byte.MAX_VALUE, -63, -16, 35, -25, 37, 94, -75, Ascii.RS, -94, -33, -90, -2, -84, 34, -7, -30, 74, PSSSigner.TRAILER_IMPLICIT, 53, -54, -18, Framer.EXIT_FRAME_PREFIX, 5, 107, 81, -31, 89, -93, -14, 113, 86, 17, 106, -119, -108, 101, -116, ByteSourceJsonBootstrapper.UTF8_BOM_2, 119, 60, 123, 40, -85, -46, Framer.STDOUT_FRAME_PREFIX, -34, -60, Framer.STDIN_REQUEST_FRAME_PREFIX, -52, -49, 118, 44, -72, -40, 46, 54, -37, 105, -77, Ascii.DC4, -107, -66, 98, -95, 59, Ascii.SYN, 102, -23, 92, 108, 109, -83, 55, 97, 75, -71, -29, -70, -15, -96, -123, -125, -38, 71, -59, -80, 51, -6, -106, 111, 110, -62, -10, 80, -1, 93, -87, -114, Ascii.ETB, Ascii.ESC, -105, 125, -20, 88, -9, Ascii.US, -5, 124, 9, Ascii.CR, 122, 103, 69, -121, -36, -24, 79, Ascii.GS, 78, 4, -21, -8, -13, 62, 61, -67, -118, -120, -35, -51, Ascii.VT, 19, -104, 2, -109, Byte.MIN_VALUE, -112, -48, 36, 52, -53, -19, -12, -50, -103, 16, 68, SignedBytes.MAX_POWER_OF_TWO, -110, 58, 1, 38, Ascii.DC2, Ascii.SUB, 72, 104, -11, -127, -117, -57, -42, 32, 10, 8, 0, 76, -41, 116};
    private boolean forEncryption;
    private final byte[] lFactors = {-108, 32, -123, 16, -62, -64, 1, -5, 1, -64, -62, 16, -123, 32, -108, 1};
    private int KEY_LENGTH = 32;
    private int SUB_LENGTH = 32 / 2;
    private byte[][] subKeys = null;
    private byte[][] _gf_mul = init_gf256_mul_table();

    private void C(byte[] bArr, int i2) {
        Arrays.clear(bArr);
        bArr[15] = (byte) i2;
        L(bArr);
    }

    private void F(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] LSX = LSX(bArr, bArr2);
        X(LSX, bArr3);
        System.arraycopy(bArr2, 0, bArr3, 0, this.SUB_LENGTH);
        System.arraycopy(LSX, 0, bArr2, 0, this.SUB_LENGTH);
    }

    private void GOST3412_2015Func(byte[] bArr, int i2, byte[] bArr2, int i3) {
        byte[][] bArr3;
        byte[] bArr4 = new byte[16];
        System.arraycopy(bArr, i2, bArr4, 0, 16);
        int i4 = 9;
        if (this.forEncryption) {
            for (int i5 = 0; i5 < 9; i5++) {
                bArr4 = Arrays.copyOf(LSX(this.subKeys[i5], bArr4), 16);
            }
            X(bArr4, this.subKeys[9]);
        } else {
            while (true) {
                bArr3 = this.subKeys;
                if (i4 <= 0) {
                    break;
                }
                bArr4 = Arrays.copyOf(XSL(bArr3[i4], bArr4), 16);
                i4--;
            }
            X(bArr4, bArr3[0]);
        }
        System.arraycopy(bArr4, 0, bArr2, i3, 16);
    }

    private void L(byte[] bArr) {
        for (int i2 = 0; i2 < 16; i2++) {
            R(bArr);
        }
    }

    private byte[] LSX(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        X(copyOf, bArr2);
        S(copyOf);
        L(copyOf);
        return copyOf;
    }

    private void R(byte[] bArr) {
        byte l2 = l(bArr);
        System.arraycopy(bArr, 0, bArr, 1, 15);
        bArr[0] = l2;
    }

    private void S(byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = PI[unsignedByte(bArr[i2])];
        }
    }

    private void X(byte[] bArr, byte[] bArr2) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
        }
    }

    private byte[] XSL(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        X(copyOf, bArr2);
        inverseL(copyOf);
        inverseS(copyOf);
        return copyOf;
    }

    private void generateSubKeys(byte[] bArr) {
        int i2;
        if (bArr.length != this.KEY_LENGTH) {
            throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
        }
        this.subKeys = new byte[10];
        for (int i3 = 0; i3 < 10; i3++) {
            this.subKeys[i3] = new byte[this.SUB_LENGTH];
        }
        int i4 = this.SUB_LENGTH;
        byte[] bArr2 = new byte[i4];
        byte[] bArr3 = new byte[i4];
        int i5 = 0;
        while (true) {
            i2 = this.SUB_LENGTH;
            if (i5 >= i2) {
                break;
            }
            byte[][] bArr4 = this.subKeys;
            byte[] bArr5 = bArr4[0];
            byte b2 = bArr[i5];
            bArr2[i5] = b2;
            bArr5[i5] = b2;
            byte[] bArr6 = bArr4[1];
            byte b3 = bArr[i2 + i5];
            bArr3[i5] = b3;
            bArr6[i5] = b3;
            i5++;
        }
        byte[] bArr7 = new byte[i2];
        for (int i6 = 1; i6 < 5; i6++) {
            for (int i7 = 1; i7 <= 8; i7++) {
                C(bArr7, ((i6 - 1) * 8) + i7);
                F(bArr7, bArr2, bArr3);
            }
            int i8 = i6 * 2;
            System.arraycopy(bArr2, 0, this.subKeys[i8], 0, this.SUB_LENGTH);
            System.arraycopy(bArr3, 0, this.subKeys[i8 + 1], 0, this.SUB_LENGTH);
        }
    }

    private static byte[][] init_gf256_mul_table() {
        byte[][] bArr = new byte[256];
        for (int i2 = 0; i2 < 256; i2++) {
            bArr[i2] = new byte[256];
            for (int i3 = 0; i3 < 256; i3++) {
                bArr[i2][i3] = kuz_mul_gf256_slow((byte) i2, (byte) i3);
            }
        }
        return bArr;
    }

    private void inverseL(byte[] bArr) {
        for (int i2 = 0; i2 < 16; i2++) {
            inverseR(bArr);
        }
    }

    private void inverseR(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 1, bArr2, 0, 15);
        bArr2[15] = bArr[0];
        byte l2 = l(bArr2);
        System.arraycopy(bArr, 1, bArr, 0, 15);
        bArr[15] = l2;
    }

    private void inverseS(byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = inversePI[unsignedByte(bArr[i2])];
        }
    }

    private static byte kuz_mul_gf256_slow(byte b2, byte b3) {
        byte b4 = 0;
        for (byte b5 = 0; b5 < 8 && b2 != 0 && b3 != 0; b5 = (byte) (b5 + 1)) {
            if ((b3 & 1) != 0) {
                b4 = (byte) (b4 ^ b2);
            }
            byte b6 = (byte) (b2 & 128);
            b2 = (byte) (b2 << 1);
            if (b6 != 0) {
                b2 = (byte) (b2 ^ 195);
            }
            b3 = (byte) (b3 >> 1);
        }
        return b4;
    }

    private byte l(byte[] bArr) {
        byte b2 = bArr[15];
        for (int i2 = 14; i2 >= 0; i2--) {
            b2 = (byte) (b2 ^ this._gf_mul[unsignedByte(bArr[i2])][unsignedByte(this.lFactors[i2])]);
        }
        return b2;
    }

    private int unsignedByte(byte b2) {
        return b2 & 255;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "GOST3412_2015";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.forEncryption = z;
            generateSubKeys(((KeyParameter) cipherParameters).getKey());
        } else if (cipherParameters == null) {
        } else {
            throw new IllegalArgumentException("invalid parameter passed to GOST3412_2015 init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (this.subKeys != null) {
            if (i2 + 16 <= bArr.length) {
                if (i3 + 16 <= bArr2.length) {
                    GOST3412_2015Func(bArr, i2, bArr2, i3);
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("GOST3412_2015 engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
